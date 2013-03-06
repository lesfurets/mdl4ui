/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.model.event;

public enum EventProperty {
    FIELD("FIELD"), //
    BLOCK("BLOCK"), //
    SCREEN("SCREEN"), //
    DEPENDENCIES("DEPENDENCIES");

    private final String code;

    EventProperty(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
