/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.model.event;

import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.FieldInitializer;
import org.mdl4ui.fields.model.FieldInitializerFactory;

public final class FieldInitializerInterceptor extends EventInterceptor implements FieldInitializer {
    private FieldInitializerFactory factory;
    private final FieldInitializer delegate;

    public FieldInitializerInterceptor(FieldInitializer delegate) {
        this.delegate = delegate;
    }

    public FieldInitializer getDelegate() {
        return delegate;
    }

    public void setFactory(FieldInitializerFactory factory) {
        this.factory = factory;
    }

    @Override
    public void init(Field field, FieldEvent event) {
        before(event, field.getFieldID(), EventKind.INIT);
        try {
            delegate.init(field, event);
        } finally {
            after(factory, event);
        }
    }
}
