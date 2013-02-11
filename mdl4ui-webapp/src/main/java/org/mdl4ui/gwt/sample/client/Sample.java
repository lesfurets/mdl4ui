package org.mdl4ui.gwt.sample.client;

import org.mdl4ui.fields.model.FieldHelpFactory;
import org.mdl4ui.fields.model.FieldLabelFactory;
import org.mdl4ui.fields.model.FieldRendererFactory;
import org.mdl4ui.fields.model.Wizard;
import org.mdl4ui.fields.sample.i18n.GwtFieldHelpFactory;
import org.mdl4ui.fields.sample.i18n.GwtFieldLabelFactory;
import org.mdl4ui.gwt.sample.client.ui.factory.GwtFieldRendererFactory;
import org.mdl4ui.gwt.sample.client.ui.model.WizardView;
import org.mdl4ui.ui.sample.EScreenSample;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.RootPanel;

public class Sample implements EntryPoint {

    @Override
    public void onModuleLoad() {
        FieldLabelFactory labelFactory = GWT.create(GwtFieldLabelFactory.class);
        FieldHelpFactory helpFactory = GWT.create(GwtFieldHelpFactory.class);
        FieldRendererFactory rendererFactory = GWT.create(GwtFieldRendererFactory.class);

        Wizard wizard = new Wizard(labelFactory, helpFactory, rendererFactory);
        wizard.setScreens(EScreenSample.values());

        WizardView wizardView = new WizardView(wizard);
        RootPanel.get("content").add(wizardView);
    }
}
