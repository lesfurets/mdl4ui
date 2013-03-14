package org.mdl4ui.fields.sample.editor;

import java.util.Date;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.component.DateField;
import org.mdl4ui.fields.model.event.FieldEvent;
import org.mdl4ui.fields.model.validation.FieldValidation;
import org.mdl4ui.fields.sample.InjectSampleEditor;
import org.mdl4ui.fields.sample.OnField;
import org.mdl4ui.fields.sample.context.SampleContext;
import org.mdl4ui.fields.sample.i18n.ValidationMessages;
import org.mdl4ui.ui.sample.EFieldSample;

@InjectSampleEditor(@OnField(EFieldSample.BIRTHDATE))
public class BirthdateEditor extends SampleEditor {

    private ValidationMessages messages;

    public BirthdateEditor(ValidationMessages messages) {
        this.messages = messages;
    }

    @Override
    public String value(FieldID field, SampleContext context, FieldEvent fieldEvent) {
        Date birthDate = context.getPerson().getBirthDate();
        return birthDate != null ? birthDate.toString() : null;
    }

    @Override
    public void updateFromContext(Field field, SampleContext context, FieldEvent fieldEvent) {
        DateField dateField = field.getComponent();
        dateField.setValue(context.getPerson().getBirthDate());
    }

    @Override
    public void updateContext(Field field, SampleContext context, FieldEvent fieldEvent) {
        DateField dateField = field.getComponent();
        context.getPerson().setBirthDate(dateField.getValue());
    }

    @Override
    public FieldValidation validate(Field field, SampleContext context, FieldEvent fieldEvent) {
        DateField dateField = field.getComponent();
        if (dateField.isEmpty()) {
            error(field, messages.please_specify_your_birth_date());
        }
        return valid(field);
    }
}
