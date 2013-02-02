package org.mdl4ui.gwt.sample.client.ui.renderer;

import org.mdl4ui.fields.model.FieldRenderer;
import org.mdl4ui.fields.model.component.ListBoxField;
import org.mdl4ui.gwt.sample.client.ui.ListBox;

public class ListBoxFieldRenderer implements FieldRenderer<ListBoxField> {

    @Override
    public ListBoxField getFieldComponent() {
        return new ListBox();
    }
}
