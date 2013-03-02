package org.mdl4ui.gwt.model.client.renderer;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.component.PasswordField;
import org.mdl4ui.gwt.model.client.widget.PasswordTextBox;

public class PasswordFieldRenderer extends BaseFieldRenderer<PasswordField> {

    @Override
    protected PasswordField createFieldComponent(FieldID fieldID) {
        return new PasswordTextBox();
    }
}
