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
import static org.apache.commons.lang3.CharUtils.isAscii;
import static org.apache.commons.lang3.CharUtils.unicodeEscaped;
import static org.ez18n.apt.macro.MacroProcessor.replaceProperties;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.TypeElement;

import org.ez18n.apt.LabelTemplateMethod;
import org.ez18n.apt.TemplateLoader;
import org.ez18n.apt.base.EGenerationType;
import org.ez18n.apt.macro.PropertyParsingException;

abstract class BundlePropertiesProcessor extends LabelBundleProcessor {
    private final String template;

    public BundlePropertiesProcessor() {
        super(EGenerationType.RESSOURCE);
        try {
            template = TemplateLoader.load("BundleProperty.template");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    protected abstract String getPrefix();

    @Override
    protected String getTargetSimpleName(TypeElement typeElement) {
        return getPrefix() + typeElement.getSimpleName().toString();
    }

    @Override
    protected String getCode(TypeElement bundleType, List<LabelTemplateMethod> methods) {
        final StringBuffer methodsCode = new StringBuffer();
        for (LabelTemplateMethod method : methods) {
            methodsCode.append(getProperty(bundleType, method));
        }
        return methodsCode.toString();
    }

    protected abstract String getPropertyValue(LabelTemplateMethod method);

    protected final String getProperty(TypeElement bundleType, LabelTemplateMethod method) {
        try {
            final Map<String, String> conf = new HashMap<String, String>();
            conf.put("process.class", getClass().getName());
            conf.put("process.date", DateFormat.getDateTimeInstance(SHORT, SHORT).format(new Date()));
            conf.put("key", method.getName());
            conf.put("value", toAscii(getPropertyValue(method)));
            return replaceProperties(template, conf, NO_VALUE);
        } catch (PropertyParsingException e) {
            throw new RuntimeException(e);
        }
    }

    protected String toAscii(String string) {
        StringBuffer buffer = new StringBuffer();
        for (char character : string.toCharArray()) {
            buffer.append(isAscii(character) ? character : unicodeEscaped(character));
        }
        return buffer.toString();
    }
}
