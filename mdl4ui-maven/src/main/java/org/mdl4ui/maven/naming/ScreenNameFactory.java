/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.maven.naming;

import static org.apache.commons.lang3.StringUtils.capitalize;

import java.text.MessageFormat;

import org.mdl4ui.base.model.ScreenID;

public final class ScreenNameFactory extends DefaultNameFactory<ScreenID> {

    @Override
    public final String getClassName(ScreenID screenID) {
        final String id = screenID.toString().replace("SCR_", "").toLowerCase();
        return capitalize(id) + "Screen";
    }

    @Override
    public final String getPackageName(ScreenID screenID) {
        return MessageFormat.format("{0}.selenium.screen", screenID.getClass().getPackage().getName());
    }
}
