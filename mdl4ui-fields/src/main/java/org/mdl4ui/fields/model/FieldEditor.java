/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.model;

import org.mdl4ui.fields.model.validation.FieldValidation;

public interface FieldEditor {

    /**
     * @param field the {@link Field} to be update from the context
     * @param context the context to use
     */
    void updateFromContext(Field field, WizardContext context);

    /**
     * @param field the edited {@link Field}
     * @param context the context to update
     */
    void updateContext(Field field, WizardContext context);

    /**
     * reset the context if the field is not visible
     * 
     * @param field the {@link Field} to use
     * @param context the {@link WizardContext} to use
     */
    void reset(Field field, WizardContext context);

    /**
     * @param field the {@link Field} to validate
     * @param context the current context to validate
     * @return the validation message is not valid, <code>null</code> otherwise
     */
    FieldValidation validate(Field field, WizardContext context);
}
