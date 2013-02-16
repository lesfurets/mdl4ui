/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.sample.context;

public enum Timezone {
    ETC_GMT_PLUS_12("Etc/GMT+12", "GMT-12:00"), //
    ETC_GMT_PLUS_11("Etc/GMT+11", "GMT-11:00"), //
    ETC_GMT_PLUS_10("Etc/GMT+10", "GMT-10:00"), //
    ETC_GMT_PLUS_9("Etc/GMT+9", "GMT-09:00"), //
    ETC_GMT_PLUS_8("Etc/GMT+8", "GMT-08:00"), //
    ETC_GMT_PLUS_7("Etc/GMT+7", "GMT-07:00"), //
    ETC_GMT_PLUS_6("Etc/GMT+6", "GMT-06:00"), //
    ETC_GMT_PLUS_5("Etc/GMT+5", "GMT-05:00"), //
    ETC_GMT_PLUS_4("Etc/GMT+4", "GMT-04:00"), //
    ETC_GMT_PLUS_3("Etc/GMT+3", "GMT-03:00"), //
    ETC_GMT_PLUS_2("Etc/GMT+2", "GMT-02:00"), //
    ETC_GMT_PLUS_1("Etc/GMT+1", "GMT-01:00"), //
    ETC_GMT("Etc/GMT", "GMT+00:00"), //
    ETC_GMT_MINUS_1("Etc/GMT-1", "GMT+01:00"), //
    ETC_GMT_MINUS_2("Etc/GMT-2", "GMT+02:00"), //
    ETC_GMT_MINUS_3("Etc/GMT-3", "GMT+03:00"), //
    ETC_GMT_MINUS_4("Etc/GMT-4", "GMT+04:00"), //
    ETC_GMT_MINUS_5("Etc/GMT-5", "GMT+05:00"), //
    ETC_GMT_MINUS_6("Etc/GMT-6", "GMT+06:00"), //
    ETC_GMT_MINUS_7("Etc/GMT-7", "GMT+07:00"), //
    ETC_GMT_MINUS_8("Etc/GMT-8", "GMT+08:00"), //
    ETC_GMT_MINUS_9("Etc/GMT-9", "GMT+09:00"), //
    ETC_GMT_MINUS_10("Etc/GMT-10", "GMT+10:00"), //
    ETC_GMT_MINUS_11("Etc/GMT-11", "GMT+11:00"), //
    ETC_GMT_MINUS_12("Etc/GMT-12", "GMT+12:00"), //
    ETC_GMT_MINUS_13("Etc/GMT-13", "GMT+13:00"), //
    ETC_GMT_MINUS_14("Etc/GMT-14", "GMT+14:00");

    private final String code;
    private final String displayName;

    private Timezone(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public String getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Timezone fromCode(String code) {
        for (Timezone timezone : values()) {
            if (timezone.getCode().equals(code))
                return timezone;
        }
        return null;
    }

}
