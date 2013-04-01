/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.maven.field;


public class TextboxCodeGenerator extends DefaultFieldCodeGenerator {

    @Override
    protected String getSetterTemplate() {
        return "FieldTextboxSetter.template";
    }
}
