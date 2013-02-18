package org.mdl4ui.gwt.sample.client.renderer;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.component.TextBoxField;
import org.mdl4ui.gwt.sample.client.ui.TextBox;

public class TextBoxFieldRenderer extends BaseFieldRenderer<TextBoxField> {

    @Override
    protected TextBoxField createFieldComponent(FieldID fieldID) {
        return new TextBox();
    }
}
 