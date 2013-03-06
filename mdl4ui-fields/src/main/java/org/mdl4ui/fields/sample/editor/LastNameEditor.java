package org.mdl4ui.fields.sample.editor;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.DefaultEditor;
import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.WizardContext;
import org.mdl4ui.fields.model.component.TextBoxField;
import org.mdl4ui.fields.model.event.FieldEvent;
import org.mdl4ui.fields.model.validation.FieldValidation;
import org.mdl4ui.fields.sample.InjectSampleEditor;
import org.mdl4ui.fields.sample.OnField;
import org.mdl4ui.fields.sample.context.SampleContext;
import org.mdl4ui.fields.sample.i18n.ValidationMessages;
import org.mdl4ui.ui.sample.EFieldSample;

@InjectSampleEditor(@OnField(EFieldSample.LAST_NAME))
public class LastNameEditor extends DefaultEditor {
    private ValidationMessages messages;

    public LastNameEditor(ValidationMessages messages) {
        this.messages = messages;
    }

    @Override
    public String value(FieldID field, WizardContext context, FieldEvent fieldEvent) {
        SampleContext sampleContext = (SampleContext) context;
        return sampleContext.getPerson().getLastName();
    }

    @Override
    public void updateFromContext(Field field, WizardContext context, FieldEvent fieldEvent) {
        SampleContext sampleContext = (SampleContext) context;
        TextBoxField textbox = field.getComponent();
        textbox.setValue(sampleContext.getPerson().getLastName());
    }

    @Override
    public void updateContext(Field field, WizardContext context, FieldEvent fieldEvent) {
        TextBoxField textbox = field.getComponent();
        SampleContext sampleContext = (SampleContext) context;
        sampleContext.getPerson().setLastName(textbox.getValue());
    }

    @Override
    public FieldValidation validate(Field field, WizardContext context, FieldEvent fieldEvent) {
        TextBoxField textbox = field.getComponent();
        String value = textbox.getValue();
        if (value == null || value.trim().length() == 0) {
            return error(field, messages.please_specify_your_last_name());
        }
        return valid(field);
    }
}
