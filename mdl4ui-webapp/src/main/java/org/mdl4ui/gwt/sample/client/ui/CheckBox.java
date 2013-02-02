package org.mdl4ui.gwt.sample.client.ui;

import org.mdl4ui.fields.model.component.CheckBoxField;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.i18n.shared.DirectionEstimator;

public class CheckBox extends com.github.gwtbootstrap.client.ui.CheckBox implements CheckBoxField,
                HasValueChangeHandlers<Boolean> {

    public CheckBox() {
        super();
    }

    public CheckBox(String label, boolean asHTML) {
        super(label, asHTML);
    }

    public CheckBox(String label, Direction dir) {
        super(label, dir);
    }

    public CheckBox(String label, DirectionEstimator directionEstimator) {
        super(label, directionEstimator);
    }

    public CheckBox(String label) {
        super(label);
    }
}
