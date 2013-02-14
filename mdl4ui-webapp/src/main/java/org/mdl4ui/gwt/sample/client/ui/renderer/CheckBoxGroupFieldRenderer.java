package org.mdl4ui.gwt.sample.client.ui.renderer;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.component.CheckBoxGroupField;
import org.mdl4ui.gwt.sample.client.ui.CheckBoxGroup;

public class CheckBoxGroupFieldRenderer extends BaseFieldRenderer<CheckBoxGroupField> {

    @Override
    protected CheckBoxGroupField createFieldComponent(FieldID fieldID) {
        return new CheckBoxGroup(fieldID.toString());
    }
}
