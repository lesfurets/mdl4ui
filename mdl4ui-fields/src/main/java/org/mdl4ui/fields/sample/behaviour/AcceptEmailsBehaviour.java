package org.mdl4ui.fields.sample.behaviour;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.DefaultBehaviour;
import org.mdl4ui.fields.model.WizardContext;
import org.mdl4ui.fields.model.event.FieldEvent;
import org.mdl4ui.fields.sample.InjectSampleBehaviour;
import org.mdl4ui.fields.sample.OnField;
import org.mdl4ui.fields.sample.context.SampleContext;
import org.mdl4ui.ui.sample.EFieldSample;

@InjectSampleBehaviour(@OnField({ EFieldSample.EMAILS_PREFERENCES, EFieldSample.MAX_WEEKLY_EMAILS }))
public class AcceptEmailsBehaviour extends DefaultBehaviour {

    @Override
    public boolean isVisible(FieldID fieldId, WizardContext context, FieldEvent fieldEvent) {
        SampleContext sampleContext = (SampleContext) context;
        Boolean acceptEmail = sampleContext.getUserAccount().isAcceptEmail();
        return acceptEmail != null && acceptEmail;
    }
}
