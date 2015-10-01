/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.base.apt;

import javax.annotation.processing.SupportedAnnotationTypes;

@SupportedAnnotationTypes(value = "org.mdl4ui.base.injection.InjectBehaviour")
public class J2seFieldBehaviourProcessor extends FieldBehaviourProcessor {
    @Override
    protected boolean isGwtFactory() {
        return false;
    }

    @Override
    public String simpleClassName(String... prefix) {
        return "MockFieldBehaviourFactory";
    }
}
