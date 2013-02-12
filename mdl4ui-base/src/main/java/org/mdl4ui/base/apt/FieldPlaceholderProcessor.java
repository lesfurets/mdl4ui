/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.base.apt;

import static javax.lang.model.SourceVersion.RELEASE_6;

import java.lang.annotation.Annotation;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;

import org.mdl4ui.base.injection.InjectPlaceholder;

@SupportedAnnotationTypes(value = "org.mdl4ui.base.injection.InjectPlaceholder")
@SupportedSourceVersion(RELEASE_6)
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
