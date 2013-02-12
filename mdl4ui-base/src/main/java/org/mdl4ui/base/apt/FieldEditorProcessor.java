/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.base.apt;

import java.lang.annotation.Annotation;

import org.mdl4ui.base.injection.InjectEditor;

public abstract class FieldEditorProcessor extends ComponentsFieldProcessor {

    @Override
    protected Class<? extends Annotation> getAnnotationClass() {
        return InjectEditor.class;
    }

    @Override
    protected String getEnumSupport() {
        return "EFieldEditor";
    }

    @Override
    protected String getTemplate() {
        return "FieldEditorFactory.template";
    }

    @Override
    protected String getMapName() {
        return "editors";
    }
}
