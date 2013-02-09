/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.ez18n.apt.processor;

import static javax.lang.model.SourceVersion.RELEASE_6;

import java.lang.annotation.Annotation;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.element.TypeElement;

import org.ez18n.runtime.Mobile;

@SupportedAnnotationTypes(value = "org.ez18n.MessageBundle")
@SupportedSourceVersion(RELEASE_6)
public class TestMobileBundleProcessor extends TestBundleProcessor {

	@Override
	protected String getBundleClassName(TypeElement typeElement, boolean fqcn) {
		final String simpleName = typeElement.getSimpleName().toString();
		final int resourceIndex = simpleName.indexOf("Resources");
		final String shortName = resourceIndex > 0 ? simpleName.substring(0, resourceIndex) : simpleName;
		return (fqcn ? typeElement.getEnclosingElement().toString() + "." : "") + shortName + "MobileBundle";
	}
	
	@Override
	protected Class<? extends Annotation> getAnnotation() {
		return Mobile.class;
	}

}
