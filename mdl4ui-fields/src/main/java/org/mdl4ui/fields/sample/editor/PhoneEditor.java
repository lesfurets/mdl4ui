package org.mdl4ui.fields.sample.editor;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.component.TextBoxField;
import org.mdl4ui.fields.model.event.FieldEvent;
import org.mdl4ui.fields.model.validation.FieldValidation;
import org.mdl4ui.fields.sample.InjectSampleEditor;
import org.mdl4ui.fields.sample.OnField;
import org.mdl4ui.fields.sample.context.SampleContext;
import org.mdl4ui.fields.sample.i18n.ValidationMessages;
import org.mdl4ui.ui.sample.EFieldSample;

@InjectSampleEditor(@OnField(EFieldSample.PHONE_NUMBER))
public class PhoneEditor extends SampleEditor {

    private static final String PHONE_PATTERN = "^(01|02|03|04|05|06|07|08|09)[0-9]{8}";

    private ValidationMessages messages;

    public PhoneEditor(ValidationMessages messages) {
        this.messages = messages;
    }

    @Override
    public String value(FieldID field, SampleContext context, FieldEvent fieldEvent) {
        return context.getUserAccount().getPhoneNumber();
    }

    @Override
    public void updateFromContext(Field field, SampleContext context, FieldEvent fieldEvent) {
        TextBoxField textbox = field.getComponent();
        textbox.setValue(context.getUserAccount().getPhoneNumber());
    }

    @Override
    public void updateContext(Field field, SampleContext context, FieldEvent fieldEvent) {
        TextBoxField textbox = field.getComponent();
        context.getUserAccount().setPhoneNumber(textbox.getValue());
    }

    @Override
    public FieldValidation validate(Field field, SampleContext context, FieldEvent fieldEvent) {
        TextBoxField textbox = field.getComponent();
        if (textbox.isEmpty()) {
            return error(field, messages.please_specify_a_phone_number());
        } else if (!textbox.getValue().replace(" ", "").matches(PHONE_PATTERN)) {
            return error(field, messages.please_specify_a_valid_phone_number());
        }
        return valid(field);
    }
}
