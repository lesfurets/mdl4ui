/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.ez18n.apt.processor;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;

@SupportedAnnotationTypes(value = "org.ez18n.MessageBundle")
public class TestDesktopBundleProcessor extends TestBundleProcessor {

    @Override
    protected String getBundleClassName(TypeElement typeElement, boolean fqcn) {
        final String simpleName = typeElement.getSimpleName().toString();
        final int resourceIndex = simpleName.indexOf("Resources");
        final String shortName = resourceIndex > 0 ? simpleName.substring(0, resourceIndex) : simpleName;
        return (fqcn ? typeElement.getEnclosingElement().toString() + "." : "") + shortName + "DesktopBundle";
    }

    @Override
    protected String getBundleType() {
        return "Desktop";
    }
}
