package org.mdl4ui.fields.sample.editor;

import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.FieldEditor;
import org.mdl4ui.fields.model.WizardContext;
import org.mdl4ui.fields.model.validation.FieldValidation;
import org.mdl4ui.fields.sample.InjectSampleEditor;
import org.mdl4ui.fields.sample.OnField;
import org.mdl4ui.ui.sample.EFieldSample;

@InjectSampleEditor(@OnField(EFieldSample.PASSWORD))
public class PasswordEditor implements FieldEditor {

    @Override
    public void updateFromContext(Field field, WizardContext context) {
    }

    @Override
    public void updateContext(Field field, WizardContext context) {
    }

    @Override
    public void reset(Field field, WizardContext context) {
    }

    @Override
    public FieldValidation validate(Field field, WizardContext context) {
        return null;
    }
}
