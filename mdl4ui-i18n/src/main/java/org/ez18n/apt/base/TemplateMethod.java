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
package org.ez18n.apt.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TemplateMethod {
    private static final String java_dot_lang_dot = "java.lang.";
    private static final String EMPTY = "";
    private final boolean deprecated;
    private final String name;
    private final List<TemplateParam> params = new ArrayList<TemplateParam>();
    private final TemplateParam returnParam;
    private final List<TemplateSignal> exceptions = new ArrayList<TemplateSignal>();

    public TemplateMethod(String name, boolean deprecated, TemplateParam returnParam) {
        this.name = name;
        this.deprecated = deprecated;
        this.returnParam = returnParam;
    }

    public String getName() {
        return name;
    }

    public boolean isDeprecated() {
        return deprecated;
    }

    public List<TemplateParam> getParams() {
        return params;
    }

    public TemplateParam getReturnParam() {
        return returnParam;
    }

    public List<TemplateSignal> getExceptions() {
        return exceptions;
    }

    public final String formatParamsName(boolean append) {
        return formatParamsName(append, "", "");
    }

    public final String formatParamsName(boolean append, String prefix, String suffix) {
        final StringBuffer buffer = new StringBuffer();
        if (append && params.size() > 0)
            buffer.append(", ");
        for (int i = 0; i < params.size(); i++) {
            buffer.append(prefix);
            buffer.append(params.get(i).getName());
            buffer.append(suffix);
            if (i < params.size() - 1)
                buffer.append(", ");
        }
        return buffer.toString();
    }

    public final String formatParamsType(boolean append) {
        final StringBuffer buffer = new StringBuffer();
        if (append && params.size() > 0)
            buffer.append(", ");
        for (int i = 0; i < params.size(); i++) {
            final String type = params.get(i).getType();
            final int indexOfLowerThan = type.indexOf('<');
            buffer.append(indexOfLowerThan > 0 ? type.substring(0, indexOfLowerThan) : type);
            if (i < params.size() - 1)
                buffer.append(", ");
        }
        return trimJavaDotLangDot(buffer.toString());
    }

    public final String formatParamsTypeAsList(boolean append) {
        final StringBuffer buffer = new StringBuffer();
        if (append && params.size() > 0)
            buffer.append(", ");
        for (int i = 0; i < params.size(); i++) {
            final String type = params.get(i).getType();
            final int indexOfLowerThan = type.indexOf('<');
            buffer.append("java.util.List<");
            buffer.append(indexOfLowerThan > 0 ? type.substring(0, indexOfLowerThan) : type);
            buffer.append(">");
            if (i < params.size() - 1)
                buffer.append(", ");
        }
        return trimJavaDotLangDot(buffer.toString());
    }

    public final String formatParamsAsFields() {
        final StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < params.size(); i++) {
            buffer.append(params.get(i).getType());
            buffer.append(" ");
            buffer.append(params.get(i).getName());
            buffer.append(";");
            if (i < params.size() - 1)
                buffer.append("\n        ");
        }
        return trimJavaDotLangDot(buffer.toString());
    }

    public final String formatParamsAsFieldLists() {
        final StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < params.size(); i++) {
            buffer.append("private final java.util.List<");
            buffer.append(params.get(i).getType());
            buffer.append("> ");
            buffer.append(params.get(i).getName());
            buffer.append("List = new java.util.ArrayList<");
            buffer.append(params.get(i).getType());
            buffer.append(">();");
            if (i < params.size() - 1)
                buffer.append("\n        ");
        }
        return trimJavaDotLangDot(buffer.toString());
    }

    public final String formatParamsAsFieldListsClear() {
        final StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < params.size(); i++) {
            buffer.append(params.get(i).getName());
            buffer.append("List.clear();");
            if (i < params.size() - 1)
                buffer.append("\n                ");
        }
        return buffer.toString();
    }

    public final String formatParamsAsFieldListsAdd() {
        final StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < params.size(); i++) {
            buffer.append(params.get(i).getName());
            buffer.append("List.add(");
            buffer.append(params.get(i).getName());
            buffer.append(");");
            if (i < params.size() - 1)
                buffer.append("\n            ");
        }
        return buffer.toString();
    }

    public final String fillFieldLists() {
        final StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < params.size(); i++) {
            buffer.append(params.get(i).getName());
            buffer.append("List.add(e.");
            buffer.append(params.get(i).getName());
            buffer.append(");");
            if (i < params.size() - 1)
                buffer.append("\n                    ");
        }
        return trimJavaDotLangDot(buffer.toString());
    }

    public final String formatParamsAsInit() {
        final StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < params.size(); i++) {
            buffer.append("this.");
            buffer.append(params.get(i).getName());
            buffer.append(" = ");
            buffer.append(params.get(i).getName());
            buffer.append(";");
            if (i < params.size() - 1)
                buffer.append("\n            ");
        }
        return buffer.toString();
    }

    public final String formatParamsAsEvent() {
        final StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < params.size(); i++) {
            buffer.append("e.");
            buffer.append(params.get(i).getName());
            if (i < params.size() - 1)
                buffer.append(", ");
        }
        return buffer.toString();
    }

    public final String formatParamsTypeAndName(boolean append) {
        final StringBuffer buffer = new StringBuffer();
        if (append && params.size() > 0)
            buffer.append(", ");
        for (int i = 0; i < params.size(); i++) {
            buffer.append(params.get(i).getType());
            buffer.append(" ");
            buffer.append(params.get(i).getName());
            if (i < params.size() - 1)
                buffer.append(", ");
        }
        return trimJavaDotLangDot(buffer.toString());
    }

    public final String formatParamsTypeAndNameAndAnnotations(boolean append) {
        final StringBuffer buffer = new StringBuffer();
        if (append && params.size() > 0)
            buffer.append(", ");
        for (int i = 0; i < params.size(); i++) {
            for (int j = 0; j < params.get(i).getAnnotations().size(); j++) {
                buffer.append("@");
                buffer.append(params.get(i).getAnnotations().get(j).getType());
                buffer.append(" ");
            }

            buffer.append(params.get(i).getType());
            buffer.append(" ");
            buffer.append(params.get(i).getName());
            if (i < params.size() - 1)
                buffer.append(", ");
        }
        return trimJavaDotLangDot(buffer.toString());
    }

    public final String formatParamsTypeAsListAndName(boolean append) {
        final StringBuffer buffer = new StringBuffer();
        if (append && params.size() > 0)
            buffer.append(", ");
        for (int i = 0; i < params.size(); i++) {
            buffer.append("java.util.List<");
            buffer.append(params.get(i).getType());
            buffer.append("> ");
            buffer.append(params.get(i).getName());
            if (i < params.size() - 1)
                buffer.append(", ");
        }
        return trimJavaDotLangDot(buffer.toString());
    }

    public final String formatThrowsExceptions() {
        final StringBuffer buffer = new StringBuffer();
        if (exceptions.size() > 0)
            buffer.append("throws ");
        for (int i = 0; i < exceptions.size(); i++) {
            buffer.append(exceptions.get(i).getType());
            if (i < exceptions.size() - 1)
                buffer.append(", ");
        }
        return trimJavaDotLangDot(buffer.toString());
    }

    public final String formatExceptions() {
        final StringBuffer buffer = new StringBuffer();
        if (exceptions.size() > 0)
            buffer.append(", ");
        for (int i = 0; i < exceptions.size(); i++) {
            buffer.append(exceptions.get(i).getType());
            if (i < exceptions.size() - 1)
                buffer.append(", ");
        }
        return trimJavaDotLangDot(buffer.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        return toString().equals(obj.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append(returnParam.getType());
        buffer.append(" ");
        buffer.append(name);
        buffer.append("(");
        final Iterator<TemplateParam> itParam = params.iterator();
        while (itParam.hasNext()) {
            final TemplateParam param = itParam.next();
            buffer.append(param.getType());
            if (itParam.hasNext())
                buffer.append(", ");
        }
        buffer.append(")");
        return buffer.toString();
    }

    public static final String trimJavaDotLangDot(String fqcn) {
        return fqcn.replace(java_dot_lang_dot, EMPTY);
    }
}
