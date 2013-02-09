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

import java.util.Map;

import javax.annotation.processing.AbstractProcessor;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;

public abstract class TemplateAnnotationProcessor<M extends TemplateMethod> extends AbstractProcessor {
    protected final String getTargetClassName(TypeElement typeElement) {
        return typeElement.getEnclosingElement().toString() + '.' + getTargetSimpleName(typeElement);
    }

    protected abstract String getTargetSimpleName(TypeElement typeElement);

    protected static final void collectGenerics(DeclaredType declaredType, TypeElement typeElement,
                    Map<String, String> genericParams) {
        for (int i = 0; i < Math.min(typeElement.getTypeParameters().size(), declaredType.getTypeArguments().size()); i++) {
            final String paramName = typeElement.getTypeParameters().get(i).toString();
            final String paramType = declaredType.getTypeArguments().get(i).toString();
            if (genericParams.containsKey(paramName))
                continue;
            genericParams.put(paramName, paramType);
        }
    }
}
