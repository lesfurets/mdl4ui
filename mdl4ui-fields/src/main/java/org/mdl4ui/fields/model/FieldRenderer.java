/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.model;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.component.FieldComponent;

public interface FieldRenderer<C extends FieldComponent<?>> {

    C getFieldComponent(FieldID fieldID);

    void setLabel(String label, FieldID fieldID);

    boolean isLabelInline();

}
