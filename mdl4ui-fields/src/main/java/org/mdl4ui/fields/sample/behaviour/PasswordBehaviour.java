package org.mdl4ui.fields.sample.behaviour;

import org.mdl4ui.fields.model.DefaultBehaviour;
import org.mdl4ui.fields.sample.InjectSampleBehaviour;
import org.mdl4ui.fields.sample.OnField;
import org.mdl4ui.ui.sample.EFieldSample;

@InjectSampleBehaviour(@OnField(EFieldSample.PASSWORD))
public class PasswordBehaviour extends DefaultBehaviour {

}
