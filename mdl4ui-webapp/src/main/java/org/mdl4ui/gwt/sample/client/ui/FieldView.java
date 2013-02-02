package org.mdl4ui.gwt.sample.client.ui;

import com.github.gwtbootstrap.client.ui.ControlGroup;
import com.github.gwtbootstrap.client.ui.ControlLabel;
import com.github.gwtbootstrap.client.ui.Controls;
import com.github.gwtbootstrap.client.ui.HelpInline;
import com.github.gwtbootstrap.client.ui.constants.ControlGroupType;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class FieldView implements IsWidget {

    private final ControlGroup controlGroup;
    private final HelpInline help;

    public FieldView(IsWidget widget, String label) {
        controlGroup = new ControlGroup();

        ControlLabel controLabel = new ControlLabel(label);
        controlGroup.add(controLabel);

        Controls controls = new Controls();
        help = new HelpInline();
        controls.add(widget);
        controls.add(help);
        controlGroup.add(controls);
    }

    @Override
    public Widget asWidget() {
        return controlGroup;
    }

    public void error(String message) {
        controlGroup.setType(ControlGroupType.ERROR);
        help.setText(message);
    }

    public void valid() {
        controlGroup.setType(ControlGroupType.SUCCESS);
        help.setText(null);
    }
}
