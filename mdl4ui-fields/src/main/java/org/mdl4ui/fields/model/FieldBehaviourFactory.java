/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.model;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.event.FieldEventMulticaster;

public interface FieldBehaviourFactory extends FieldEventMulticaster {

    FieldBehaviour get(FieldID fieldId);

}
