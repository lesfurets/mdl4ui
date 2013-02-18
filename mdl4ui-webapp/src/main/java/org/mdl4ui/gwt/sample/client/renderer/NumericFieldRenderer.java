package org.mdl4ui.gwt.sample.client.renderer;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.component.NumericField;
import org.mdl4ui.gwt.sample.client.ui.IntegerBox;

public class NumericFieldRenderer extends BaseFieldRenderer<NumericField> {

    @Override
    protected NumericField createFieldComponent(FieldID fieldID) {
        return new IntegerBox();
    }
}
