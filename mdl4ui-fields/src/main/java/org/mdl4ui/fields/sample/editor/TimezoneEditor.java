package org.mdl4ui.fields.sample.editor;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.component.ListBoxField;
import org.mdl4ui.fields.model.event.FieldEvent;
import org.mdl4ui.fields.model.validation.FieldValidation;
import org.mdl4ui.fields.sample.InjectSampleEditor;
import org.mdl4ui.fields.sample.OnField;
import org.mdl4ui.fields.sample.context.SampleContext;
import org.mdl4ui.fields.sample.context.Timezone;
import org.mdl4ui.fields.sample.i18n.ValidationMessages;
import org.mdl4ui.ui.sample.EFieldSample;

@InjectSampleEditor(@OnField(EFieldSample.TIMEZONE))
public class TimezoneEditor extends SampleEditor {
    private final ValidationMessages messages;

    public TimezoneEditor(ValidationMessages messages) {
        this.messages = messages;
    }

    @Override
    public String value(FieldID field, SampleContext context, FieldEvent fieldEvent) {
        Timezone timezone = context.getUserAccount().getTimezone();
        return timezone != null ? timezone.getCode() : null;
    }

    @Override
    public void updateFromContext(Field field, SampleContext context, FieldEvent fieldEvent) {
        ListBoxField listbox = field.getComponent();
        listbox.setValue(context.getUserAccount().getTimezone().getCode());
    }

    @Override
    public void updateContext(Field field, SampleContext context, FieldEvent fieldEvent) {
        ListBoxField listbox = field.getComponent();
        if (listbox.getValue() != null)
            context.getUserAccount().setTimezone(Timezone.fromCode(listbox.getValue()));
    }

    @Override
    public FieldValidation validate(Field field, SampleContext context, FieldEvent fieldEvent) {
        ListBoxField listbox = field.getComponent();
        if (listbox.isEmpty()) {
            return error(field, messages.please_specify_your_timezone());
        }
        return valid(field);
    }
}
