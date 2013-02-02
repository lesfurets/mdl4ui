/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.common;

import org.mdl4ui.base.model.EElementType;
import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.base.model.ScreenID;
import org.mdl4ui.fields.common.component.FieldComponent;
import org.mdl4ui.fields.common.validation.FieldValidation;

public class Field implements BlockItem {

    private final FieldID fieldId;
    private final ScreenID screenId;

    private final FieldComponent<?> component;
    private final FieldRenderer<?, ?> renderer;
    private EFieldState state = EFieldState.DEFAULT;

    public Field(FieldID fieldId, ScreenID screenId, final FieldRenderer<?, ?> renderer) {
        this.fieldId = fieldId;
        this.screenId = screenId;
        this.component = renderer.getFieldComponent();
        this.renderer = renderer;
    }

    public FieldID getId() {
        return fieldId;
    }

    public ScreenID getScreenID() {
        return screenId;
    }

    @SuppressWarnings("unchecked")
    public <F extends FieldComponent<?>> F getComponent() {
        return (F) component;
    }

    @SuppressWarnings("unchecked")
    public <R extends FieldRenderer<?, ?>> R getRenderer() {
        return (R) renderer;
    }

    public void setLabel(String html) {
        renderer.setLabel(html);
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
