/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.model;

import org.mdl4ui.base.model.EElementType;
import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.component.FieldComponent;
import org.mdl4ui.fields.model.validation.FieldValidation;

public class Field implements BlockItem {

    private final FieldID fieldId;

    private final String label, help;
    private final FieldComponent<?> component;
    private final FieldRenderer<?> renderer;
    private EFieldState state = EFieldState.DEFAULT;

    public Field(FieldID fieldId, String label, String help, final FieldRenderer<?> renderer) {
        this.fieldId = fieldId;
        this.label = label;
        this.help = help;
        this.component = renderer.getFieldComponent();
        this.renderer = renderer;
    }

    public FieldID getId() {
        return fieldId;
    }

    public String getLabel() {
        return label;
    }

    public String getHelp() {
        return help;
    }

    @SuppressWarnings("unchecked")
    public <F extends FieldComponent<?>> F getComponent() {
        return (F) component;
    }

    @SuppressWarnings("unchecked")
    public <R extends FieldRenderer<?>> R getRenderer() {
        return (R) renderer;
    }

    public EFieldState getState() {
        return this.state;
    }

    public final void setState(EFieldState newState, FieldValidation validation) {
        state = newState;
    }

    @Override
    public EElementType getType() {
        return EElementType.FIELD;
    }
}
