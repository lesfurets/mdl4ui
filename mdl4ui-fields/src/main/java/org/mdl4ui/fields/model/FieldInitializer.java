/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.model;

import org.mdl4ui.fields.model.event.FieldEvent;

public interface FieldInitializer {

    void init(Field field, FieldEvent event);

}
