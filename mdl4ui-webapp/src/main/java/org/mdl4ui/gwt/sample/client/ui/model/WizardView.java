package org.mdl4ui.gwt.sample.client.ui.model;

import java.util.HashMap;
import java.util.Map;

import org.mdl4ui.base.model.ScreenID;
import org.mdl4ui.fields.model.DefaultWizard;
import org.mdl4ui.fields.model.Screen;
import org.mdl4ui.fields.model.Wizard;

import com.github.gwtbootstrap.client.ui.FluidContainer;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class WizardView implements IsWidget {

    private final FluidContainer container;
    private final Map<ScreenID, ScreenView> screens = new HashMap<ScreenID, ScreenView>();

    public WizardView(DefaultWizard wizard) {
        Screen firstScreen = null;
        for (ScreenID screenID : wizard.getScreens().keySet()) {
            ScreenView screen = new ScreenView(wizard.getScreens().get(screenID));
            if (firstScreen == null) {
                firstScreen = screen.getScreen();
            }
            screens.put(screenID, screen);
        }
        container = new FluidContainer();
    }

    public void displayScreen(Wizard wizard, ScreenID screenID) {
        container.clear();
        final ScreenView screenView = screens.get(screenID);
        if (screenView == null) {
            throw new IllegalArgumentException("unknow screen id : " + screenView);
        }
        container.add(screenView);
        wizard.setCurrentScreen(screenView.getScreen());
    }

    @Override
    public Widget asWidget() {
        return container;
    }
}
