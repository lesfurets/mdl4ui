/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.maven;

import static java.text.DateFormat.SHORT;
import static java.text.DateFormat.getDateTimeInstance;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.join;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.ez18n.apt.macro.MacroProcessor;
import org.ez18n.apt.macro.PropertyParsingException;
import org.mdl4ui.base.model.BlockID;
import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.base.model.ScreenID;
import org.mdl4ui.maven.field.FieldCodeGenerator;
import org.mdl4ui.maven.naming.BlockNameFactory;
import org.mdl4ui.maven.naming.ScreenNameFactory;

/**
 * @goal generateSelenium
 * @requiresDependencyResolution test
 * @phase generate-test-sources
 * @threadSafe
 */
public final class GenerateSeleniumMojo extends AbstractSeleniumMojo {

    private static final BlockNameFactory BLOCK_NAME_FACTORY = new BlockNameFactory();
    private static final ScreenNameFactory SCREEN_NAME_FACTORY = new ScreenNameFactory();

    /**
     * @parameter default-value=".java"
     * @required
     */
    private String extension;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        ensureOutputTestDirectoryExists();
        if (skip()) {
            getLog().info("Tests are skipped, generated apt-test also.");
            return;
        }

        final ClassLoader classLoader = getClassLoader(true);

        // retrieve all screen & page object, by module
        final Collection<ScreenID> screens = new ArrayList<ScreenID>();
        final List<String> screenClasses = getScreenClasses();
        for (int i = 0; i < screenClasses.size(); i++) {
            String screenClass = screenClasses.get(i);
            for (ScreenID screen : AbstractSeleniumMojo.<ScreenID> enumValues(asList(screenClass), classLoader)) {
                screens.add(screen);
            }
        }

