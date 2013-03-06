/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.model.event;

public enum EventKind {
    UPDATE_VALUE("UPDATE_VALUE"), //
    IS_VISIBLE("IS_VISIBLE"), //
    INIT("INIT"), //
    UPDATE_FROM_CONTEXT("UPDATE_FROM_CONTEXT"), //
    UPDATE_CONTEXT("UPDATE_CONTEXT"), //
    RESET("RESET"), //
    VALIDATE("VALIDATE"), //
    VALUE_CHANGED("VALUE_CHANGED"), //
    ;

    private final String code;

    EventKind(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
