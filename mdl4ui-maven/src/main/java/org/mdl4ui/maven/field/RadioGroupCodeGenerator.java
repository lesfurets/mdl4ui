/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.maven.field;

public class RadioGroupCodeGenerator extends DefaultFieldCodeGenerator {

    @Override
    protected String getSetterTemplate() {
        return "FieldRadioGroupSetter.template";
    }
}
