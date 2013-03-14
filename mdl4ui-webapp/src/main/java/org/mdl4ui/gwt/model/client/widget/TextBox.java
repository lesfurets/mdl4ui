package org.mdl4ui.gwt.model.client.widget;

import org.mdl4ui.fields.model.component.TextBoxField;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;

public class TextBox extends com.github.gwtbootstrap.client.ui.TextBox implements TextBoxField,
                HasValueChangeHandlers<String> {

    @Override
    public boolean isEmpty() {
        return getValue() == null || getValue().trim().length() == 0;
    }
}
