package org.mdl4ui.fields.sample.editor;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.component.RadioGroupField;
import org.mdl4ui.fields.model.event.FieldEvent;
import org.mdl4ui.fields.model.validation.FieldValidation;
import org.mdl4ui.fields.sample.InjectSampleEditor;
import org.mdl4ui.fields.sample.OnField;
import org.mdl4ui.fields.sample.context.Language;
import org.mdl4ui.fields.sample.context.SampleContext;
import org.mdl4ui.fields.sample.i18n.ValidationMessages;
import org.mdl4ui.ui.sample.EFieldSample;

@InjectSampleEditor(@OnField(EFieldSample.LANGUAGE))
public class LanguageEditor extends SampleEditor {
    private ValidationMessages messages;

    public LanguageEditor(ValidationMessages messages) {
        this.messages = messages;
    }

    @Override
    public String value(FieldID field, SampleContext context, FieldEvent fieldEvent) {
        Language language = context.getUserAccount().getLanguage();
        return language != null ? language.name() : null;
    }

    @Override
    public void updateFromContext(Field field, SampleContext context, FieldEvent fieldEvent) {
        Language language = context.getUserAccount().getLanguage();
        if (language != null) {
            RadioGroupField radio = field.getComponent();
            radio.setValue(language.name());
        }
    }

    @Override
    public void updateContext(Field field, SampleContext context, FieldEvent fieldEvent) {
        RadioGroupField radio = field.getComponent();
        if (radio.getValue() != null) {
            context.getUserAccount().setLanguage(Language.valueOf(radio.getValue()));
        }
    }

    @Override
    public FieldValidation validate(Field field, SampleContext context, FieldEvent fieldEvent) {
        RadioGroupField radio = field.getComponent();
        if (radio.isEmpty()) {
            return error(field, messages.please_specify_your_language());
        }
        return valid(field);
    }
}
