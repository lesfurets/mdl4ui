package org.mdl4ui.gwt.model.client.renderer;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.component.RadioGroupField;
import org.mdl4ui.gwt.model.client.widget.RadioGroup;

public class RadioGroupFieldRenderer extends BaseFieldRenderer<RadioGroupField> {

    @Override
    protected RadioGroupField createFieldComponent(FieldID fieldID) {
        return new RadioGroup(fieldID.toString());
    }
}
