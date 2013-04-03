package org.mdl4ui.fields.sample.editor;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.component.RadioGroupField;
import org.mdl4ui.fields.model.event.FieldEvent;
import org.mdl4ui.fields.model.validation.FieldValidation;
import org.mdl4ui.fields.sample.InjectSampleEditor;
import org.mdl4ui.fields.sample.OnField;
import org.mdl4ui.fields.sample.context.SampleContext;
import org.mdl4ui.fields.sample.i18n.ValidationMessages;
import org.mdl4ui.ui.sample.EFieldSample;

@InjectSampleEditor(@OnField(EFieldSample.EMAIL_ACCEPTED))
public class EmailAcceptedEditor extends SampleEditor {

    private final ValidationMessages messages;

    public EmailAcceptedEditor(ValidationMessages messages) {
        this.messages = messages;
    }

    @Override
    public String value(FieldID field, SampleContext context, FieldEvent fieldEvent) {
        Boolean acceptEmail = context.getUserAccount().isAcceptEmail();
        return acceptEmail != null ? Boolean.toString(acceptEmail) : null;
    }

    @Override
    public void updateFromContext(Field field, SampleContext context, FieldEvent fieldEvent) {
        Boolean acceptEmail = context.getUserAccount().isAcceptEmail();
        if (acceptEmail != null) {
            RadioGroupField radio = field.getComponent();
            radio.setValue(acceptEmail.toString());
        }
    }

    @Override
    public void updateContext(Field field, SampleContext context, FieldEvent fieldEvent) {
        RadioGroupField radio = field.getComponent();
        context.getUserAccount().setAcceptEmail(Boolean.valueOf(radio.getValue()));
    }

    @Override
    public FieldValidation validate(Field field, SampleContext context, FieldEvent fieldEvent) {
        RadioGroupField radioGroup = field.getComponent();
        if (radioGroup.isEmpty()) {
            return error(field, messages.do_your_want_receive_email_from_on_service());
        }
        return valid(field);
    }
}
