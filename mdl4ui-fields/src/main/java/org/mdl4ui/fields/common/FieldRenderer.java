/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.common;

import org.mdl4ui.fields.common.component.FieldComponent;

public interface FieldRenderer<T, C extends FieldComponent<T>> {

    C getFieldComponent();

    void setLabel(String html);

}
