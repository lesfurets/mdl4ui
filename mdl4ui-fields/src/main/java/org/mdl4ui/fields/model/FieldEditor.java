/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.model;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.event.FieldEvent;
import org.mdl4ui.fields.model.validation.FieldValidation;

public interface FieldEditor {

    /**
     * @param field the {@link Field} to use
     * @param context the context to use
     * @param fieldEvent the {@link FieldEvent} to use
     * @return the field value
     */
    String value(FieldID field, WizardContext context, FieldEvent fieldEvent);

    /**
     * @param field the {@link Field} to be update from the context
     * @param context the context to use
     * @param fieldEvent the {@link FieldEvent} to use
     */
    void updateFromContext(Field field, WizardContext context, FieldEvent fieldEvent);

    /**
     * @param field the edited {@link Field}
     * @param context the context to update
     * @param fieldEvent the {@link FieldEvent} to use
     */
    void updateContext(Field field, WizardContext context, FieldEvent fieldEvent);

    /**
     * reset the context if the field is not visible
     * 
     * @param field the {@link Field} to use
     * @param context the {@link WizardContext} to use
     * @param fieldEvent the {@link FieldEvent} to use
     */
    void reset(Field field, WizardContext context, FieldEvent fieldEvent);

    /**
     * @param field the {@link Field} to validate
     * @param context the current context to validate
     * @param fieldEvent the {@link FieldEvent} to use
     * @return the validation message is not valid, <code>null</code> otherwise
     */
    FieldValidation validate(Field field, WizardContext context, FieldEvent fieldEvent);
}
