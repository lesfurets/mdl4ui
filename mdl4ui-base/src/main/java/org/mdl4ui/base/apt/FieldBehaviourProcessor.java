/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.base.apt;

import java.lang.annotation.Annotation;

import org.mdl4ui.base.injection.InjectBehaviour;

public abstract class FieldBehaviourProcessor extends ComponentsFieldProcessor {

    @Override
    protected Class<? extends Annotation> getAnnotationClass() {
        return InjectBehaviour.class;
    }

    @Override
    protected String getEnumSupport() {
        return "EFieldBehaviour";
    }

    @Override
    protected String getTemplate() {
        return "FieldBehaviourFactory.template";
    }

    @Override
    protected String getMapName() {
        return "behaviours";
    }
}
