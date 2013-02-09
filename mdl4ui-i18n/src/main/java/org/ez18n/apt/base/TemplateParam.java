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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class TemplateParam {
    private final String type;
    private final String name;
    private final List<TemplateAnnotation> annotations;

    public TemplateParam(String type) {
        this(type, null);
    }

    public TemplateParam(String type, String name) {
        this(type, name, new ArrayList<TemplateAnnotation>());
    }

    public TemplateParam(String type, String name, List<TemplateAnnotation> annotations) {
        this.type = type;
        this.name = name;
        this.annotations = annotations;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public List<TemplateAnnotation> getAnnotations() {
        return annotations;
    }

    // caracteres pouvant preceder et suivre un paramatre formel a substituer
    private static final Set<Character> sep = Collections.unmodifiableSet(new HashSet<Character>() {
        private static final long serialVersionUID = -8219898996883791759L;
        {
            add('<');
            add('>');
            add(' ');
            add(',');
            add('(');
            add(')');
        }
    });

    public static String replaceGenericParams(Map<String, String> genericParams, String rawType) {
        // cas trivial que l'on elimine pour ne pas plus complexifier ce qui suit...
        if (genericParams.containsKey(rawType)) {
            return genericParams.get(rawType);
        }
        // le vrai travail...
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int startCandidate = 0;
        int endCandidate = 0;
        while (i < rawType.length()) {
            final char c = rawType.charAt(i);
            if (sep.contains(c)) {
                endCandidate = i;
                String candidate = rawType.substring(startCandidate, endCandidate);
                if (genericParams.containsKey(candidate)) {
                    // subsitution d'un parametre formel
                    sb.append(genericParams.get(candidate));
                } else {
                    // pas de substitution possible
                    sb.append(candidate);
                }
                sb.append(c);
                startCandidate = endCandidate + 1;
            }
            i++;
        }
        if (!sep.contains(rawType.charAt(i - 1))) {
            sb.append(rawType.substring(startCandidate));
        }
        return sb.toString();
    }
}
