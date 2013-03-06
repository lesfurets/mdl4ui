/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.model.event;

import org.mdl4ui.base.model.FieldID;

abstract class EventInterceptor {
    static void before(FieldEvent event, FieldID fieldId, EventKind kind) {
        if (event == null)
            return;
        event.setFieldId(fieldId);
        event.setKind(kind);
    }

    static void after(FieldEventMulticaster multicaster, FieldEvent event) {
        if (multicaster != null)
            multicaster.notify(event);
    }
}
