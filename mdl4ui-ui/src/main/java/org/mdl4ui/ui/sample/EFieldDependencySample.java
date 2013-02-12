/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.ui.sample;

import static org.mdl4ui.ui.sample.EFieldSample.NUMERIC;
import static org.mdl4ui.ui.sample.EFieldSample.PASSWORD;
import static org.mdl4ui.ui.sample.EFieldSample.TEXTBOX;

import org.mdl4ui.base.model.FieldDependency;
import org.mdl4ui.base.model.FieldID;

public enum EFieldDependencySample implements FieldDependency {

    NO(null), //
    DEP_TEXTBOX(TEXTBOX, PASSWORD, NUMERIC);

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
