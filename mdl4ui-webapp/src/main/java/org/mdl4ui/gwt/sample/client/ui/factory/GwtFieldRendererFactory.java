package org.mdl4ui.gwt.sample.client.ui.factory;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.FieldRenderer;
import org.mdl4ui.fields.model.FieldRendererFactory;
import org.mdl4ui.gwt.sample.client.ui.renderer.CheckBoxGroupFieldRenderer;
import org.mdl4ui.gwt.sample.client.ui.renderer.DateFieldRenderer;
import org.mdl4ui.gwt.sample.client.ui.renderer.ListBoxFieldRenderer;
import org.mdl4ui.gwt.sample.client.ui.renderer.NumericFieldRenderer;
import org.mdl4ui.gwt.sample.client.ui.renderer.PasswordFieldRenderer;
import org.mdl4ui.gwt.sample.client.ui.renderer.RadioGroupFieldRenderer;
import org.mdl4ui.gwt.sample.client.ui.renderer.TextBoxFieldRenderer;

public class GwtFieldRendererFactory implements FieldRendererFactory {

    private GwtFieldRendererFactory() {
    }

    @Override
    public FieldRenderer<?> get(FieldID fieldID) {
        switch (fieldID.type()) {
            case TEXTBOX:
                return new TextBoxFieldRenderer();
            case PASSWORD:
                return new PasswordFieldRenderer();
            case NUMERIC:
                return new NumericFieldRenderer();
            case LISTBOX:
                return new ListBoxFieldRenderer();
            case RADIO_GROUP:
                return new RadioGroupFieldRenderer();
            case CHECKBOX_GROUP:
                return new CheckBoxGroupFieldRenderer();
            case DATE:
                return new DateFieldRenderer();
            default:
                throw new IllegalArgumentException(fieldID.type().toString());
        }
    }
}
