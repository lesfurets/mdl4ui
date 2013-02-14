/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.sample.initializer;

import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.FieldInitializer;
import org.mdl4ui.fields.model.component.RadioGroupField;
import org.mdl4ui.fields.sample.InjectSampleInit;
import org.mdl4ui.fields.sample.OnField;
import org.mdl4ui.fields.sample.i18n.FieldMessages;
import org.mdl4ui.ui.sample.EFieldSample;

@InjectSampleInit(@OnField(EFieldSample.EMAIL_ACCEPTED))
public class EmailAcceptedInitializer implements FieldInitializer {

    private final FieldMessages messages;

    public EmailAcceptedInitializer(FieldMessages messages) {
        this.messages = messages;
    }

    @Override
    public void init(Field field) {
        RadioGroupField checkbox = field.getComponent();
        checkbox.addItem(messages.yes(), Boolean.TRUE.toString());
        checkbox.addItem(messages.no(), Boolean.FALSE.toString());
    }
}
