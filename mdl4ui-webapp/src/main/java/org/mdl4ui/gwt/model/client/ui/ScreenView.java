package org.mdl4ui.gwt.model.client.ui;

import org.mdl4ui.fields.model.Screen;
import org.mdl4ui.fields.model.WizardContext;

public interface ScreenView extends ElementView {

    Screen getScreen();

    void onDisplay(WizardContext context);
}
