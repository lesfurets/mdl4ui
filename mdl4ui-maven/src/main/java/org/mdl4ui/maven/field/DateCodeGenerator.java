/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.maven.field;

public class DateCodeGenerator extends DefaultFieldCodeGenerator {

    @Override
    protected String getSetterTemplate() {
        return "FieldDateSetter.template";
    }

    @Override
    protected String getGetterTemplate() {
        return "FieldDateGetter.template";
    }
}
