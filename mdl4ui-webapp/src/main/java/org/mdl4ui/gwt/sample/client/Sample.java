package org.mdl4ui.gwt.sample.client;

import static org.mdl4ui.gwt.sample.client.ui.factory.GwtFieldHelpFactory.HELP;
import static org.mdl4ui.gwt.sample.client.ui.factory.GwtFieldLabelFactory.LABEL;
import static org.mdl4ui.gwt.sample.client.ui.factory.GwtFieldRendererFactory.RENDERER;

import org.mdl4ui.fields.model.Screen;
import org.mdl4ui.fields.model.Wizard;
import org.mdl4ui.gwt.sample.client.ui.model.ScreenView;
import org.mdl4ui.ui.sample.EScreenSample;

import com.github.gwtbootstrap.client.ui.FluidContainer;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class Sample implements EntryPoint {

    @Override
    public void onModuleLoad() {
        Wizard wizard = new Wizard(LABEL, RENDERER, HELP);
        Screen screen = wizard.createScreen(EScreenSample.SCR_WIDGETS_SHOWCASE);
        ScreenView view = new ScreenView(screen);

        FluidContainer container = new FluidContainer();
        container.add(view);
        RootPanel.get("content").add(container);
    }
}
