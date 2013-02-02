/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.model;

import org.mdl4ui.fields.model.component.FieldComponent;

public interface FieldRenderer<C extends FieldComponent<?>> {

    C getFieldComponent();

}
