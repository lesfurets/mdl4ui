/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.common.component;

public interface FieldComponent<T> {

    T getValue();

    void setValue(T value);
}
