/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.model;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.event.FieldEvent;

public interface FieldBehaviour {

    boolean isVisible(FieldID fieldId, WizardContext context, FieldEvent fieldEvent);

    void updateValue(Field field, WizardContext context, FieldEvent event);
}
