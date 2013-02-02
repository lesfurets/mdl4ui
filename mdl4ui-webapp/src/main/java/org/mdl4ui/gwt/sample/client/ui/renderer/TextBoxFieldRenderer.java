package org.mdl4ui.gwt.sample.client.ui.renderer;

import org.mdl4ui.fields.model.FieldRenderer;
import org.mdl4ui.fields.model.component.TextBoxField;
import org.mdl4ui.gwt.sample.client.ui.TextBox;

public class TextBoxFieldRenderer implements FieldRenderer<TextBoxField> {

    @Override
    public TextBoxField getFieldComponent() {
        return new TextBox();
    }
}
