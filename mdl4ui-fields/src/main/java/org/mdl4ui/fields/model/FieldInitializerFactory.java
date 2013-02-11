/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.model;

import org.mdl4ui.base.model.FieldID;

public interface FieldInitializerFactory {

    FieldInitializer get(FieldID fieldId);

}
