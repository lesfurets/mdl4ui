/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.base.apt;

import static javax.lang.model.SourceVersion.RELEASE_6;

import java.lang.annotation.Annotation;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;

import org.mdl4ui.base.injection.InjectLabel;

@SupportedAnnotationTypes(value = "org.mdl4ui.base.injection.InjectLabel")
@SupportedSourceVersion(RELEASE_6)
public class FieldLabelProcessor extends I18nFieldProcessor {

    @Override
    protected Class<? extends Annotation> getAnnotationClass() {
        return InjectLabel.class;
    }

    @Override
    protected String getTemplate() {
        return "FieldLabelFactory.template";
    }

    @Override
    public String simpleClassName(String... prefix) {
        return prefix[0] + "FieldLabelFactory";
    }
}
