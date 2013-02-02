package org.mdl4ui.gwt.sample.client.ui.renderer;

import org.mdl4ui.fields.model.FieldRenderer;
import org.mdl4ui.fields.model.component.CheckBoxField;
import org.mdl4ui.gwt.sample.client.ui.CheckBox;

public class CheckBoxFieldRenderer implements FieldRenderer<CheckBoxField> {

    @Override
    public CheckBoxField getFieldComponent() {
        return new CheckBox();
    }
}
