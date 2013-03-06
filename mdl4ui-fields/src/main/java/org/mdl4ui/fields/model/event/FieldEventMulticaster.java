/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.model.event;

public interface FieldEventMulticaster {

    void notify(FieldEvent event);

    void addListener(FieldEventListener listener);

    boolean removeListener(FieldEventListener listener);
}
