package org.mdl4ui.gwt.sample.client.renderer;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.component.ListBoxField;
import org.mdl4ui.gwt.sample.client.ui.ListBox;

public class ListBoxFieldRenderer extends BaseFieldRenderer<ListBoxField> {

    @Override
    protected ListBoxField createFieldComponent(FieldID fieldID) {
        return new ListBox();
    }
}
