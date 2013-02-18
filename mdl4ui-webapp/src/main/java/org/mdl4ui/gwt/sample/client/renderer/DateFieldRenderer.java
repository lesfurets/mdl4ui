package org.mdl4ui.gwt.sample.client.renderer;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.component.DateField;
import org.mdl4ui.gwt.sample.client.ui.DateBox;

public class DateFieldRenderer extends BaseFieldRenderer<DateField> {

    @Override
    protected DateField createFieldComponent(FieldID fieldID) {
        return new DateBox();
    }
}
