/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.base.apt;

import java.lang.annotation.Annotation;

import org.mdl4ui.base.injection.InjectInit;

public abstract class FieldInitializerProcessor extends ComponentsFieldProcessor {

    @Override
    protected Class<? extends Annotation> getAnnotationClass() {
        return InjectInit.class;
    }

    @Override
    protected String getEnumSupport() {
        return "EFieldInitializer";
    }

    @Override
    protected String getTemplate() {
        return "FieldInitializerFactory.template";
    }

    @Override
    protected String getMapName() {
        return "initializers";
    }
}
