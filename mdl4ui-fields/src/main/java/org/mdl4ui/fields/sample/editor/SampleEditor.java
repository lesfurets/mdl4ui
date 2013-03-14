package org.mdl4ui.fields.sample.editor;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.DefaultEditor;
import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.WizardContext;
import org.mdl4ui.fields.model.event.FieldEvent;
import org.mdl4ui.fields.model.validation.FieldValidation;
import org.mdl4ui.fields.sample.context.SampleContext;

public abstract class SampleEditor extends DefaultEditor {

    @Override
    public final String value(FieldID field, WizardContext context, FieldEvent fieldEvent) {
        SampleContext sampleContext = (SampleContext) context;
        return value(field, sampleContext, fieldEvent);
    }

    protected abstract String value(FieldID field, SampleContext context, FieldEvent fieldEvent);

    @Override
    public final void updateFromContext(Field field, WizardContext context, FieldEvent fieldEvent) {
        SampleContext sampleContext = (SampleContext) context;
        updateFromContext(field, sampleContext, fieldEvent);
    }

    protected abstract void updateFromContext(Field field, SampleContext context, FieldEvent fieldEvent);

    @Override
    public final void updateContext(Field field, WizardContext context, FieldEvent fieldEvent) {
        SampleContext sampleContext = (SampleContext) context;
        updateContext(field, sampleContext, fieldEvent);
    }

    protected abstract void updateContext(Field field, SampleContext context, FieldEvent fieldEvent);

    @Override
    public final FieldValidation validate(Field field, WizardContext context, FieldEvent fieldEvent) {
        SampleContext sampleContext = (SampleContext) context;
        return validate(field, sampleContext, fieldEvent);
    }

    protected abstract FieldValidation validate(Field field, SampleContext context, FieldEvent fieldEvent);

}
