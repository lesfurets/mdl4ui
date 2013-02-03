package org.mdl4ui.gwt.sample.client;

import org.mdl4ui.fields.model.Screen;
import org.mdl4ui.fields.model.Wizard;
import org.mdl4ui.gwt.sample.client.ui.model.ScreenView;
import org.mdl4ui.ui.sample.EScreenSample;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

public class Sample implements EntryPoint {

    @Override
    public void onModuleLoad() {
        Wizard wizard = GWT.create(Wizard.class);
        final Screen screen = wizard.getScreen(EScreenSample.SCR_WIDGETS_SHOWCASE);
        final ScreenView view = new ScreenView(screen);

        RootPanel.get("content").add(view);
    }
}
