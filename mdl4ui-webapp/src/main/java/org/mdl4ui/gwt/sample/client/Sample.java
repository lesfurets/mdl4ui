package org.mdl4ui.gwt.sample.client;

import static org.mdl4ui.gwt.sample.client.ui.factory.GwtFieldHelpFactory.HELP;
import static org.mdl4ui.gwt.sample.client.ui.factory.GwtFieldRendererFactory.RENDERER;

import org.mdl4ui.fields.model.FieldLabelFactory;
import org.mdl4ui.fields.model.Wizard;
import org.mdl4ui.fields.sample.i18n.GwtFieldLabelFactory;
import org.mdl4ui.gwt.sample.client.ui.model.WizardView;
import org.mdl4ui.ui.sample.EScreenSample;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.RootPanel;

public class Sample implements EntryPoint {

    @Override
    public void onModuleLoad() {
        FieldLabelFactory labelFactory = GWT.create(GwtFieldLabelFactory.class);

        Wizard wizard = new Wizard(labelFactory, HELP, RENDERER);
        wizard.setScreens(EScreenSample.values());

        WizardView wizardView = new WizardView(wizard);
        RootPanel.get("content").add(wizardView);
    }
}
