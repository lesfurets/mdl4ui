package org.mdl4ui.gwt.model.client.widget;

import java.util.HashMap;
import java.util.Map;

import org.mdl4ui.fields.model.component.RadioGroupField;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.constants.ToggleType;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;

public class RadioGroup extends com.github.gwtbootstrap.client.ui.ButtonGroup implements RadioGroupField,
                HasValueChangeHandlers<String> {

    private final Map<Button, String> buttonById = new HashMap<Button, String>();

    public RadioGroup(String group) {
        setToggle(ToggleType.RADIO);
    }

    @Override
    public boolean isEmpty() {
        return getValue() == null;
    }

    @Override
    public void addItem(String item, String value) {
        final Button button = new Button(item);
        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                    @Override
                    public void execute() {
                        ValueChangeEvent.fire(RadioGroup.this, RadioGroup.this.getValue());
                    }
                });
            }
        });
        add(button);
        buttonById.put(button, value);
    }

    @Override
    public String getValue() {
        for (Button button : buttonById.keySet()) {
            if (button.isToggled()) {
                return buttonById.get(button);
            }
        }
        return null;
    }

    @Override
    public void setValue(String value) {
        for (Button button : buttonById.keySet()) {
            if (buttonById.get(button).equals(value)) {
                button.setToggle(true);
            }
        }
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }

}
