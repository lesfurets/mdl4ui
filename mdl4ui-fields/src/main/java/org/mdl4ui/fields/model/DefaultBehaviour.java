/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.model;

import org.mdl4ui.base.model.FieldID;

public class DefaultBehaviour implements FieldBehaviour {

    @Override
    public boolean isVisible(FieldID fieldId, WizardContext context) {
        return true;
    }

    @Override
    public void updateValue(Field field, WizardContext context) {
    }
}
