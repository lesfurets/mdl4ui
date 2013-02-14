/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.model;

import java.util.Map;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.base.model.ScreenID;
import org.mdl4ui.fields.model.validation.FieldValidation;

public interface Wizard {

    Map<ScreenID, Screen> getScreens();

    Screen getCurrentScreen();

    void setCurrentScreen(Screen screen);

    void addScreens(ScreenID... screenIds);

    WizardContext getContext();

    boolean isVisible(FieldID fieldId);

    void updateFromContext(Field field);

    void updateContext(Field field);

    void updateField(Field field);

    FieldValidation validate(Field field);
}
