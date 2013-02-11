/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.base.apt;

import static java.text.DateFormat.SHORT;
import static javax.lang.model.SourceVersion.RELEASE_6;
import static javax.lang.model.element.ElementKind.ANNOTATION_TYPE;
import static javax.lang.model.element.ElementKind.METHOD;

import java.io.IOException;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.SimpleAnnotationValueVisitor6;
import javax.tools.Diagnostic.Kind;
import javax.tools.JavaFileObject;

import org.apache.commons.io.IOUtils;
import org.ez18n.apt.macro.MacroProcessor;
import org.ez18n.apt.macro.PropertyParsingException;
import org.mdl4ui.base.model.BlockID;
import org.mdl4ui.base.model.ElementID;
import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.base.model.GroupID;
import org.mdl4ui.base.model.ScreenID;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

@SupportedAnnotationTypes(value = "org.mdl4ui.base.injection.InjectLabel")
@SupportedSourceVersion(RELEASE_6)
public abstract class I18nFieldLabelProcessor extends FieldProcessor {

    @Override
    protected boolean isGwtFactory() {
        return true;
    }

    protected abstract Class<? extends Annotation> getAnnotationClass();

    protected abstract String getTemplate();

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        if (roundEnv.processingOver()) {
            return true;
        }
        final Multimap<ExecutableElement, FieldID> fields = HashMultimap.create();
        final Multimap<ExecutableElement, GroupID> groups = HashMultimap.create();
        final Multimap<ExecutableElement, BlockID> blocks = HashMultimap.create();
        final Multimap<ExecutableElement, ScreenID> screens = HashMultimap.create();

