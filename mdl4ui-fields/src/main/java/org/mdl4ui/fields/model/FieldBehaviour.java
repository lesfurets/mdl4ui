/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.model;

import org.mdl4ui.base.model.FieldID;

public interface FieldBehaviour {

    boolean isVisible(FieldID fieldId, WizardContext context);

    void updateValue(Field field, WizardContext context);
}
