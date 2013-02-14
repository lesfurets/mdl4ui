package org.mdl4ui.fields.sample.editor;

import org.mdl4ui.fields.model.DefaultEditor;
import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.WizardContext;
import org.mdl4ui.fields.model.component.RadioGroupField;
import org.mdl4ui.fields.sample.InjectSampleEditor;
import org.mdl4ui.fields.sample.OnField;
import org.mdl4ui.fields.sample.context.SampleContext;
import org.mdl4ui.ui.sample.EFieldSample;

@InjectSampleEditor(@OnField(EFieldSample.EMAIL_ACCEPTED))
public class EmailAcceptedEditor extends DefaultEditor {

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
}
