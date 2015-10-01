/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.base.apt;

import java.lang.annotation.Annotation;

import javax.annotation.processing.SupportedAnnotationTypes;

import org.mdl4ui.base.injection.InjectPlaceholder;

@SupportedAnnotationTypes(value = "org.mdl4ui.base.injection.InjectPlaceholder")
public class FieldPlaceholderProcessor extends FieldMessageProcessor {

    @Override
    protected Class<? extends Annotation> getAnnotationClass() {
        return InjectPlaceholder.class;
    }

    @Override
    protected String getTemplate() {
        return "FieldPlaceholderFactory.template";
    }

    @Override
    public String simpleClassName(String... prefix) {
        return prefix[0] + "FieldPlaceholderFactory";
    }
}
