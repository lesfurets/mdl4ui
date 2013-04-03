/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.sample.initializer;

import java.util.Date;

import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.FieldInitializer;
import org.mdl4ui.fields.model.component.DateField;
import org.mdl4ui.fields.model.event.FieldEvent;
import org.mdl4ui.fields.sample.InjectSampleInit;
import org.mdl4ui.fields.sample.OnField;
import org.mdl4ui.ui.sample.EFieldSample;

@SuppressWarnings("deprecation")
@InjectSampleInit(@OnField(EFieldSample.BIRTHDATE))
public class BirthdateInitializer implements FieldInitializer {

    @Override
    public void init(Field field, FieldEvent event) {
        DateField dateField = field.getComponent();

        Date startDate = new Date();
        startDate.setDate(1);
        startDate.setMonth(1);
        startDate.setYear(0);
        dateField.setStartDate(startDate);

        Date endDate = new Date();
        dateField.setEndDate(endDate);
    }
}
