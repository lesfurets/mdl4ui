package org.mdl4ui.fields.sample.initializer;

import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.FieldInitializer;
import org.mdl4ui.fields.sample.InjectSampleInit;
import org.mdl4ui.fields.sample.OnField;
import org.mdl4ui.ui.sample.EFieldSample;

@InjectSampleInit(@OnField(EFieldSample.PASSWORD))
public class PasswordInitializer implements FieldInitializer {

    @Override
    public void init(Field field) {
        // TODO not implemented
    }
}
