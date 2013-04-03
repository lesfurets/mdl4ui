package org.mdl4ui.fields.sample.editor;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.component.PasswordField;
import org.mdl4ui.fields.model.event.FieldEvent;
import org.mdl4ui.fields.model.validation.FieldValidation;
import org.mdl4ui.fields.sample.InjectSampleEditor;
import org.mdl4ui.fields.sample.OnField;
import org.mdl4ui.fields.sample.context.SampleContext;
import org.mdl4ui.fields.sample.context.UserAccount;
import org.mdl4ui.fields.sample.i18n.ValidationMessages;
import org.mdl4ui.ui.sample.EFieldSample;

@InjectSampleEditor(@OnField(EFieldSample.PASSWORD_CONFIRMATION))
public class PasswordConfirmationEditor extends SampleEditor {
    private final ValidationMessages messages;

    public PasswordConfirmationEditor(ValidationMessages messages) {
        this.messages = messages;
    }

    @Override
    public String value(FieldID field, SampleContext context, FieldEvent fieldEvent) {
        return context.getUserAccount().getPasswordConfirmation();
    }

    @Override
    public void updateFromContext(Field field, SampleContext context, FieldEvent fieldEvent) {
        PasswordField textbox = field.getComponent();
        textbox.setValue(context.getUserAccount().getPasswordConfirmation());
    }

    @Override
    public void updateContext(Field field, SampleContext context, FieldEvent fieldEvent) {
        PasswordField textbox = field.getComponent();
        context.getUserAccount().setPasswordConfirmation(textbox.getValue());
    }

    @Override
    public FieldValidation validate(Field field, SampleContext context, FieldEvent fieldEvent) {
        UserAccount userAccount = context.getUserAccount();
        PasswordField textbox = field.getComponent();
        if (textbox.isEmpty()) {
            return error(field, messages.please_specify_your_password());
        }
        if (!userAccount.getPassword().equals(userAccount.getPasswordConfirmation())) {
            return error(field, messages.passwords_does_not_match());
        }
        return valid(field);
    }
}
