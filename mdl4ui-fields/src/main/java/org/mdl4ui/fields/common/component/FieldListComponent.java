/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.common.component;

import java.util.List;

public interface FieldListComponent<T> extends FieldComponent<T> {

    List<T> getValues();

    void setValues(List<T> value);
}
