package org.mdl4ui.gwt.model.client.widget;

import org.mdl4ui.fields.model.component.ListBoxField;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;

public class ListBox extends com.github.gwtbootstrap.client.ui.ListBox implements ListBoxField,
                HasValueChangeHandlers<String> {

    public ListBox() {
        super();
        addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                ValueChangeEvent.fire(ListBox.this, ListBox.this.getValue());
            }
        });
    }

    @Override
    public String getValue() {
        return getValue(getSelectedIndex());
    }

    @Override
    public void setValue(String value) {
        setSelectedValue(value);
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }

}
