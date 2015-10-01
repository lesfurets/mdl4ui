/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.base.apt;

import javax.annotation.processing.SupportedAnnotationTypes;

@SupportedAnnotationTypes(value = "org.mdl4ui.base.injection.InjectInit")
public class GwtFieldInitializerProcessor extends FieldInitializerProcessor {
    @Override
    protected boolean isGwtFactory() {
        return true;
    }

    @Override
    public String simpleClassName(String... prefix) {
        return "GwtFieldInitializerFactory";
    }
}
