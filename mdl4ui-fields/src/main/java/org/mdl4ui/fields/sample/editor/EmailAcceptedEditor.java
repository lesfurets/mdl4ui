package org.mdl4ui.fields.sample.editor;

import org.mdl4ui.fields.model.DefaultEditor;
import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.WizardContext;
import org.mdl4ui.fields.model.component.RadioGroupField;
import org.mdl4ui.fields.model.validation.FieldValidation;
import org.mdl4ui.fields.sample.InjectSampleEditor;
import org.mdl4ui.fields.sample.OnField;
import org.mdl4ui.fields.sample.context.SampleContext;
import org.mdl4ui.fields.sample.i18n.ValidationMessages;
import org.mdl4ui.ui.sample.EFieldSample;

@InjectSampleEditor(@OnField(EFieldSample.EMAIL_ACCEPTED))
public class EmailAcceptedEditor extends DefaultEditor {

    private ValidationMessages messages;

    public EmailAcceptedEditor(ValidationMessages messages) {
        this.messages = messages;
    }

    @Override
    public void updateFromContext(Field field, WizardContext context) {
        SampleContext sampleContext = (SampleContext) context;
        Boolean acceptEmail = sampleContext.getUserAccount().isAcceptEmail();
        if (acceptEmail != null) {
            RadioGroupField radio = field.getComponent();
            radio.setValue(acceptEmail.toString());
        }
    }

    @Override
    public void updateContext(Field field, WizardContext context) {
        SampleContext sampleContext = (SampleContext) context;
        RadioGroupField radio = field.getComponent();
        sampleContext.getUserAccount().setAcceptEmail(Boolean.valueOf(radio.getValue()));
    }

    @Override
    public FieldValidation validate(Field field, WizardContext context) {
        RadioGroupField radioGroup = field.getComponent();
        if (radioGroup.getValue() == null) {
            return error(field, messages.do_your_want_receive_email_from_on_service());
        }
        return valid(field);
    }
}
