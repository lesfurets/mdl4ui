/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.maven.field;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.ez18n.apt.macro.MacroProcessor;
import org.ez18n.apt.macro.PropertyParsingException;
import org.mdl4ui.base.model.BlockID;
import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.maven.GenerateSeleniumMojo;
import org.mdl4ui.maven.naming.BlockNameFactory;

public abstract class DefaultFieldCodeGenerator implements FieldCodeGenerator {

    protected final BlockNameFactory nameFactory = new BlockNameFactory();

    protected String getDeclarationTemplate() {
        return "FieldDefaultDeclaration.template";
    }

    /**
     * allow to override generation parameters
     * 
     * @param params the default generation parameters
     */
    protected void configureProperties(FieldID fieldId, Map<String, Object> params) throws MojoExecutionException {
        // nothing by default
    }

    @Override
    public final String getDeclarationCode(FieldID fieldId) throws IOException, PropertyParsingException,
                    MojoExecutionException {
        try {
            final Map<String, Object> params = new HashMap<String, Object>();
            params.put("field.name", nameFactory.getFieldName(fieldId));
            params.put("field.code", fieldId.toString());
            configureProperties(fieldId, params);
            final String classTemplate = IOUtils.toString(GenerateSeleniumMojo.class
                            .getResourceAsStream(getDeclarationTemplate()));
            return MacroProcessor.replaceProperties(classTemplate, params, 5);
        } catch (Exception e) {
            throw new RuntimeException("generation failed for field " + fieldId);
        }
    }

    protected String getSetterTemplate() {
        return "FieldDefaultSetter.template";
    }

    @Override
    public final String getSetterCode(FieldID fieldId, BlockID blockID) throws IOException, PropertyParsingException,
                    MojoExecutionException {
        try {
            final Map<String, Object> params = new HashMap<String, Object>();
            final String fieldName = nameFactory.getFieldName(fieldId);
            params.put("field.name", fieldName);
            params.put("field.type", capitalize(fieldName));
            params.put("field.code", fieldId.toString());
            params.put("target.class.name", nameFactory.getClassName(blockID));
            configureProperties(fieldId, params);
            final String classTemplate = IOUtils.toString(GenerateSeleniumMojo.class
                            .getResourceAsStream(getSetterTemplate()));
            return MacroProcessor.replaceProperties(classTemplate, params, 5);
        } catch (Exception e) {
            throw new RuntimeException("generation failed for field " + fieldId);
        }
    }

    protected String getGetterTemplate() {
        return "FieldDefaultGetter.template";
    }

    @Override
    public final String getGetterCode(FieldID fieldId, BlockID blockID) throws IOException, PropertyParsingException,
                    MojoExecutionException {
        try {
            final Map<String, Object> params = new HashMap<String, Object>();
            final String fieldName = nameFactory.getFieldName(fieldId);
            params.put("field.name", fieldName);
            params.put("field.type", capitalize(fieldName));
            params.put("field.code", fieldId.toString());
            params.put("target.class.name", nameFactory.getClassName(blockID));
            configureProperties(fieldId, params);
            final String classTemplate = IOUtils.toString(GenerateSeleniumMojo.class
                            .getResourceAsStream(getGetterTemplate()));
            return MacroProcessor.replaceProperties(classTemplate, params, 5);
        } catch (Exception e) {
            throw new RuntimeException("generation failed for field " + fieldId);
        }
    }

    /**
     * Transforms a list of work separated by given separator into a camelized string. ie, transforms background-color
     * to backgroundColor.
     * 
     * @param str
     * @param separator
     * @return
     */
    static String camelize(String str, String separator) {
        if (isEmpty(str)) {
            return str;
        }
        final String[] tokens = str.split(separator);
        String result = tokens[0];
        for (int i = 1; i < tokens.length; i++) {
            result += String.valueOf(tokens[i].charAt(0)).toUpperCase() + tokens[i].substring(1);
        }
        return result;
    }
}
