/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.ui.sample;

import org.mdl4ui.base.model.FieldDependency;
import org.mdl4ui.base.model.FieldID;

public enum EFieldDependencySample implements FieldDependency {

    NO(null), //
    DEP_EMAIL_ACCEPTED(EFieldSample.EMAIL_ACCEPTED,//
                    EFieldSample.EMAILS_PREFERENCES, EFieldSample.MAX_EMAILS_PER_WEEK), //
    DEP_PASSWORD(EFieldSample.PASSWORD, EFieldSample.PASSWORD_CONFIRMATION), //
    DEP_PASSWORD_CONFIRMATION(EFieldSample.PASSWORD_CONFIRMATION, EFieldSample.PASSWORD);

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
