/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.base.apt;

import static java.lang.Character.isUpperCase;
import static java.lang.Math.min;
import static org.apache.commons.lang3.StringUtils.join;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;

import org.apache.commons.lang3.StringUtils;
import org.ez18n.MessageBundle;
import org.mdl4ui.base.model.ElementID;
import org.mdl4ui.base.model.FieldID;

import com.google.common.collect.Multimap;

abstract class FieldProcessor extends AbstractProcessor {
    private static MessageFormat error = new MessageFormat(
                    "[processor:{0}] {1}#{2} duplicate injection [{3}] and [{4}]");

    abstract boolean isGwtFactory();

    abstract String simpleClassName(String... prefix);

    String qualifiedClassName(String packageName, String... prefix) {
        return packageName + "." + simpleClassName(prefix);
    }

    static <E extends Element> String typeElementToConstant(E element) {
        final StringBuilder builder = new StringBuilder();
        final char[] className = element.getSimpleName().toString().toCharArray();
        for (int i = 0; i < className.length; i++) {
            if (Character.isUpperCase(className[i]) && i > 0) {
                builder.append('_');
                builder.append(className[i]);
            } else
                builder.append(Character.toUpperCase(className[i]));
        }
        return builder.toString();
    }

    String getFieldConstantFromTypeElement(Set<TypeElement> typeElements) {
        final List<TypeElement> sortedTypeElements = new ArrayList<TypeElement>(typeElements);
        Collections.sort(sortedTypeElements, new Comparator<TypeElement>() {
            @Override
            public int compare(TypeElement o1, TypeElement o2) {
                if (o1 == null)
                    return 0;
                if (o2 == null)
                    return 1;
                final String t1 = typeElementToConstant(o1);
                final String t2 = typeElementToConstant(o2);
                return t1.compareTo(t2);
            }
        });
        final StringBuilder builder = new StringBuilder();
        final Iterator<TypeElement> it = sortedTypeElements.iterator();
        while (it.hasNext()) {
            final TypeElement elt = it.next();
            builder.append("        ");
            builder.append(typeElementToConstant(elt));
            builder.append("(new ");
            builder.append(elt.getQualifiedName().toString());
            builder.append("(");
            builder.append(injectConstructorParameters(elt));
            builder.append(it.hasNext() ? ")), //\n" : "));");
        }
        return builder.toString();
    }

    static <A> String getLabelInit(Set<ExecutableElement> filterElts, Multimap<ExecutableElement, A> fieldLabels,
                    String mapKey, FactoryName factoryName) {
        final List<ExecutableElement> sortedFilterElts = new ArrayList<ExecutableElement>(filterElts);
        Collections.sort(sortedFilterElts, new ExecutableElementComparator());
        final StringBuilder builder = new StringBuilder();
        final Iterator<ExecutableElement> itElts = filterElts.iterator();
        while (itElts.hasNext()) {
            final ExecutableElement elt = itElts.next();
            final Iterator<A> itField = fieldLabels.get(elt).iterator();
            while (itField.hasNext()) {
                final A fieldId = itField.next();
                builder.append("           ");
                builder.append(mapKey);
                builder.append(".put(");
                builder.append(fieldId.getClass().getName());
                builder.append('.');
                builder.append(fieldId.toString());
                builder.append(", ");
                builder.append(factoryName.getName(elt, true));
                builder.append(".");
                builder.append(elt.getSimpleName());
                builder.append("());");
                if (itField.hasNext() || itElts.hasNext())
                    builder.append("\n");
            }
        }
        return builder.toString();
    }

    private String injectConstructorParameters(Element elt) {
        ExecutableElement constructor = null;

        // retrieve constructor with the highest parameter count
        for (Element enclosedElement : elt.getEnclosedElements()) {
            if (enclosedElement.getKind() == ElementKind.CONSTRUCTOR) {
                ExecutableElement method = (ExecutableElement) enclosedElement;
                if (constructor == null || method.getParameters().size() > constructor.getParameters().size()) {
                    constructor = (ExecutableElement) enclosedElement;
                }
            }
        }

        if (constructor == null) {
            // no constructor found, using default constructor instead
            return "";
        }

        List<String> parameters = new ArrayList<String>();
        FactoryName factoryName = isGwtFactory() ? new GwtFactoryName() : new BundleFactoryName();
        for (VariableElement parameter : constructor.getParameters()) {
            TypeMirror asType = parameter.asType();
            if (processingEnv.getTypeUtils().asElement(asType).getAnnotation(MessageBundle.class) != null) {
                // add label bundle
                parameters.add(factoryName.getName(processingEnv.getTypeUtils().asElement(asType), true));
            } else {
                // unsupported constructor parameter
                throw new IllegalArgumentException("unsupported constructor parameter :"
                                + processingEnv.getTypeUtils().asElement(asType).toString());
            }
        }
        return join(parameters, ", ");
    }

    interface FactoryName {
        String getName(Element execElement, boolean fqcn);

        String getName(ExecutableElement execElement, boolean fqcn);

        String[] prefix();

    }

    static final class GwtFactoryName implements FactoryName {
        @Override
        public String getName(Element element, boolean fqcn) {
            final String simpleName = element.getSimpleName().toString();
            final int resourceIndex = simpleName.indexOf("Resources");
            final String shortName = resourceIndex > 0 ? simpleName.substring(0, resourceIndex) : simpleName;
            return (fqcn ? element.getEnclosingElement().toString() + "." : "") + shortName + "MessagesFactory.MSG_"
                            + toCamelCase(element) + "()";
        }

