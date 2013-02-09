/**
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE
 * file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.ez18n.apt.processor;

import static java.lang.Math.min;
import static javax.lang.model.SourceVersion.RELEASE_6;
import static javax.tools.StandardLocation.SOURCE_OUTPUT;
import static org.ez18n.apt.macro.MacroProcessor.replaceProperties;
import static org.ez18n.apt.processor.LabelBundleProcessor.NO_VALUE;
import static org.ez18n.apt.processor.MessagesFactoryProcessor.getMessagesFactoryClassName;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.FilerException;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;
import javax.tools.FileObject;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.ez18n.MessageBundle;
import org.ez18n.apt.TemplateLoader;
import org.ez18n.apt.macro.PropertyParsingException;

@SupportedAnnotationTypes(value = "org.ez18n.MessageBundle")
@SupportedSourceVersion(RELEASE_6)
public final class GwtXmlProcessor extends AbstractProcessor {
    private final String template;
    private final String fragment_template;

    public GwtXmlProcessor() {
        try {
            template = TemplateLoader.load("GeneratedGwtXml.template");
            fragment_template = TemplateLoader.load("GeneratedGwtXml.factory.template");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (roundEnv.processingOver()) {
            return true;
        }

        final List<TypeElement> bundleTypes = new ArrayList<TypeElement>();
        for (Element element : roundEnv.getElementsAnnotatedWith(MessageBundle.class)) {
            if (element.getKind() != ElementKind.INTERFACE) {
                continue;
            }
            final TypeElement bundleType = (TypeElement) element;
            bundleTypes.add(bundleType);
        }

        if (bundleTypes.isEmpty()) {
            return true;
        }

        String packageName = null;
        for (TypeElement typeElement : bundleTypes) {
            String typeElementPackage = typeElement.getEnclosingElement().toString();
            if (packageName == null) {
                packageName = typeElementPackage;
            } else {
                packageName = intersectPackage(packageName, typeElementPackage);
            }
        }

        try {
            final FileObject file = processingEnv.getFiler().createResource(SOURCE_OUTPUT,
                            packageName == null ? "net.courtanet.b2c" : packageName, "Generated.gwt.xml");
            final Writer writer = file.openWriter();
            writer.write(getCode(bundleTypes));
            writer.close();
        } catch (FilerException e) {
            return false;
        } catch (IOException e) {
            processingEnv.getMessager().printMessage(Kind.ERROR, e.getMessage());
        }
        return false;
    }

    private final String getCode(List<TypeElement> bundleTypes) {
        final StringBuffer fragments = new StringBuffer();
        for (TypeElement bundleType : bundleTypes) {
            fragments.append(getCode(bundleType));
        }

        final String code;
        final Map<String, String> conf = new HashMap<String, String>();
        conf.put("factory.fragment", fragments.toString());
        try {
            code = replaceProperties(template, conf, "#no_value#");
        } catch (PropertyParsingException e) {
            throw new RuntimeException(e);
        }
        return code;
    }

    private final String getCode(TypeElement bundleTypes) {
        final String code;
        final Map<String, String> conf = new HashMap<String, String>();
        final String messagesFactoryClassName = getMessagesFactoryClassName(bundleTypes, true);
        conf.put("mobile.label.factory.class.name", messagesFactoryClassName + ".MobileLabelFactory");
        conf.put("desktop.label.factory.class.name", messagesFactoryClassName + ".DesktopLabelFactory");
        try {
            code = replaceProperties(fragment_template, conf, NO_VALUE);
        } catch (PropertyParsingException e) {
            throw new RuntimeException(e);
        }
        return code;
    }

    private final String intersectPackage(String s1, String s2) {
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
}
