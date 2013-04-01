/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.maven.naming;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.uncapitalize;

import java.text.MessageFormat;

import org.mdl4ui.base.model.BlockID;
import org.mdl4ui.base.model.FieldID;

public final class BlockNameFactory extends DefaultNameFactory<BlockID> {

    @Override
    public final String getClassName(BlockID blocID) {
        final String id = blocID.toString().replace("BLK_", "");
        return camelize(capitalize(id.toLowerCase()), "_") + "Block";
    }

    @Override
    public final String getPackageName(BlockID blockID) {
        return MessageFormat.format("{0}.selenium.block", blockID.getClass().getPackage().getName());
    }

    public final String getFieldName(BlockID blocID) {
        final String id = blocID.toString().replace("BLK_", "");
        return camelize(uncapitalize(id.toLowerCase()), "_");
    }

    public final String getFieldName(FieldID blocID) {
        final String id = blocID.toString().replace("BLK_", "");
        return camelize(id.toLowerCase(), "_");
    }
}
