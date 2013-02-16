package org.mdl4ui.fields.sample.editor;

import org.mdl4ui.fields.model.DefaultEditor;
import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.WizardContext;
import org.mdl4ui.fields.model.component.PasswordField;
import org.mdl4ui.fields.model.validation.FieldValidation;
import org.mdl4ui.fields.sample.InjectSampleEditor;
import org.mdl4ui.fields.sample.OnField;
import org.mdl4ui.fields.sample.context.SampleContext;
import org.mdl4ui.fields.sample.context.UserAccount;
import org.mdl4ui.fields.sample.i18n.ValidationMessages;
import org.mdl4ui.ui.sample.EFieldSample;

@InjectSampleEditor(@OnField(EFieldSample.PASSWORD_CONFIRMATION))
public class PasswordConfirmationEditor extends DefaultEditor {
    private ValidationMessages messages;

    public PasswordConfirmationEditor(ValidationMessages messages) {
        this.messages = messages;
    }

    @Override
    public void updateFromContext(Field field, WizardContext context) {
        SampleContext sampleContext = (SampleContext) context;
        PasswordField textbox = field.getComponent();
        textbox.setValue(sampleContext.getUserAccount().getPasswordConfirmation());
    }

    @Override
    public void updateContext(Field field, WizardContext context) {
        PasswordField textbox = field.getComponent();
        SampleContext sampleContext = (SampleContext) context;
        sampleContext.getUserAccount().setPasswordConfirmation(textbox.getValue());
    }

    @Override
    public FieldValidation validate(Field field, WizardContext context) {
        UserAccount userAccount = ((SampleContext) context).getUserAccount();
        PasswordField textbox = field.getComponent();
        String value = textbox.getValue();
        if (value == null || value.trim().length() == 0) {
            return error(field, messages.please_specify_your_password());
        }
        if (!userAccount.getPassword().equals(userAccount.getPasswordConfirmation())) {
            return error(field, messages.passwords_does_not_match());
        }
        return valid(field);
    }
}
