package org.mdl4ui.gwt.sample.client.factory;

import org.mdl4ui.fields.model.Screen;
import org.mdl4ui.gwt.model.client.factory.GwtScreenFactory;
import org.mdl4ui.gwt.model.client.ui.DefaultScreenView;
import org.mdl4ui.gwt.model.client.ui.ScreenView;
import org.mdl4ui.gwt.sample.client.ui.RegistrationDoneView;
import org.mdl4ui.ui.sample.EScreenSample;

public class GwtSampleScreenFactory implements GwtScreenFactory {

    private GwtSampleScreenFactory() {
    }

    @Override
    public ScreenView getView(Screen screen) {
        EScreenSample screenID = (EScreenSample) screen.getScreenID();
        switch (screenID) {
            case SCR_DONE:
                return new RegistrationDoneView(screen);
            default:
                return new DefaultScreenView(screen);
        }
    }
}
