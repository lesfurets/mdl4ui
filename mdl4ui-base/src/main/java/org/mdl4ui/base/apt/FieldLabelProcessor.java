/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.base.apt;

import java.lang.annotation.Annotation;

import javax.annotation.processing.SupportedAnnotationTypes;

import org.mdl4ui.base.injection.InjectLabel;

@SupportedAnnotationTypes(value = "org.mdl4ui.base.injection.InjectLabel")
public class FieldLabelProcessor extends FieldMessageProcessor {

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
