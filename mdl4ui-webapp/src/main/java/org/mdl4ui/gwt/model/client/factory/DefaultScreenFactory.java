package org.mdl4ui.gwt.model.client.factory;

import org.mdl4ui.fields.model.Screen;
import org.mdl4ui.gwt.model.client.ui.DefaultScreenView;
import org.mdl4ui.gwt.model.client.ui.ScreenView;

public class DefaultScreenFactory implements GwtScreenFactory {

    private DefaultScreenFactory() {
    }

    @Override
    public ScreenView getView(Screen screen) {
        return new DefaultScreenView(screen);
    }
}
