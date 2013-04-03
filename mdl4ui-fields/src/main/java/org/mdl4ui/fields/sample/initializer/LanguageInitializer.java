/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.sample.initializer;

import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.FieldInitializer;
import org.mdl4ui.fields.model.component.RadioGroupField;
import org.mdl4ui.fields.model.event.FieldEvent;
import org.mdl4ui.fields.sample.InjectSampleInit;
import org.mdl4ui.fields.sample.OnField;
import org.mdl4ui.fields.sample.context.Language;
import org.mdl4ui.fields.sample.i18n.FieldMessages;
import org.mdl4ui.ui.sample.EFieldSample;

@InjectSampleInit(@OnField(EFieldSample.LANGUAGE))
public class LanguageInitializer implements FieldInitializer {

    private final FieldMessages messages;

    public LanguageInitializer(FieldMessages messages) {
        this.messages = messages;
    }

    @Override
    public void init(Field field, FieldEvent event) {
        RadioGroupField checkbox = field.getComponent();
        checkbox.addItem(messages.english(), Language.EN.name());
        checkbox.addItem(messages.francais(), Language.FR.name());
    }
}
