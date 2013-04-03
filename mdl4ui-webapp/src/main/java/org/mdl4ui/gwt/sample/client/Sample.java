package org.mdl4ui.gwt.sample.client;

import org.mdl4ui.base.model.ScenarioID;
import org.mdl4ui.fields.model.ClientFactory;
import org.mdl4ui.fields.model.DefaultWizard;
import org.mdl4ui.fields.model.event.FieldEvent;
import org.mdl4ui.fields.model.event.FieldEventListener;
import org.mdl4ui.fields.sample.context.SampleContext;
import org.mdl4ui.gwt.model.client.factory.GwtClientFactory;
import org.mdl4ui.gwt.model.client.ui.WizardView;
import org.mdl4ui.ui.sample.EScenarioSample;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;

public class Sample implements EntryPoint {

    @Override
    public void onModuleLoad() {
        ScenarioID scenario;
        try {
            String token = History.getToken();
            scenario = EScenarioSample.valueOf(token);
        } catch (Exception e) {
            scenario = EScenarioSample.SCENARIO_MAIL;
        }
        GWT.log("Using scenario " + scenario);

        ClientFactory clientFactory = GWT.create(GwtClientFactory.class);
        DefaultWizard wizard = new DefaultWizard(new SampleContext(), clientFactory);
        wizard.addScreens(scenario);
        wizard.addFieldListener(new FieldEventListener() {
            @Override
            public void onEvent(FieldEvent event) {
                GWT.log(event.toString());
            }
        });

        WizardView wizardView = new WizardView(wizard);
        wizardView.displayScreen(wizard, scenario.screens().get(0));

        RootPanel.get("content").add(wizardView);
    }
}
