package org.mdl4ui.gwt.sample.client;

import static org.mdl4ui.gwt.sample.client.ui.factory.GwtFieldHelpFactory.HELP;
import static org.mdl4ui.gwt.sample.client.ui.factory.GwtFieldLabelFactory.LABEL;
import static org.mdl4ui.gwt.sample.client.ui.factory.GwtFieldRendererFactory.RENDERER;

import org.mdl4ui.fields.model.Wizard;
import org.mdl4ui.gwt.sample.client.ui.model.WizardView;
import org.mdl4ui.ui.sample.EScreenSample;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class Sample implements EntryPoint {

    @Override
    public void onModuleLoad() {
        Wizard wizard = new Wizard(LABEL, RENDERER, HELP);
        wizard.setScreens(EScreenSample.values());

        WizardView wizardView = new WizardView(wizard);
        RootPanel.get("content").add(wizardView);
    }
}