        for (final ScreenID screenID : screens) {
            // generate block objects classes
            for (BlockID blockID : screenID.blocks()) {
                try {
                    final String factoryClass = generateBlockClass(blockID, screenID, classLoader);
                    final File outputFile = new File(getOutputTestDirectory(),
                                    BLOCK_NAME_FACTORY.getOutputFile(blockID) + extension);
                    createParentFolder(outputFile);
                    IOUtils.write(factoryClass, new FileOutputStream(outputFile), "UTF-8");
                    getLog().info("written " + outputFile.getAbsolutePath());
                } catch (Exception e) {
                    throw new MojoExecutionException("error occured when generating class for block " + blockID, e);
                }
            }

            // generate page objects classes
            try {
                final String factoryClass = generateScreenClass(screenID);
                final File outputFile = new File(getOutputTestDirectory(), SCREEN_NAME_FACTORY.getOutputFile(screenID)
                                + extension);
                createParentFolder(outputFile);
                IOUtils.write(factoryClass, new FileOutputStream(outputFile), "UTF-8");
                getLog().info("written " + outputFile.getAbsolutePath());
            } catch (Exception e) {
                throw new MojoExecutionException("error occured when generating class for screen " + screenID, e);
            }
        }
    }

    private String generateScreenClass(ScreenID screenID) throws IOException, PropertyParsingException {
        List<BlockID> sortedBlocks = new ArrayList<BlockID>(screenID.blocks());

        Set<String> imports = new HashSet<String>();
        imports.add(screenID.getClass().getName());
        if (!sortedBlocks.isEmpty())
            imports.add(sortedBlocks.iterator().next().getClass().getName());
        for (BlockID block : sortedBlocks) {
            imports.add(BLOCK_NAME_FACTORY.getClassQualifiedName(block));
        }

        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("process.class", getClass().getName());
        params.put("process.date", getDateTimeInstance(SHORT, SHORT).format(new Date()));
        params.put("package.name", SCREEN_NAME_FACTORY.getPackageName(screenID));
        params.put("source.screen.name", screenID.getClass().getSimpleName() + "." + screenID.toString());
        params.put("source.screen.class", screenID.getClass().getName());
        params.put("target.class.name", SCREEN_NAME_FACTORY.getClassName(screenID));
        params.put("field.blocs.declaration", getBlocksFieldCode("BlockDeclaration.template", sortedBlocks));
        params.put("field.blocs.constructor", getBlocksFieldCode("BlockConstructor.template", sortedBlocks));
        params.put("source.screen.identifier",
                        !sortedBlocks.isEmpty() ? BLOCK_NAME_FACTORY.getFieldName(sortedBlocks.get(0))
                                        + ".getIdentifyingElementLocator()" : "null");
        params.put("field.blocs.getter", getBlocksFieldCode("BlockGetter.template", sortedBlocks));
        params.put("field.blocs.ifBlocStatement", getBlocksFieldCode("ScreenBlockIfStatement.template", sortedBlocks));
        params.put("field.blocs.ifFieldStatement", getBlocksFieldCode("ScreenFieldIfStatement.template", sortedBlocks));
        params.put("imports", formatImport(imports));

        final String classTemplate = IOUtils.toString(GenerateSeleniumMojo.class
                        .getResourceAsStream("ScreenPageClass.template"));
        return MacroProcessor.replaceProperties(classTemplate, params, 5);
    }

    private String getBlocksFieldCode(String template, List<BlockID> sortedBlocks) throws IOException,
                    PropertyParsingException {
        List<String> declarations = new ArrayList<String>();
        for (BlockID blockID : sortedBlocks) {
            final Map<String, Object> params = new HashMap<String, Object>();
            params.put("field.bloc.class", BLOCK_NAME_FACTORY.getClassName(blockID));
            params.put("field.bloc.type", capitalize(BLOCK_NAME_FACTORY.getFieldName(blockID)));
            params.put("field.bloc.name", BLOCK_NAME_FACTORY.getFieldName(blockID));
            params.put("field.bloc.id", blockID.getClass().getSimpleName() + "." + blockID.toString());
            final String classTemplate = IOUtils.toString(GenerateSeleniumMojo.class.getResourceAsStream(template));
            declarations.add(MacroProcessor.replaceProperties(classTemplate, params, 5));
        }
        return join(declarations, "\n");
    }

    private String generateBlockClass(BlockID blockID, ScreenID screenID, ClassLoader classLoader) throws IOException,
                    PropertyParsingException, MojoExecutionException, MojoFailureException {
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("process.class", getClass().getName());
        params.put("process.date", getDateTimeInstance(SHORT, SHORT).format(new Date()));
        params.put("package.name", BLOCK_NAME_FACTORY.getPackageName(blockID));
        params.put("source.bloc.name", blockID.getClass().getSimpleName() + "." + blockID.toString());
        params.put("source.bloc.class", blockID.getClass().getName());
        params.put("bloc.next", blockID.toString().toUpperCase() + "_NEXT");
        params.put("bloc.fields.declaration", getFieldDeclarationCode(blockID, classLoader));
        params.put("bloc.fields.getter", getFieldSetterCode(blockID, classLoader));
        params.put("bloc.fields.setter", getFieldGetterCode(blockID, classLoader));
        params.put("target.class.name", BLOCK_NAME_FACTORY.getClassName(blockID));

        final List<FieldID> blockFields = blockID.fields();
        params.put("bloc.fields.type", blockFields.iterator().next().getClass().getName());

        Set<String> imports = new HashSet<String>();
        imports.add(blockID.getClass().getName());

        final StringBuilder ifStatements = new StringBuilder();
        final StringBuilder hasStatements = new StringBuilder();
        for (FieldID fieldID : blockFields) {
            imports.add(fieldID.getClass().getName());
            ifStatements.append(getStatement("BlockIfStatement.template", fieldID));
            hasStatements.append(getStatement("BlockHasStatement.template", fieldID));
        }
        params.put("bloc.fields.ifStatements", ifStatements.toString());
        params.put("bloc.fields.hasStatements", hasStatements.toString());

        params.put("imports", formatImport(imports));
        final String classTemplate = IOUtils.toString(GenerateSeleniumMojo.class
                        .getResourceAsStream("BlockClass.template"));
        return MacroProcessor.replaceProperties(classTemplate, params, 5);
    }

    private String getStatement(String template, FieldID fieldID) throws IOException, PropertyParsingException {
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("block.field.id", fieldID.getClass().getSimpleName() + "." + fieldID.toString());
        params.put("block.field.type", capitalize(BLOCK_NAME_FACTORY.getFieldName(fieldID)));
        final String classTemplate = IOUtils.toString(GenerateSeleniumMojo.class.getResourceAsStream(template));
        return MacroProcessor.replaceProperties(classTemplate, params, 5);
    }

    private String getFieldDeclarationCode(BlockID blockID, ClassLoader classLoader) throws IOException,
                    PropertyParsingException, MojoExecutionException, MojoFailureException {
        List<String> fields = new ArrayList<String>();
        for (FieldID fieldId : blockID.fields()) {
            final FieldCodeGenerator fieldFactory = getFieldCodeGenerator(fieldId, classLoader);
            if (fieldFactory == null)
                continue;
            final String code = fieldFactory.getDeclarationCode(fieldId);
            if (!isEmpty(code))
                fields.add(code);
        }
        return join(fields, "\n");
    }

    private String getFieldSetterCode(BlockID blockID, ClassLoader classLoader) throws IOException,
                    PropertyParsingException, MojoExecutionException, MojoFailureException {
        List<String> fields = new ArrayList<String>();
        for (FieldID fieldId : blockID.fields()) {
            final FieldCodeGenerator fieldFactory = getFieldCodeGenerator(fieldId, classLoader);
            if (fieldFactory == null)
                continue;
            final String code = fieldFactory.getSetterCode(fieldId, blockID);
            if (!isEmpty(code))
                fields.add(code);
        }
        return join(fields, "\n");
    }

    private String getFieldGetterCode(BlockID blockID, ClassLoader classLoader) throws IOException,
                    PropertyParsingException, MojoExecutionException, MojoFailureException {
        List<String> fields = new ArrayList<String>();
        for (FieldID fieldId : blockID.fields()) {
            final FieldCodeGenerator fieldFactory = getFieldCodeGenerator(fieldId, classLoader);
            if (fieldFactory == null)
                continue;
            final String code = fieldFactory.getGetterCode(fieldId, blockID);
            if (!isEmpty(code))
                fields.add(code);
        }
        return join(fields, "\n");
    }
}
