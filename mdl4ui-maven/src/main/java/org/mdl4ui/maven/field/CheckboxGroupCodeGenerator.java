/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.maven.field;

public class CheckboxGroupCodeGenerator extends DefaultFieldCodeGenerator {

    @Override
    protected String getSetterTemplate() {
        return "FieldCheckboxGroupSetter.template";
    }
}
