/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.base.apt;

import java.lang.annotation.Annotation;

import javax.annotation.processing.SupportedAnnotationTypes;

import org.mdl4ui.base.injection.InjectHelp;

@SupportedAnnotationTypes(value = "org.mdl4ui.base.injection.InjectHelp")
public class FieldHelpProcessor extends FieldMessageProcessor {

    @Override
    protected Class<? extends Annotation> getAnnotationClass() {
        return InjectHelp.class;
    }

    @Override
    protected String getTemplate() {
        return "FieldHelpFactory.template";
    }

    @Override
    public String simpleClassName(String... prefix) {
        return prefix[0] + "FieldHelpFactory";
    }
}
