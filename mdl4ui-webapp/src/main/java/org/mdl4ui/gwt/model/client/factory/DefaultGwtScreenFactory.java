package org.mdl4ui.gwt.model.client.factory;

import org.mdl4ui.fields.model.Screen;
import org.mdl4ui.gwt.model.client.ui.DefaultScreenView;
import org.mdl4ui.gwt.model.client.ui.ScreenView;

public class DefaultGwtScreenFactory implements GwtScreenFactory {

    private DefaultGwtScreenFactory() {
    }

    @Override
    public ScreenView getView(Screen screen) {
        return new DefaultScreenView(screen);
    }
}
