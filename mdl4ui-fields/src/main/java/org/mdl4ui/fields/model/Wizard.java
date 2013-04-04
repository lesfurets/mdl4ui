/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.model;

import java.util.Map;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.base.model.ScenarioID;
import org.mdl4ui.base.model.ScreenID;
import org.mdl4ui.fields.model.event.FieldEvent;
import org.mdl4ui.fields.model.event.FieldEventListener;
import org.mdl4ui.fields.model.validation.FieldValidation;

public interface Wizard {

    ScenarioID getScenario();

    WizardContext getContext();

    Map<ScreenID, Screen> getScreens();

    void addScreens(ScenarioID application);

    void displayScreen(Screen screen);

    void submit(Block block);

    boolean isVisible(FieldID fieldId, FieldEvent event);

    void updateFromContext(Field field, FieldEvent event);

    void updateContext(Field field, FieldEvent event);

    void updateField(Field field, FieldEvent event);

    FieldValidation validate(Field field, FieldEvent event);

    void addFieldListener(FieldEventListener listener);

    void removeFieldListener(FieldEventListener listener);
}
