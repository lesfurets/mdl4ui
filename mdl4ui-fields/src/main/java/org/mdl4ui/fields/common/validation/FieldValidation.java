/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.common.validation;

import java.util.Collection;

import org.mdl4ui.base.model.FieldID;

public class FieldValidation {

    public static boolean valid(Collection<FieldValidation> validations) {
        boolean allValid = true;
        for (FieldValidation val : validations) {
            allValid &= val.isValid();
        }
        return allValid;
    }

    private final FieldID fieldId;
    private final String message;
    private final EErrorType errorType;

    public FieldValidation(FieldID fieldId) {
        this(fieldId, null);
    }

    public FieldValidation(FieldID fieldId, String message) {
        this(fieldId, null, EErrorType.EMPTY_FIELD);
    }

    public FieldValidation(FieldID fieldId, String message, EErrorType kind) {
        this.fieldId = fieldId;
        this.message = message;
        this.errorType = kind;
    }

    public FieldID getFieldId() {
        return this.fieldId;
    }

    public String getMessage() {
        return this.message;
    }

    public EErrorType getErrorType() {
        return this.errorType;
    }

    public boolean isValid() {
        return message == null;
    }
}
