package org.mdl4ui.gwt.sample.client.ui.model;

import org.mdl4ui.fields.model.EFieldState;
import org.mdl4ui.fields.model.Field;

import com.github.gwtbootstrap.client.ui.ControlGroup;
import com.github.gwtbootstrap.client.ui.ControlLabel;
import com.github.gwtbootstrap.client.ui.Controls;
import com.github.gwtbootstrap.client.ui.HelpBlock;
import com.github.gwtbootstrap.client.ui.HelpInline;
import com.github.gwtbootstrap.client.ui.base.ValueBoxBase;
import com.github.gwtbootstrap.client.ui.constants.ControlGroupType;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class FieldView implements IsWidget {

    private final ControlGroup controlGroup;
    private final HelpInline validation;

    public FieldView(Field field) {
        final Widget component = (Widget) field.getComponent();
        component.getElement().setId(field.getId().toString());

        controlGroup = new ControlGroup();

        if (!field.isLabelInline()) {
            ControlLabel label = new ControlLabel(field.getLabel());
            controlGroup.add(label);
        }

        Controls controls = new Controls();
        controls.add(component);

        validation = new HelpInline();
        controls.add(validation);

        if (field.getPlaceholder() != null && component instanceof ValueBoxBase) {
            ((ValueBoxBase<?>) component).setPlaceholder(field.getPlaceholder());
        }

        if (field.getHelp() != null) {
            HelpBlock help = new HelpBlock(field.getHelp());
            controls.add(help);
        }

        controlGroup.add(controls);
    }

    @Override
    public Widget asWidget() {
        return controlGroup;
    }

    public void setFieldState(EFieldState state) {
        switch (state) {
            case DEFAULT:
                controlGroup.setVisible(true);
                controlGroup.setType(ControlGroupType.NONE);
                break;
            case ERROR:
                controlGroup.setVisible(true);
                controlGroup.setType(ControlGroupType.ERROR);
                break;
            case HIDDEN:
                controlGroup.setVisible(false);
                break;
            case SET:
                controlGroup.setVisible(true);
                controlGroup.setType(ControlGroupType.SUCCESS);
                break;
        }
    }
}
