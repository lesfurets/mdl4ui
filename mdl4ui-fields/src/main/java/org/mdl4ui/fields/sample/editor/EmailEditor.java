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

@InjectSampleEditor(@OnField(EFieldSample.EMAIL))
public class EmailEditor extends SampleEditor {

    private static final String EMAIL_PATTERN = "[\\-!#\\$%&'*\\+/=\\?\\^_`\\{\\|\\}~A-Za-z0-9]+(\\.[\\-!#\\$%&'*\\+/=\\?\\^_`\\{\\|\\}\\~A-Za-z0-9]+)*@([\\-_A-Za-z0-9]+\\.)*[\\-_A-Za-z0-9]+\\.[\\-_A-Za-z0-9]{2,}";

    private final ValidationMessages messages;

    public EmailEditor(ValidationMessages messages) {
        this.messages = messages;
    }

    @Override
    public String value(FieldID field, SampleContext context, FieldEvent fieldEvent) {
        return context.getUserAccount().getEmail();
    }

    @Override
    public void updateFromContext(Field field, SampleContext context, FieldEvent fieldEvent) {
        TextBoxField textbox = field.getComponent();
        textbox.setValue(context.getUserAccount().getEmail());
    }

    @Override
    public void updateContext(Field field, SampleContext context, FieldEvent fieldEvent) {
        TextBoxField textbox = field.getComponent();
        context.getUserAccount().setEmail(textbox.getValue());
    }

    @Override
    public FieldValidation validate(Field field, SampleContext context, FieldEvent fieldEvent) {
        TextBoxField textbox = field.getComponent();
        if (textbox.isEmpty()) {
            return error(field, messages.please_specify_your_email());
        } else if (!textbox.getValue().matches(EMAIL_PATTERN)) {
            return error(field, messages.please_specify_a_valid_email());
        }
        return valid(field);
    }
}
