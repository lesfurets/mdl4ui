package org.mdl4ui.fields.sample.editor;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.component.NumericField;
import org.mdl4ui.fields.model.event.FieldEvent;
import org.mdl4ui.fields.model.validation.FieldValidation;
import org.mdl4ui.fields.sample.InjectSampleEditor;
import org.mdl4ui.fields.sample.OnField;
import org.mdl4ui.fields.sample.context.SampleContext;
import org.mdl4ui.fields.sample.i18n.ValidationMessages;
import org.mdl4ui.ui.sample.EFieldSample;

@InjectSampleEditor(@OnField(EFieldSample.MAX_EMAILS_PER_WEEK))
public class MaxWeeklyEmailsEditor extends SampleEditor {
    private final ValidationMessages messages;

    public MaxWeeklyEmailsEditor(ValidationMessages messages) {
        this.messages = messages;
    }

    @Override
    public String value(FieldID field, SampleContext context, FieldEvent fieldEvent) {
        int maxEmailPerWeek = context.getUserAccount().getMaxEmailPerWeek();
        return Integer.toString(maxEmailPerWeek);
    }

    @Override
    public void updateFromContext(Field field, SampleContext context, FieldEvent fieldEvent) {
        NumericField texbox = field.getComponent();
        texbox.setValue(context.getUserAccount().getMaxEmailPerWeek());
    }

    @Override
    public void updateContext(Field field, SampleContext context, FieldEvent fieldEvent) {
        NumericField texbox = field.getComponent();
        if (texbox.getValue() != null)
            context.getUserAccount().setMaxEmailPerWeek(texbox.getValue());
    }

    @Override
    public FieldValidation validate(Field field, SampleContext context, FieldEvent fieldEvent) {
        NumericField texbox = field.getComponent();
        if (texbox.isEmpty()) {
            return error(field, messages.please_specify_the_email_frequency());
        }
        return valid(field);
    }
}
