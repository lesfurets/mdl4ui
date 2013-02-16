package org.mdl4ui.fields.sample.editor;

import org.mdl4ui.fields.model.DefaultEditor;
import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.WizardContext;
import org.mdl4ui.fields.model.component.RadioGroupField;
import org.mdl4ui.fields.model.validation.FieldValidation;
import org.mdl4ui.fields.sample.InjectSampleEditor;
import org.mdl4ui.fields.sample.OnField;
import org.mdl4ui.fields.sample.context.Language;
import org.mdl4ui.fields.sample.context.SampleContext;
import org.mdl4ui.fields.sample.i18n.ValidationMessages;
import org.mdl4ui.ui.sample.EFieldSample;

@InjectSampleEditor(@OnField(EFieldSample.LANGUAGE))
public class LanguageEditor extends DefaultEditor {
    private ValidationMessages messages;

    public LanguageEditor(ValidationMessages messages) {
        this.messages = messages;
    }

    @Override
    public void updateFromContext(Field field, WizardContext context) {
        SampleContext sampleContext = (SampleContext) context;
        Language language = sampleContext.getUserAccount().getLanguage();
        if (language != null) {
            RadioGroupField radio = field.getComponent();
            radio.setValue(language.name());
        }
    }

    @Override
    public void updateContext(Field field, WizardContext context) {
        SampleContext sampleContext = (SampleContext) context;
        RadioGroupField radio = field.getComponent();
        if (radio.getValue() != null) {
            sampleContext.getUserAccount().setLanguage(Language.valueOf(radio.getValue()));
        }
    }

    @Override
    public FieldValidation validate(Field field, WizardContext context) {
        RadioGroupField radio = field.getComponent();
        if (radio.getValue() == null) {
            return error(field, messages.please_specify_your_language());
        }
        return valid(field);
    }
}
