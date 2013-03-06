/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.model.event;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.FieldBehaviour;
import org.mdl4ui.fields.model.FieldBehaviourFactory;
import org.mdl4ui.fields.model.WizardContext;

public final class FieldBehaviourInterceptor extends EventInterceptor implements FieldBehaviour {
    private FieldBehaviourFactory factory;
    private final FieldBehaviour delegate;

    public FieldBehaviourInterceptor(FieldBehaviour delegate) {
        super();
        this.delegate = delegate;
    }

    public FieldBehaviour getDelegate() {
        return delegate;
    }

    public void setFactory(FieldBehaviourFactory factory) {
        this.factory = factory;
    }

    @Override
    public boolean isVisible(FieldID fieldId, WizardContext context, FieldEvent event) {
        before(event, fieldId, EventKind.IS_VISIBLE);
        try {
            return delegate.isVisible(fieldId, context, event);
        } finally {
            after(factory, event);
        }
    }

    @Override
    public void updateValue(Field field, WizardContext context, FieldEvent event) {
        before(event, field.getFieldID(), EventKind.UPDATE_VALUE);
        try {
            delegate.updateValue(field, context, event);
        } finally {
            after(factory, event);
        }
    }
}
