package org.mdl4ui.gwt.sample.client.ui.renderer;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.component.ListBoxField;
import org.mdl4ui.gwt.sample.client.ui.ListBox;

public class ListBoxFieldRenderer extends BaseFieldRenderer<ListBoxField> {

    @Override
    protected ListBoxField createFieldComponent(FieldID fieldID) {
        final ListBox listBox = new ListBox();
        listBox.addItem("item1");
        listBox.addItem("item2");
        listBox.addItem("item3");
        return listBox;
    }
}
