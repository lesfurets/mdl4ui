/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.maven.field;


public class ListboxCodeGenerator extends DefaultFieldCodeGenerator {

    @Override
    protected String getSetterTemplate() {
        return "FieldListboxSetter.template";
    }
}
