package org.mdl4ui.gwt.sample.client.ui.model;

import java.util.HashMap;
import java.util.Map;

import org.mdl4ui.base.model.ScreenID;
import org.mdl4ui.fields.model.Wizard;

import com.github.gwtbootstrap.client.ui.FluidContainer;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class WizardView implements IsWidget {

    private final FluidContainer container;
    private final Map<ScreenID, ScreenView> screens = new HashMap<ScreenID, ScreenView>();

    public WizardView(Wizard wizard) {
        ScreenView firstScreen = null;
        for (ScreenID screenID : wizard.getScreens().keySet()) {
            ScreenView screen = new ScreenView(wizard.getScreens().get(screenID));
            if (firstScreen == null) {
                firstScreen = screen;
            }
            screens.put(screenID, screen);
        }
        container = new FluidContainer();
        container.add(firstScreen);
    }

    @Override
    public Widget asWidget() {
        return container;
    }
}
