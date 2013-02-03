package org.mdl4ui.gwt.sample.client.ui;

import static org.mdl4ui.gwt.sample.client.ui.factory.GwtFieldHelpFactory.HELP;
import static org.mdl4ui.gwt.sample.client.ui.factory.GwtFieldLabelFactory.LABEL;
import static org.mdl4ui.gwt.sample.client.ui.factory.GwtFieldRendererFactory.RENDERER;

import org.mdl4ui.fields.model.Wizard;

public class WizardImpl extends Wizard {

    public WizardImpl() {
        super(LABEL, RENDERER, HELP);
    }
}
