/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.sample.initializer;

import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.FieldInitializer;
import org.mdl4ui.fields.model.component.RadioGroupField;
import org.mdl4ui.fields.sample.InjectSampleInit;
import org.mdl4ui.fields.sample.OnField;
import org.mdl4ui.fields.sample.context.EmailType;
import org.mdl4ui.fields.sample.i18n.FieldMessages;
import org.mdl4ui.ui.sample.EFieldSample;

@InjectSampleInit(@OnField(EFieldSample.EMAILS_PREFERENCES))
public class EmailPreferencesInitializer implements FieldInitializer {

    private FieldMessages messages;

    public EmailPreferencesInitializer(FieldMessages messages) {
        this.messages = messages;
    }

    @Override
    public void init(Field field) {
        RadioGroupField radiogroup = field.getComponent();
        radiogroup.addItem(EmailType.PRIVATE.name(), messages.privateMessage());
        radiogroup.addItem(EmailType.ADMINISTRATOR.name(), messages.administratorMessage());
        radiogroup.addItem(EmailType.NEWSLETTER.name(), messages.newsletter());
    }
}
