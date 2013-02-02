package org.mdl4ui.gwt.sample.client.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mdl4ui.fields.model.component.CheckBoxGroupField;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.constants.ToggleType;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;

public class CheckBoxGroup extends com.github.gwtbootstrap.client.ui.ButtonGroup implements CheckBoxGroupField,
                HasValueChangeHandlers<List<String>> {

    private final Map<Button, String> buttonById = new HashMap<Button, String>();

    public CheckBoxGroup(String group) {
        setToggle(ToggleType.CHECKBOX);
    }

    @Override
    public void addItem(String item, String value) {
        final Button button = new Button(item);
        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                ValueChangeEvent.fire(CheckBoxGroup.this, CheckBoxGroup.this.getValue());
            }
        });
        add(button);
        buttonById.put(button, value);
    }

    @Override
    public List<String> getValue() {
        List<String> value = new ArrayList<String>();
        for (Button button : buttonById.keySet()) {
            if (button.isToggled()) {
                value.add(buttonById.get(button));
            }
        }
        return value;
    }

    @Override
    public void setValue(List<String> value) {
        for (Button button : buttonById.keySet()) {
            button.setToggle(value.contains(buttonById.get(button)));
        }
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<List<String>> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }
}
