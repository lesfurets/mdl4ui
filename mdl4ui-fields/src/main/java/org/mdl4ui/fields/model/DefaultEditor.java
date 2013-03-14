/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.model;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.event.FieldEvent;
import org.mdl4ui.fields.model.validation.ErrorType;
import org.mdl4ui.fields.model.validation.FieldValidation;

public abstract class DefaultEditor implements FieldEditor {

    @Override
    public void updateFromContext(Field field, WizardContext context, FieldEvent fieldEvent) {
    }

    @Override
    public void updateContext(Field field, WizardContext context, FieldEvent fieldEvent) {
    }

    @Override
    public void reset(Field field, WizardContext context, FieldEvent fieldEvent) {
    }

    @Override
    public FieldValidation validate(Field field, WizardContext context, FieldEvent fieldEvent) {
        return valid(field);
    }

    public static final FieldValidation valid(Field field) {
        return new FieldValidation(field.getFieldID());
    }

    protected static final FieldValidation empty(Field field, String message) {
        return validation(field.getFieldID(), message, ErrorType.EMPTY_FIELD);
    }

    protected static final FieldValidation error(Field field, String message) {
        return validation(field.getFieldID(), message, ErrorType.INCONSISTENT_FIELD);
    }

    protected static final FieldValidation validation(FieldID field, String message, ErrorType errorType) {
        return new FieldValidation(field, message, errorType);
    }
}
