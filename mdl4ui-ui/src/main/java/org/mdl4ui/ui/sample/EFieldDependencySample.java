/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.ui.sample;

import static org.mdl4ui.ui.sample.EFieldSample.EMAILS_PREFERENCES;
import static org.mdl4ui.ui.sample.EFieldSample.EMAIL_ACCEPTED;
import static org.mdl4ui.ui.sample.EFieldSample.MAX_WEEKLY_EMAILS;
import static org.mdl4ui.ui.sample.EFieldSample.PASSWORD;
import static org.mdl4ui.ui.sample.EFieldSample.PASSWORD_CONFIRMATION;

import org.mdl4ui.base.model.FieldDependency;
import org.mdl4ui.base.model.FieldID;

public enum EFieldDependencySample implements FieldDependency {

    NO(null), //
    DEP_EMAIL_ACCEPTED(EMAIL_ACCEPTED, EMAILS_PREFERENCES, MAX_WEEKLY_EMAILS), //
    DEP_PASSWORD(PASSWORD, PASSWORD_CONFIRMATION), //
    DEP_PASSWORD_CONFIRMATION(PASSWORD_CONFIRMATION, PASSWORD);

    private final FieldID from;
    private final FieldID[] to;

    EFieldDependencySample(FieldID from) {
        this(from, new EFieldSample[] {});
    }

    EFieldDependencySample(FieldID from, FieldID... to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public FieldID from() {
        return from;
    }

    @Override
    public FieldID[] to() {
        return to;
    }
}
