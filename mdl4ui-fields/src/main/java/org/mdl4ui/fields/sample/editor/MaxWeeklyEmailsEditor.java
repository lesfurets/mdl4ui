package org.mdl4ui.fields.sample.editor;

import org.mdl4ui.fields.model.DefaultEditor;
import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.WizardContext;
import org.mdl4ui.fields.model.component.NumericField;
import org.mdl4ui.fields.model.validation.FieldValidation;
import org.mdl4ui.fields.sample.InjectSampleEditor;
import org.mdl4ui.fields.sample.OnField;
import org.mdl4ui.fields.sample.context.SampleContext;
import org.mdl4ui.fields.sample.i18n.ValidationMessages;
import org.mdl4ui.ui.sample.EFieldSample;

@InjectSampleEditor(@OnField(EFieldSample.MAX_WEEKLY_EMAILS))
public class MaxWeeklyEmailsEditor extends DefaultEditor {
    private ValidationMessages messages;

    public MaxWeeklyEmailsEditor(ValidationMessages messages) {
        this.messages = messages;
    }

    @Override
    public void updateFromContext(Field field, WizardContext context) {
        SampleContext sampleContext = (SampleContext) context;
        NumericField texbox = field.getComponent();
        texbox.setValue(sampleContext.getUserAccount().getMaxEmailPerWeek());
    }

    @Override
    public void updateContext(Field field, WizardContext context) {
        NumericField texbox = field.getComponent();
        SampleContext sampleContext = (SampleContext) context;
        if (texbox.getValue() != null)
            sampleContext.getUserAccount().setMaxEmailPerWeek(texbox.getValue());
    }

    @Override
    public FieldValidation validate(Field field, WizardContext context) {
        NumericField texbox = field.getComponent();
        if (texbox.getValue() == null) {
            return error(field, messages.please_specify_the_email_frequency());
        }
        return valid(field);
    }
}