        try {
            for (Element element : roundEnv.getElementsAnnotatedWith(getAnnotationClass())) {
                if (element.getKind() != ANNOTATION_TYPE) {
                    continue;
                }
                final TypeElement annotation = (TypeElement) element;
                final Collection<TypeElement> messageBundles = new ArrayList<TypeElement>();

                // retrieve all annotations extending @InjectLabel
                for (Element elementAnnoted : roundEnv.getElementsAnnotatedWith(annotation)) {
                    if (elementAnnoted.getKind() != METHOD) {
                        continue;
                    }
                    final ExecutableElement methodAnnoted = (ExecutableElement) elementAnnoted;
                    messageBundles.add((TypeElement) methodAnnoted.getEnclosingElement());

                    // retrieve all annotated elements by this inherited annotation
                    for (AnnotationMirror injectLabel : elementAnnoted.getAnnotationMirrors()) {
                        Map<? extends ExecutableElement, ? extends AnnotationValue> injectLabelValues = injectLabel
                                        .getElementValues();

                        // retrieve element ids from this annotations
                        final OnElementVisitor visitor = new OnElementVisitor();
                        final OnElementVisitorContext visitorContext = new OnElementVisitorContext();
                        for (ExecutableElement onElementMethod : injectLabelValues.keySet()) {
                            AnnotationValue onElementValue = injectLabelValues.get(onElementMethod);
                            onElementValue.accept(visitor, visitorContext);
                        }

                        final Set<ScreenID> screensIds = new HashSet<ScreenID>();
                        final Set<BlockID> blockIds = new HashSet<BlockID>();
                        final Set<GroupID> groupIds = new HashSet<GroupID>();
                        final Set<FieldID> fieldIds = new HashSet<FieldID>();
                        for (Element elementFound : visitorContext.elementIds) {
                            final ElementID elementID = getElementID(elementFound);
                            switch (elementID.elementType()) {
                                case SCREEN:
                                    screensIds.add((ScreenID) elementID);
                                    break;
                                case BLOCK:
                                    blockIds.add((BlockID) elementID);
                                    break;
                                case GROUP:
                                    groupIds.add((GroupID) elementID);
                                    break;
                                case FIELD:
                                    fieldIds.add((FieldID) elementID);
                                    break;
                            }
                        }

                        insertAndCheckUnicity(this, fields, methodAnnoted, fieldIds, processingEnv);
                        insertAndCheckUnicity(this, groups, methodAnnoted, groupIds, processingEnv);
                        insertAndCheckUnicity(this, blocks, methodAnnoted, blockIds, processingEnv);
                        insertAndCheckUnicity(this, screens, methodAnnoted, screensIds, processingEnv);
                    }
                }

                final String packageName = getPackage(messageBundles);

                final FactoryName[] factories = new FactoryName[] { new GwtFactoryName(), new BundleFactoryName() };
                for (FactoryName factoryName : factories) {
                    final String className = qualifiedClassName(packageName, factoryName.prefix());
                    processingEnv.getMessager().printMessage(Kind.NOTE, className);
                    try {
                        final JavaFileObject file = processingEnv.getFiler().createSourceFile(className);
                        final Writer writer = file.openWriter();
                        writer.write(getCode(fields, groups, blocks, screens, true, factoryName, packageName));
                        writer.close();
                    } catch (IOException e) {
                        processingEnv.getMessager().printMessage(Kind.ERROR, e.getMessage());
                    }
                }
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ElementID getElementID(Element element) throws Exception {
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

    private String getCode(Multimap<ExecutableElement, FieldID> fields, //
                    Multimap<ExecutableElement, GroupID> groups, //
                    Multimap<ExecutableElement, BlockID> blocks, //
                    Multimap<ExecutableElement, ScreenID> screens, //
                    boolean gwt, FactoryName factoryName, String packageName) throws IOException {
        final String template = IOUtils.toString(getClass().getResourceAsStream(getTemplate()));
        final Set<ExecutableElement> filterElts = new HashSet<ExecutableElement>(fields.keySet());
        filterElts.addAll(groups.keySet());
        filterElts.addAll(blocks.keySet());
        filterElts.addAll(screens.keySet());

        final StringBuilder labelInit = new StringBuilder();
        labelInit.append(getLabelInit(filterElts, fields, "${map.name.elements}", factoryName));
        labelInit.append(getLabelInit(filterElts, groups, "${map.name.elements}", factoryName));
        labelInit.append(getLabelInit(filterElts, blocks, "${map.name.elements}", factoryName));
        labelInit.append(getLabelInit(filterElts, screens, "${map.name.elements}", factoryName));

        final Map<String, String> conf = new HashMap<String, String>();
        conf.put("process.class", getClass().getName());
        conf.put("process.date", DateFormat.getDateTimeInstance(SHORT, SHORT).format(new Date()));
        conf.put("package.name", packageName);
        conf.put("target.class.name", simpleClassName(factoryName.prefix()));
        conf.put("map.name.elements", "elements");
        conf.put("map.name.tags", "tags");
        conf.put("class.init", labelInit.toString());
        try {
            return MacroProcessor.replaceProperties(template, conf, "#no_value#");
        } catch (PropertyParsingException e) {
            throw new RuntimeException(e);
        }
    }

    private static final class OnElementVisitor extends SimpleAnnotationValueVisitor6<Void, OnElementVisitorContext> {

        @Override
        public Void visitEnumConstant(VariableElement c, OnElementVisitorContext p) {
            p.elementIds.add(c);
            return null;
        }

        @Override
        public Void visitArray(List<? extends AnnotationValue> vals, OnElementVisitorContext p) {
            for (AnnotationValue annotationValue : vals) {
                visit(annotationValue, p);
            }
            return null;
        }

        @Override
        public Void visitAnnotation(AnnotationMirror a, OnElementVisitorContext p) {
            for (AnnotationValue annotationValue : a.getElementValues().values()) {
                visit(annotationValue, p);
            }
            return null;
        }

        @Override
        public Void visitUnknown(AnnotationValue av, OnElementVisitorContext p) {
            return super.visitUnknown(av, p);
        }
    }

    private static final class OnElementVisitorContext {
        private final Set<Element> elementIds = new HashSet<Element>();
    }
}
