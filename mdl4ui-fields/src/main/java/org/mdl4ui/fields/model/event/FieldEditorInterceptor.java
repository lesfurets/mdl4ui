/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.model.event;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.FieldEditor;
import org.mdl4ui.fields.model.FieldEditorFactory;
import org.mdl4ui.fields.model.WizardContext;
import org.mdl4ui.fields.model.validation.FieldValidation;

public final class FieldEditorInterceptor extends EventInterceptor implements FieldEditor {
    private FieldEditorFactory factory;
    private final FieldEditor delegate;

    public FieldEditorInterceptor(FieldEditor delegate) {
        super();
        this.delegate = delegate;
    }

    public FieldEditor getDelegate() {
        return delegate;
    }

    public void setFactory(FieldEditorFactory factory) {
        this.factory = factory;
    }

    @Override
    public String value(FieldID fieldID, WizardContext context, FieldEvent event) {
        return delegate.value(fieldID, context, event);
    }

    @Override
    public void updateFromContext(Field field, WizardContext context, FieldEvent event) {
        before(event, field.getFieldID(), EventKind.UPDATE_FROM_CONTEXT);
        try {
            delegate.updateFromContext(field, context, event);
        } finally {
            after(factory, event);
        }
    }

    @Override
    public void updateContext(Field field, WizardContext context, FieldEvent event) {
        final String initialValue = value(field.getFieldID(), context, event);
        try {
            before(event, field.getFieldID(), EventKind.UPDATE_CONTEXT);
            try {
                delegate.updateContext(field, context, event);
            } finally {
                after(factory, event);
            }
        } finally {
            notifyChangeValue(field, initialValue, context, event);
        }
    }

    @Override
    public void reset(Field field, WizardContext context, FieldEvent event) {
        final String initialValue = value(field.getFieldID(), context, event);
        try {
            before(event, field.getFieldID(), EventKind.RESET);
            try {
                delegate.reset(field, context, event);
            } finally {
                after(factory, event);
            }
        } finally {
            notifyChangeValue(field, initialValue, context, event);
        }
    }

    @Override
    public FieldValidation validate(Field field, WizardContext context, FieldEvent event) {
        before(event, field.getFieldID(), EventKind.VALIDATE);
        try {
            return delegate.validate(field, context, event);
        } finally {
            after(factory, event);
        }
    }

    private void notifyChangeValue(Field field, String initialValue, WizardContext context, FieldEvent event) {
        final String finalValue = value(field.getFieldID(), context, event);
        if (finalValue == null)
            return;
        if (!finalValue.equals(initialValue)) {
            event.setKind(EventKind.VALUE_CHANGED);
            event.setOldValue(initialValue);
            event.setNewValue(finalValue);
            after(factory, event);
            event.setOldValue(null);
            event.setNewValue(null);
        }
    }
}
