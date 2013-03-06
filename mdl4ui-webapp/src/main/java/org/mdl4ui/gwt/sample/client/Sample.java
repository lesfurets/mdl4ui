package org.mdl4ui.gwt.sample.client;

import org.mdl4ui.fields.model.ClientFactory;
import org.mdl4ui.fields.model.DefaultWizard;
import org.mdl4ui.fields.model.event.FieldEvent;
import org.mdl4ui.fields.model.event.FieldEventListener;
import org.mdl4ui.fields.sample.context.SampleContext;
import org.mdl4ui.gwt.model.client.factory.GwtClientFactory;
import org.mdl4ui.gwt.model.client.ui.WizardView;
import org.mdl4ui.ui.sample.EApplicationSample;
import org.mdl4ui.ui.sample.EScreenSample;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

public class Sample implements EntryPoint {

    @Override
    public void onModuleLoad() {
        ClientFactory clientFactory = GWT.create(GwtClientFactory.class);
        DefaultWizard wizard = new DefaultWizard(new SampleContext(), clientFactory);
        wizard.addScreens(EApplicationSample.SAMPLE);
        wizard.addFieldListener(new FieldEventListener() {
            @Override
            public void onEvent(FieldEvent event) {
                GWT.log(event.toString());
            }
        });

        WizardView wizardView = new WizardView(wizard);
        wizardView.displayScreen(wizard, EScreenSample.values()[0]);

        RootPanel.get("content").add(wizardView);
    }
}
