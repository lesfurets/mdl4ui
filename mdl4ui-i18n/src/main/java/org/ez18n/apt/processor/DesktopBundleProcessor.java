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

import static java.text.DateFormat.SHORT;
import static javax.lang.model.SourceVersion.RELEASE_6;
import static org.ez18n.apt.base.TemplateMethod.trimJavaDotLangDot;
import static org.ez18n.apt.macro.MacroProcessor.replaceProperties;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.element.TypeElement;

import org.ez18n.apt.LabelTemplateMethod;
import org.ez18n.apt.TemplateLoader;
import org.ez18n.apt.macro.PropertyParsingException;

@SupportedAnnotationTypes(value = "org.ez18n.MessageBundle")
@SupportedSourceVersion(RELEASE_6)
public final class DesktopBundleProcessor extends LabelBundleProcessor {
    private final String template;
    private final String no_param_method_template;
    private final String multi_param_method_template;

    public DesktopBundleProcessor() {
        try {
            template = TemplateLoader.load("DesktopBundle.template");
            no_param_method_template = TemplateLoader.load("NoParamBundleMethod.template");
            multi_param_method_template = TemplateLoader.load("MultiParamBundleMethod.template");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    protected String getTargetSimpleName(TypeElement typeElement) {
        return getDesktopBundleClassName(typeElement, false);
    }

    static final String getDesktopBundleClassName(TypeElement typeElement, boolean fqcn) {
        final String simpleName = typeElement.getSimpleName().toString();
        final int resourceIndex = simpleName.indexOf("Resources");
        final String shortName = resourceIndex > 0 ? simpleName.substring(0, resourceIndex) : simpleName;
        return (fqcn ? typeElement.getEnclosingElement().toString() + "." : "") + shortName + "DesktopBundle";
    }

    @Override
    protected String getCode(TypeElement bundleType, List<LabelTemplateMethod> methods) {
        final StringBuffer methodsCode = new StringBuffer();
        for (LabelTemplateMethod method : methods)
            methodsCode.append(getCode(bundleType, method));

        final String code;
        final Map<String, String> conf = new HashMap<String, String>();
        conf.put("process.class", getClass().getName());
        conf.put("process.date", DateFormat.getDateTimeInstance(SHORT, SHORT).format(new Date()));
        conf.put("source.class.name.camel", toCamelCase(bundleType));
        conf.put("target.class.name", getTargetSimpleName(bundleType));
        conf.put("source.class.name", bundleType.getSimpleName().toString());
        conf.put("package.name", bundleType.getEnclosingElement().toString());
        conf.put("methods.code", methodsCode.toString());
        conf.put("bundle.property.file", "Desktop" + bundleType.getSimpleName().toString());
        try {
            code = replaceProperties(template, conf, NO_VALUE);
        } catch (PropertyParsingException e) {
            throw new RuntimeException(e);
        }
        return code;
    }

    private final String getCode(TypeElement bundleType, LabelTemplateMethod method) {
        final String code;
        final Map<String, String> conf = new HashMap<String, String>();
        conf.put("return.type", trimJavaDotLangDot(method.getReturnParam().getType()));
        conf.put("default.message", method.getBase());
        conf.put("method.name", method.getName());
        conf.put("input.params", method.formatParamsName(false));
        conf.put("input.typed.params", method.formatParamsTypeAndName(false));
        final String method_template = method.getParams().isEmpty() ? no_param_method_template
                        : multi_param_method_template;
        try {
            code = replaceProperties(method_template, conf, NO_VALUE);
        } catch (PropertyParsingException e) {
            throw new RuntimeException(e);
        }
        return code;
    }
}
