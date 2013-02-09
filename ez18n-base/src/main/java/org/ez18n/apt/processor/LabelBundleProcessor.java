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

import static java.lang.Character.isUpperCase;
import static javax.tools.StandardLocation.SOURCE_OUTPUT;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic.Kind;
import javax.tools.FileObject;

import org.ez18n.Message;
import org.ez18n.MessageBundle;
import org.ez18n.apt.LabelTemplateMethod;
import org.ez18n.apt.base.EGenerationType;
import org.ez18n.apt.base.TemplateAnnotation;
import org.ez18n.apt.base.TemplateAnnotationProcessor;
import org.ez18n.apt.base.TemplateParam;

abstract class LabelBundleProcessor extends TemplateAnnotationProcessor<LabelTemplateMethod> {
	static final String NO_VALUE = "#no_value#";
	static final String NO_MESSAGE = "###no_message###";

	private final EGenerationType generationType;

	protected LabelBundleProcessor() {
		this(EGenerationType.SOURCE);
	}

	protected LabelBundleProcessor(EGenerationType generationType) {
		this.generationType = generationType;
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		if (roundEnv.processingOver()) {
			return true;
		}
		final List<LabelTemplateMethod> methods = new ArrayList<LabelTemplateMethod>();
		for (Element element : roundEnv.getElementsAnnotatedWith(MessageBundle.class)) {
			if (element.getKind() != ElementKind.INTERFACE) {
				continue;
			}
			final TypeElement bundleType = (TypeElement) element;
			processLabels(bundleType, methods);
			try {

				FileObject file = null;
				switch (generationType) {
				case SOURCE:
					file = processingEnv.getFiler().createSourceFile(getTargetClassName(bundleType), bundleType);
					break;

				case RESSOURCE:
					file = processingEnv.getFiler().createResource(SOURCE_OUTPUT,
							bundleType.getEnclosingElement().toString(),
							getTargetSimpleName(bundleType) + ".properties");
					break;
				}

				final Writer writer = file.openWriter();
				writer.write(getCode(bundleType, methods));
				writer.close();
			} catch (IOException e) {
				processingEnv.getMessager().printMessage(Kind.ERROR, e.getMessage(), bundleType);
			} finally {
				methods.clear();
			}
		}
		return false;
	}

	protected abstract String getCode(TypeElement bundleType, List<LabelTemplateMethod> methods);

	private void processLabels(TypeElement resourcesType, List<LabelTemplateMethod> methods) {
		for (Element element : resourcesType.getEnclosedElements()) {
			if (element.getKind() != ElementKind.METHOD) {
				continue;
			}
			final ExecutableElement method = (ExecutableElement) element;
			Message labelAnnotation = element.getAnnotation(Message.class);
			final TemplateParam returnType = new TemplateParam(method.getReturnType().toString());
			final boolean deprecated = method.getAnnotation(Deprecated.class) != null;
			final LabelTemplateMethod labelMethod;
			try {
				labelMethod = new LabelTemplateMethod(method.getSimpleName().toString(), deprecated, returnType,
						labelAnnotation.value(), labelAnnotation.mobile());
			} catch (Throwable t) {
				processingEnv.getMessager().printMessage(Kind.WARNING, t.getMessage(), resourcesType);
				continue;
			}

			for (VariableElement variable : method.getParameters()) {
				final String paramName = variable.getSimpleName().toString();
				final String paramType = variable.asType().toString();

				List<TemplateAnnotation> annotations = new ArrayList<TemplateAnnotation>();
				for (AnnotationMirror annotationMirrors : variable.getAnnotationMirrors()) {
					annotations.add(new TemplateAnnotation(annotationMirrors.getAnnotationType().toString()));
				}

				labelMethod.getParams().add(new TemplateParam(paramType, paramName, annotations));
			}
			methods.add(labelMethod);
		}
	}

	protected void checkTemplateMethod(TypeElement bundleType, LabelTemplateMethod method) {
		if (isEmpty(method.getMobile())) {
			processingEnv.getMessager().printMessage(Kind.WARNING, "mobile message is empty", bundleType);
		}

		if (method.getBase().equals(method.getMobile(false))) {
			processingEnv.getMessager()
					.printMessage(Kind.WARNING,
							method.getName() + ": duplicate mobile & desktop messages, mobile value can be removed",
							bundleType);
		}
	}

	protected static String toCamelCase(TypeElement typeElement) {
		return toCamelCase(typeElement.getSimpleName().toString());
	}

	protected static String toCamelCase(String string) {
		final StringBuffer result = new StringBuffer();
		for (int i = 0; i < string.length(); i++) {
			final char charAt = string.charAt(i);
			if (isUpperCase(charAt)) {
				result.append(charAt);
			}
		}
		return result.toString();
	}

}
