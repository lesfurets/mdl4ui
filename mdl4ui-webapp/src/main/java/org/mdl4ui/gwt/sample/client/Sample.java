package org.mdl4ui.gwt.sample.client;

import org.mdl4ui.fields.model.ClientFactory;
import org.mdl4ui.fields.model.DefaultWizard;
import org.mdl4ui.gwt.sample.client.ui.factory.GwtClientFactory;
import org.mdl4ui.gwt.sample.client.ui.model.WizardView;
import org.mdl4ui.ui.sample.EScreenSample;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

public class Sample implements EntryPoint {

    @Override
    public void onModuleLoad() {
        ClientFactory clientFactory = GWT.create(GwtClientFactory.class);
        DefaultWizard wizard = new DefaultWizard(clientFactory);
        wizard.addScreens(EScreenSample.values());

        WizardView wizardView = new WizardView(wizard);
        wizardView.displayScreen(wizard, EScreenSample.values()[0]);

        RootPanel.get("content").add(wizardView);
    }
}
