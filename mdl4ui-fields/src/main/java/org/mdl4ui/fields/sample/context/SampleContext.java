/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.sample.context;

import org.mdl4ui.fields.model.WizardContext;

public class SampleContext implements WizardContext {

    private Person person = new Person();
    private UserAccount userAccount = new UserAccount();

    public Person getPerson() {
        return person;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }
}
