package org.mdl4ui.fields.model;

import java.util.List;

import org.mdl4ui.base.model.EElementType;
import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.component.FieldComponent;
import org.mdl4ui.fields.model.validation.FieldValidation;

public class Field extends ElementImpl {

    private final FieldID fieldId;

    private final String label, help, placeholder;
    private final FieldRenderer<?> renderer;
    private EFieldState state = EFieldState.DEFAULT;

    public Field(FieldID fieldId, String label, String help, String placeholder, final FieldRenderer<?> renderer) {
        this.fieldId = fieldId;
        this.label = label;
        this.help = help;
        this.placeholder = placeholder;
        this.renderer = renderer;
    }

    public FieldID getFieldID() {
        return fieldId;
    }

    public String getLabel() {
        return label;
    }

    public String getHelp() {
        return help;
    }

    public String getPlaceholder() {
        return this.placeholder;
    }

    @SuppressWarnings("unchecked")
    public <F extends FieldComponent<?>> F getComponent() {
        final F component = (F) renderer.getFieldComponent(fieldId);
        renderer.setLabel(label, fieldId);
        return component;
    }

    public boolean isLabelInline() {
        return renderer.isLabelInline();
    }

    public EFieldState getState() {
        return this.state;
    }

    public final void setState(EFieldState newState, FieldValidation validation) {
        state = newState;
    }

    @Override
    public EElementType elementType() {
        return EElementType.FIELD;
    }

    @Override
    public List<Element> childs() {
        return null;
    }

}
