/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.gwt.sample.client.ui;

import static org.mdl4ui.fields.sample.i18n.FieldMessagesFactory.MSG_FM;
import static org.mdl4ui.gwt.sample.client.i18n.SampleMessagesFactory.MSG_SM;

import org.mdl4ui.fields.sample.context.EmailType;
import org.mdl4ui.fields.sample.context.UserAccount;

public class UserAccountWidget extends RegistrationWidget {

    public UserAccountWidget() {
        super();
    }

    public void setUserAccount(UserAccount account) {
        container.clear();

        addLegend(MSG_SM().my_account());

        if (account.getLogin() != null) {
            addRow(MSG_FM().login(), account.getLogin());
        }
        if (account.getEmail() != null) {
            addRow(MSG_FM().email(), account.getEmail());
        }
        if (account.getLanguage() != null) {
            switch (account.getLanguage()) {
                case EN:
                    addRow(MSG_FM().language(), MSG_FM().english());
                    break;
                case FR:
                    addRow(MSG_FM().language(), MSG_FM().francais());
                    break;
            }
        }
        addRow(MSG_FM().emailAccepted(), account.isAcceptEmail() ? MSG_FM().yes() : MSG_FM().no());
        if (account.isAcceptEmail()) {
            if (account.getEmailTypes() != null) {
                StringBuilder builder = new StringBuilder();
                for (EmailType type : account.getEmailTypes()) {
                    switch (type) {
                        case ADMINISTRATOR:
                            builder.append(MSG_FM().administratorMessage());
                            break;
                        case NEWSLETTER:
                            builder.append(MSG_FM().newsletter());
                            break;
                        case PRIVATE:
                            builder.append(MSG_FM().privateMessage());
                            break;
                    }
                }
                addRow(MSG_FM().emailPreference(), builder.toString());
            }
            addRow(MSG_FM().max_mail_per_week(), Integer.toString(account.getMaxEmailPerWeek()));
        }
        if (account.getPhoneNumber() != null) {
            addRow(MSG_FM().phoneNumber(), account.getPhoneNumber());
        }
    }
}
