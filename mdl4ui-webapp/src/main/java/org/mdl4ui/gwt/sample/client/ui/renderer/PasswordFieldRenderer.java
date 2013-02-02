package org.mdl4ui.gwt.sample.client.ui.renderer;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.component.PasswordField;
import org.mdl4ui.gwt.sample.client.ui.PasswordTextBox;

public class PasswordFieldRenderer extends BaseFieldRenderer<PasswordField> {

    @Override
    protected PasswordField createFieldComponent(FieldID fieldID) {
        return new PasswordTextBox();
    }
}
