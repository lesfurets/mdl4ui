/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.model;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.event.FieldEvent;

public abstract class DefaultBehaviour implements FieldBehaviour {

    @Override
    public boolean isVisible(FieldID fieldId, WizardContext context, FieldEvent fieldEvent) {
        return true;
    }

    @Override
    public void updateValue(Field field, WizardContext context, FieldEvent event) {
    }
}