        @Override
        public String getName(ExecutableElement execElement, boolean fqcn) {
            return getName(execElement.getEnclosingElement(), fqcn);
        }

        @Override
        public String[] prefix() {
            return new String[] { "Gwt" };
        }
    }

    static final class BundleFactoryName implements FactoryName {
        @Override
        public String getName(Element element, boolean fqcn) {
            final String simpleName = element.getSimpleName().toString();
            final int resourceIndex = simpleName.indexOf("Resources");
            final String shortName = resourceIndex > 0 ? simpleName.substring(0, resourceIndex) : simpleName;
            return (fqcn ? element.getEnclosingElement().toString() + "." : "") + shortName + "DesktopBundle.MSG_"
                            + toCamelCase(element);
        }

        @Override
        public String getName(ExecutableElement execElement, boolean fqcn) {
            return getName(execElement.getEnclosingElement(), fqcn);
        }

        @Override
        public String[] prefix() {
            return new String[] { "Bundle" };
        }
    }

    private static String toCamelCase(Element typeElement) {
        return toCamelCase(typeElement.getSimpleName().toString());
    }

    private static String toCamelCase(String string) {
        final StringBuffer result = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            final char charAt = string.charAt(i);
            if (isUpperCase(charAt))
                result.append(charAt);
        }
        return result.toString();
    }

    static String getFieldIdsInit(Set<TypeElement> filterElts, Multimap<TypeElement, FieldID> initializer) {
        final List<TypeElement> sortedFilterElts = new ArrayList<TypeElement>(filterElts);
        Collections.sort(sortedFilterElts, new TypeElementComparator());
        final StringBuilder builder = new StringBuilder();
        final Iterator<TypeElement> itElts = filterElts.iterator();
        while (itElts.hasNext()) {
            final Element elt = itElts.next();
            final List<FieldID> sortedFieldIds = new ArrayList<FieldID>(initializer.get((TypeElement) elt));
            Collections.sort(sortedFieldIds, new FieldIdComparator());
            final Iterator<FieldID> itField = sortedFieldIds.iterator();
            while (itField.hasNext()) {
                final FieldID fieldId = itField.next();
                builder.append("           ${map.name}.put(");
                builder.append(fieldId.getClass().getName());
                builder.append('.');
                builder.append(fieldId.toString());
                builder.append(", ${enum.support}.");
                builder.append(typeElementToConstant(elt));
                builder.append(");");
                if (itField.hasNext() || itElts.hasNext())
                    builder.append("\n");
            }
        }
        return builder.toString();
    }

    static final <E extends Element, A> void insertAndCheckUnicity(FieldProcessor processor,
                    Multimap<E, A> injectedTarget, E element, Set<A> fieldIds, ProcessingEnvironment env) {
        for (A fieldId : fieldIds) {
            for (Map.Entry<E, Collection<A>> entry : injectedTarget.asMap().entrySet()) {
                if (entry.getValue().contains(fieldId)) {
                    final String msg = error.format(new Object[] { processor.getClass().getSimpleName(),
                                    fieldId.getClass().getSimpleName(), fieldId.toString(),
                                    entry.getKey().getSimpleName(), element.getSimpleName() });
                    env.getMessager().printMessage(Diagnostic.Kind.ERROR, msg);
                }
            }
        }
        injectedTarget.putAll(element, fieldIds);
    }

    private static final class TypeElementComparator implements Comparator<TypeElement> {
        @Override
        public int compare(TypeElement o1, TypeElement o2) {
            final String t1 = typeElementToConstant(o1);
            final String t2 = typeElementToConstant(o2);
            return t1.compareTo(t2);
        }

    }

    private static final class ExecutableElementComparator implements Comparator<ExecutableElement> {
        @Override
        public int compare(ExecutableElement o1, ExecutableElement o2) {
            final TypeElement t1 = (TypeElement) o1.getEnclosingElement();
            final TypeElement t2 = (TypeElement) o2.getEnclosingElement();
            return t1.getQualifiedName().toString().compareTo(t2.getQualifiedName().toString());
        }
    }

    private static final class FieldIdComparator implements Comparator<FieldID> {
        @Override
        public int compare(FieldID f1, FieldID f2) {
            return f1.toString().compareTo(f2.toString());
        }
    }

    static <T extends Element> String getPackage(Collection<T> elements) {
        String packageName = null;
        for (T typeElement : elements) {
            String typeElementPackage = typeElement.getEnclosingElement().toString();
            if (packageName == null) {
                packageName = typeElementPackage;
            } else {
                packageName = intersectPackage(packageName, typeElementPackage);
            }
        }
        return packageName;
    }

    private static String intersectPackage(String s1, String s2) {
        String[] elements1 = StringUtils.split(s1, ".");
        String[] elements2 = StringUtils.split(s2, ".");

        List<String> intersection = new ArrayList<String>();
        for (int i = 0; i < min(elements1.length, elements2.length); i++) {
            if (!elements1[i].equals(elements2[i])) {
                break;
            }
            intersection.add(elements1[i]);
        }
        return StringUtils.join(intersection, ".");
    }

    static ElementID getElementID(Element element) throws Exception {
        final String elementName = element.getSimpleName().toString();
        Class<?> enumClass = Class.forName(((TypeElement) element.getEnclosingElement()).getQualifiedName().toString());
        List<ElementID> values = Arrays.asList((ElementID[]) enumClass.getMethod("values").invoke(null));
        for (ElementID elementID : values) {
            if (elementName.equals(elementID.toString())) {
                return elementID;
            }
        }
        return null;
    }
}
