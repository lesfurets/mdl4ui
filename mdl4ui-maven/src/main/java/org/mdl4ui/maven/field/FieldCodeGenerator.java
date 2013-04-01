/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.maven.field;

import java.io.IOException;

import org.apache.maven.plugin.MojoExecutionException;
import org.ez18n.apt.macro.PropertyParsingException;
import org.mdl4ui.base.model.BlockID;
import org.mdl4ui.base.model.FieldID;

public interface FieldCodeGenerator {

    String getDeclarationCode(FieldID fieldId) throws IOException, PropertyParsingException, MojoExecutionException;

    String getSetterCode(FieldID fieldId, BlockID blockID) throws IOException, PropertyParsingException,
                    MojoExecutionException;

    String getGetterCode(FieldID fieldId, BlockID blockID) throws IOException, PropertyParsingException,
                    MojoExecutionException;
}
