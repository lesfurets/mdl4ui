package org.mdl4ui.gwt.model.client.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mdl4ui.fields.model.component.CheckBoxGroupField;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.constants.ToggleType;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
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
    public boolean isEmpty() {
        return getValue() == null || getValue().isEmpty();
    }

    @Override
    public void addItem(String item, String value) {
        final Button button = new Button(item);
        button.addMouseUpHandler(new MouseUpHandler() {
            @Override
            public void onMouseUp(MouseUpEvent event) {
                Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                    @Override
                    public void execute() {
                        ValueChangeEvent.fire(CheckBoxGroup.this, CheckBoxGroup.this.getValue());
                    }
                });
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
