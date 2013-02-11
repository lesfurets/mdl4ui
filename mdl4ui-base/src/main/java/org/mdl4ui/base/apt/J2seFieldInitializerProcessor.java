/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.base.apt;

import static javax.lang.model.SourceVersion.RELEASE_6;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;

@SupportedAnnotationTypes(value = "org.mdl4ui.base.injection.InjectInit")
@SupportedSourceVersion(RELEASE_6)
public class J2seFieldInitializerProcessor extends FieldInitializerProcessor {
    @Override
    protected boolean isGwtFactory() {
        return false;
    }

    @Override
    public String simpleClassName(String... prefix) {
        return "MockFieldInitializerFactory";
    }
}
