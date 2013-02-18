package org.mdl4ui.gwt.sample.client.model;

import static org.mdl4ui.base.model.UIElementImpl.containsRec;

import java.util.List;

import org.mdl4ui.base.model.EElementType;
import org.mdl4ui.base.model.UIElementImpl;
import org.mdl4ui.fields.model.Field;

import com.github.gwtbootstrap.client.ui.ControlGroup;
import com.github.gwtbootstrap.client.ui.ControlLabel;
import com.github.gwtbootstrap.client.ui.Controls;
import com.github.gwtbootstrap.client.ui.HelpInline;
import com.github.gwtbootstrap.client.ui.Tooltip;
import com.github.gwtbootstrap.client.ui.base.ValueBoxBase;
import com.github.gwtbootstrap.client.ui.constants.ControlGroupType;
import com.github.gwtbootstrap.client.ui.constants.Trigger;
import com.google.gwt.user.client.ui.Widget;

public class FieldView implements ElementView {

    private final Field field;
    private final ControlGroup controlGroup;
    private final HelpInline validation;

    public FieldView(Field field) {
        this.field = field;

        Widget component = (Widget) field.getComponent();
        component.getElement().setId(field.getFieldID().toString());

        controlGroup = new ControlGroup();

        if (!field.isLabelInline()) {
            ControlLabel label = new ControlLabel(field.getLabel());
            controlGroup.add(label);
        }

        Controls controls = new Controls();
        Tooltip tooltip = new Tooltip(field.getHelp());
        tooltip.setTrigger(Trigger.HOVER);
        controls.add(tooltip);
        tooltip.add(component);

        validation = new HelpInline();
        controls.add(validation);

        if (field.getPlaceholder() != null && component instanceof ValueBoxBase) {
            ((ValueBoxBase<?>) component).setPlaceholder(field.getPlaceholder());
        }

        controlGroup.add(controls);
    }

    public Field getField() {
        return field;
    }

    @Override
    public Widget asWidget() {
        return controlGroup;
    }

    public void updateField() {
        switch (field.getState()) {
            case DEFAULT:
                controlGroup.setVisible(true);
                controlGroup.setType(ControlGroupType.NONE);
                validation.setVisible(false);
                validation.setText(null);
                break;
            case ERROR:
                controlGroup.setVisible(true);
                controlGroup.setType(ControlGroupType.ERROR);
                validation.setVisible(true);
                validation.setText(field.getValidation().getMessage());
                break;
            case HIDDEN:
                controlGroup.setVisible(false);
                validation.setText(null);
                break;
            case SET:
                controlGroup.setVisible(true);
                controlGroup.setType(ControlGroupType.SUCCESS);
                validation.setVisible(false);
                validation.setText(null);
                break;
        }
    }

    @Override
    public List<ElementView> childs() {
        return null;
    }

    @Override
    public EElementType elementType() {
        return EElementType.FIELD;
    }

    @Override
    public boolean contains(ElementView child) {
        return containsRec(this, child);
    }

    @Override
    public List<FieldView> fields() {
        return UIElementImpl.<FieldView, ElementView> collectFields(this);
    }

    @Override
    public List<BlockView> blocks() {
        return UIElementImpl.<BlockView, ElementView> collectBlocks(this);
    }

    @Override
    public List<GroupView> groups() {
        return UIElementImpl.<GroupView, ElementView> collectGroups(this);
    }
}
