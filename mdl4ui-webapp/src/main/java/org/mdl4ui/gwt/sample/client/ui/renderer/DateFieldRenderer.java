package org.mdl4ui.gwt.sample.client.ui.renderer;

import org.mdl4ui.fields.model.FieldRenderer;
import org.mdl4ui.fields.model.component.DateField;
import org.mdl4ui.gwt.sample.client.ui.DateBox;

public class DateFieldRenderer implements FieldRenderer<DateField> {

    @Override
    public DateField getFieldComponent() {
        return new DateBox();
    }
}
