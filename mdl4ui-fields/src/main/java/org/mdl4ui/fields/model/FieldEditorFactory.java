/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.model;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.event.FieldEventMulticaster;

public interface FieldEditorFactory extends FieldEventMulticaster {

    FieldEditor get(FieldID fieldId);

}
