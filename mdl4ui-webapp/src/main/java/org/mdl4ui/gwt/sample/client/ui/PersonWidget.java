/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.gwt.sample.client.ui;

import static com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat.DATE_SHORT;
import static org.mdl4ui.fields.sample.i18n.FieldMessagesFactory.MSG_FM;
import static org.mdl4ui.gwt.sample.client.i18n.SampleMessagesFactory.MSG_SM;

import java.util.Date;

import org.mdl4ui.fields.sample.context.Person;

import com.google.gwt.i18n.client.DateTimeFormat;

public class PersonWidget extends RegistrationWidget {

    public PersonWidget() {
        super();
    }

    public void setPerson(Person person) {
        container.clear();

        addLegend(MSG_SM().me());

        if (person.getFirstName() != null) {
            addRow(MSG_FM().firstName(), person.getFirstName());
        }
        if (person.getLastName() != null) {
            addRow(MSG_FM().lastName(), person.getLastName());
        }
        if (person.getBirthDate() != null) {
            Date birthDate = person.getBirthDate();
            addRow(MSG_FM().birthDate(), DateTimeFormat.getFormat(DATE_SHORT).format(birthDate));
        }
    }
}
