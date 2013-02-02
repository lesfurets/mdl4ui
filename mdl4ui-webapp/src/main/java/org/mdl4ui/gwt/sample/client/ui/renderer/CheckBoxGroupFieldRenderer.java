package org.mdl4ui.gwt.sample.client.ui.renderer;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.component.CheckBoxGroupField;
import org.mdl4ui.gwt.sample.client.ui.CheckBoxGroup;

public class CheckBoxGroupFieldRenderer extends BaseFieldRenderer<CheckBoxGroupField> {

    @Override
    protected CheckBoxGroupField createFieldComponent(FieldID fieldID) {
        final CheckBoxGroup checkBoxGroup = new CheckBoxGroup(fieldID.toString());
        checkBoxGroup.addItem("item1", "item1");
        checkBoxGroup.addItem("item2", "item2");
        checkBoxGroup.addItem("item3", "item3");
        return checkBoxGroup;
    }
}
