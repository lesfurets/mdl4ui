/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.model;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.event.FieldEvent;
import org.mdl4ui.fields.model.validation.EErrorType;
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
        return validation(field.getFieldID(), message, EErrorType.EMPTY_FIELD);
    }

    protected static final FieldValidation error(Field field, String message) {
        return validation(field.getFieldID(), message, EErrorType.INCONSISTENT_FIELD);
    }

    protected static final FieldValidation validation(FieldID field, String message, EErrorType errorType) {
        return new FieldValidation(field, message, errorType);
    }
}
