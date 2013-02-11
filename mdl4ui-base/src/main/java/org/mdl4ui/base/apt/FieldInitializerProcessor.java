/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.base.apt;

import static java.text.DateFormat.SHORT;
import static javax.lang.model.element.ElementKind.ANNOTATION_TYPE;
import static javax.lang.model.element.ElementKind.CLASS;

import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;
import javax.tools.JavaFileObject;

import org.apache.commons.io.IOUtils;
import org.ez18n.apt.macro.MacroProcessor;
import org.ez18n.apt.macro.PropertyParsingException;
import org.mdl4ui.base.injection.InjectInit;
import org.mdl4ui.base.model.ElementID;
import org.mdl4ui.base.model.FieldID;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public abstract class FieldInitializerProcessor extends FieldProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (roundEnv.processingOver()) {
            return true;
        }

        final Multimap<TypeElement, FieldID> initializer = HashMultimap.create();

        try {
            for (Element element : roundEnv.getElementsAnnotatedWith(InjectInit.class)) {
                if (element.getKind() != ANNOTATION_TYPE) {
                    continue;
                }
                final TypeElement annotation = (TypeElement) element;
                final Collection<TypeElement> classesAnnoted = new ArrayList<TypeElement>();

                // retrieve all annotations extending source annotation
                for (Element elementAnnoted : roundEnv.getElementsAnnotatedWith(annotation)) {
                    if (elementAnnoted.getKind() != CLASS) {
                        continue;
                    }
                    final TypeElement classAnnoted = (TypeElement) elementAnnoted;
                    classesAnnoted.add(classAnnoted);

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

                        final Set<FieldID> fieldIds = new HashSet<FieldID>();
                        for (Element elementFound : visitorContext.getElementIds()) {
                            final ElementID elementID = getElementID(elementFound);
                            fieldIds.add((FieldID) elementID);
                        }

                        insertAndCheckUnicity(this, initializer, classAnnoted, fieldIds, processingEnv);
                    }
                }

                if (classesAnnoted.isEmpty()) {
                    continue;
                }

                final String packageName = getPackage(classesAnnoted);
                final String className = qualifiedClassName(packageName);
                processingEnv.getMessager().printMessage(Kind.NOTE, className);

                try {
                    final JavaFileObject file = processingEnv.getFiler().createSourceFile(className);
                    final Writer writer = file.openWriter();
                    writer.write(getCode(initializer, packageName));
                    writer.close();
                } catch (IOException e) {
                    processingEnv.getMessager().printMessage(Kind.ERROR, e.getMessage());
                }
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getCode(Multimap<TypeElement, FieldID> initializer, String packageName) throws IOException {
        final String classTemplate = IOUtils.toString(getClass()
                        .getResourceAsStream("FieldInitializerFactory.template"));
        final Set<TypeElement> filterElts = initializer.keySet();
        final Map<String, String> conf = new HashMap<String, String>();
        conf.put("process.class", getClass().getName());
        conf.put("process.date", DateFormat.getDateTimeInstance(SHORT, SHORT).format(new Date()));
        conf.put("package.name", packageName);
        conf.put("target.class.name", simpleClassName());
        conf.put("constants", getFieldConstantFromTypeElement(filterElts));
        conf.put("map.name", "initializers");
        conf.put("enum.support", "EFieldInitializer");
        conf.put("class.init", getFieldIdsInit(filterElts, initializer));
        try {
            return MacroProcessor.replaceProperties(classTemplate, conf, "#no_value#");
        } catch (PropertyParsingException e) {
            throw new RuntimeException(e);
        }
    }
}
