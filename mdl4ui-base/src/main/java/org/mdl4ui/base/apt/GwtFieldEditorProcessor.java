/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.base.apt;

import static javax.lang.model.SourceVersion.RELEASE_6;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;

@SupportedAnnotationTypes(value = "org.mdl4ui.base.injection.InjectEditor")
@SupportedSourceVersion(RELEASE_6)
public class GwtFieldEditorProcessor extends FieldEditorProcessor {
    @Override
    protected boolean isGwtFactory() {
        return true;
    }

    @Override
    public String simpleClassName(String... prefix) {
        return "GwtFieldEditorFactory";
    }
}
