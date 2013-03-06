/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.model.event;

import java.util.ArrayList;
import java.util.List;

public final class DefaultFieldEventMulticaster implements FieldEventMulticaster {

    private final List<FieldEventListener> listeners = new ArrayList<FieldEventListener>();

    @Override
    public void notify(FieldEvent event) {
        for (FieldEventListener listener : listeners)
            listener.onEvent(event);
    }

    @Override
    public void addListener(FieldEventListener listener) {
        listeners.add(listener);
    }

    @Override
    public boolean removeListener(FieldEventListener listener) {
        return listeners.remove(listener);
    }
}
