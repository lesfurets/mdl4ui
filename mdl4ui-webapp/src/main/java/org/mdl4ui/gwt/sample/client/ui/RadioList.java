/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.gwt.sample.client.ui;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.mdl4ui.fields.model.component.RadioListField;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;

public class RadioList extends Composite implements RadioListField, HasClickHandlers, HasValueChangeHandlers<String> {

    private final String name;
    private final Panel panel;
    private final Map<RadioButton, String> radios = new LinkedHashMap<RadioButton, String>();

    private final ValueChangeHandler<Boolean> radioChangeHandler = new ValueChangeHandler<Boolean>() {

        @Override
        public void onValueChange(ValueChangeEvent<Boolean> event) {
            final RadioButton source = (RadioButton) event.getSource();
            if (source.getValue()) {
                ValueChangeEvent.fire(RadioList.this, getValue());
            }
        }
    };

    private final KeyUpHandler radioKeyHandler = new KeyUpHandler() {

        @Override
        public void onKeyUp(KeyUpEvent event) {
            switch (event.getNativeKeyCode()) {
                case KeyCodes.KEY_LEFT:
                case KeyCodes.KEY_RIGHT:
                case KeyCodes.KEY_UP:
                case KeyCodes.KEY_DOWN:
                    Scheduler.get().scheduleDeferred(new ScheduledCommand() {

                        @Override
                        public void execute() {
                            ValueChangeEvent.fire(RadioList.this, getValue());
                        }
                    });
                    break;
            }
        }
    };

    private int tabIndex;

    /**
     * @param name the group name with which to associate the radio button
     */
    public RadioList(String name) {
        super();
        this.name = name;
        panel = new FlowPanel();
        initWidget(panel);
    }

    private List<RadioButton> getRadioButtons() {
        return new ArrayList<RadioButton>(radios.keySet());
    }

    @Override
    public void addItem(String label, String value) {
        final RadioButton button = new RadioButton(name, label, true);
        button.getElement().setId(name + "_" + value.toUpperCase());
        button.addValueChangeHandler(radioChangeHandler);
        button.addKeyUpHandler(radioKeyHandler);
        button.setTabIndex(tabIndex);
        button.addStyleName("item-" + radios.size());
        radios.put(button, value);
        panel.add(button);
    }

    @Override
    public String getValue() {
        for (RadioButton button : radios.keySet()) {
            if (button.getValue().booleanValue()) {
                return radios.get(button);
            }
        }
        return null;
    }

    @Override
    public void setValue(String value) {
        changeSelectedValue(value, false);
    }

    private void changeSelectedValue(String value, boolean fireEvent) {
        for (RadioButton button : getRadioButtons()) {
            boolean selected = radios.get(button).equals(value);
            button.setValue(selected);
            if (fireEvent && selected) {
                ValueChangeEvent.fire(this, getValue());
            }
        }
    }

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
        HandlerRegistration registration = null;
        for (RadioButton radio : getRadioButtons()) {
            registration = radio.addClickHandler(handler);
        }
        return registration;
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }
}
