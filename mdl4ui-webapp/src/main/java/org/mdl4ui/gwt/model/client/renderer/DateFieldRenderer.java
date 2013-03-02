package org.mdl4ui.gwt.model.client.renderer;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.component.DateField;
import org.mdl4ui.gwt.model.client.widget.DateBox;

public class DateFieldRenderer extends BaseFieldRenderer<DateField> {

    @Override
    protected DateField createFieldComponent(FieldID fieldID) {
        return new DateBox();
    }
}
