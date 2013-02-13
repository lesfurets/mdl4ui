/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.maven;

import static java.io.File.separatorChar;
import static java.text.DateFormat.SHORT;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.ez18n.apt.macro.MacroProcessor;
import org.ez18n.apt.macro.PropertyParsingException;
import org.mdl4ui.base.model.BlockID;
import org.mdl4ui.base.model.DependencyPath;
import org.mdl4ui.base.model.ElementID;
import org.mdl4ui.base.model.FieldDependency;
import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.base.model.GroupID;
import org.mdl4ui.base.model.ScreenID;
import org.mdl4ui.maven.util.BundleFieldFactoryDelegate;

/**
 * @goal generateTests
 * @phase process-classes
 * @threadSafe
 * @requiresDependencyResolution compile
 */
public class GenerateDepsTestMojo extends AbstractDepsMojo {
    private static final String UTF_8 = "UTF-8";
    private static final String TAB_TAB = "        ";
    private static final String FIELD_DEPENDENCY = "FieldDependency";
    private static final String FIELD_ORDER = "FieldOrder";
    private static final String FIELD_MESSAGE = "FieldMessage";

    /**
     * @parameter default-value=".template"
     * @required
     */
    protected String extension;

    /**
     * @parameter expression="${project.basedir}/src/test/java"
     */
    private File outputTestDirectory;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("outputDirectory : " + outputTestDirectory.getAbsolutePath());
        final ClassLoader classLoader = getClassLoader();
        final BundleFieldFactoryDelegate bundleFactory = getBundleFactoy(getBundleFieldFactoryClass(), classLoader);
        final Map<FieldID, List<DependencyPath>> allDeepDeps = new HashMap<FieldID, List<DependencyPath>>();
        final Map<FieldID, List<FieldID>> allDeps = new HashMap<FieldID, List<FieldID>>();
        computeAllDeps(classLoader, allDeps, allDeepDeps);

        for (String fieldScreenClass : getFieldDependencyClasses()) {
            final List<FieldDependency> dependencies = getFieldDependencies(forName(fieldScreenClass, classLoader));
            if (dependencies.size() == 0)
                continue;
            final FieldDependency firstDependency = dependencies.get(0);
            getLog().info("fieldScreenClass : " + firstDependency.getClass().getName());
            try {
                final String factoryClass = testDependencyClass(firstDependency, allDeepDeps, bundleFactory);
                final File outputFile = new File(outputTestDirectory, outputFile(FIELD_DEPENDENCY, firstDependency));
                createParentFolder(outputFile);
                IOUtils.write(factoryClass, new FileOutputStream(outputFile), UTF_8);
                getLog().info("written " + outputFile.getAbsolutePath());
            } catch (FileNotFoundException e) {
                throw new MojoExecutionException(e.getMessage());
            } catch (IOException e) {
                throw new MojoExecutionException(e.getMessage());
            } catch (PropertyParsingException e) {
                throw new MojoExecutionException(e.getMessage());
            }

        }

        for (String fieldScreenClass : getScreenClasses()) {
            final List<ScreenID> screenIds = getScreenID(forName(fieldScreenClass, classLoader));
            if (screenIds.size() == 0)
                continue;
            final ScreenID firstScreenId = screenIds.get(0);
            getLog().info("fieldScreenClass : " + firstScreenId.getClass().getName());
            try {
                final String factoryClass = testOrderClass(screenIds, allDeepDeps, bundleFactory);
                final File outputFile = new File(outputTestDirectory, outputFile(FIELD_ORDER, firstScreenId));
                createParentFolder(outputFile);
                IOUtils.write(factoryClass, new FileOutputStream(outputFile), UTF_8);
                getLog().info("written " + outputFile.getAbsolutePath());
            } catch (FileNotFoundException e) {
                throw new MojoExecutionException(e.getMessage());
            } catch (IOException e) {
                throw new MojoExecutionException(e.getMessage());
            } catch (PropertyParsingException e) {
                throw new MojoExecutionException(e.getMessage());
            }

            try {
                final String factoryClass = testMessageClass(screenIds, allDeepDeps, bundleFactory);
                final File outputFile = new File(outputTestDirectory, outputFile(FIELD_MESSAGE, firstScreenId));
                createParentFolder(outputFile);
                IOUtils.write(factoryClass, new FileOutputStream(outputFile), UTF_8);
                getLog().info("written " + outputFile.getAbsolutePath());
            } catch (FileNotFoundException e) {
                throw new MojoExecutionException(e.getMessage());
            } catch (IOException e) {
                throw new MojoExecutionException(e.getMessage());
            } catch (PropertyParsingException e) {
                throw new MojoExecutionException(e.getMessage());
            }
        }
    }

    private final String outputFile(String prefix, ScreenID screenId) {
        return getPackage(screenId.getClass()).replace('.', separatorChar) + separatorChar
                        + className(prefix, screenId) + extension;
    }

    private final String outputFile(String prefix, FieldDependency dependency) {
        return getPackage(dependency.getClass()).replace('.', separatorChar) + separatorChar
                        + className(prefix, dependency) + extension;
    }

    private final String className(String prefix, ScreenID screenId) {
        return prefix + screenId.getClass().getSimpleName().substring(1).replace("Screen", "") + "Test";
    }

    private final String className(String prefix, FieldDependency dependency) {
        return prefix + dependency.getClass().getSimpleName().substring(1).replace("FieldDependency", "") + "Test";
    }

    private final String factoryClassName(ScreenID screenId) {
        return FIELD_DEPENDENCY + screenId.getClass().getSimpleName().substring(1).replace("Screen", "") + "Factory";
    }

    private final String factoryClassName(FieldDependency dependency) {
        return FIELD_DEPENDENCY + dependency.getClass().getSimpleName().substring(1).replace("FieldDependency", "")
                        + "Factory";
    }

    private String testDependencyClass(FieldDependency firstDependency, Map<FieldID, List<DependencyPath>> deepDeps,
                    BundleFieldFactoryDelegate bundleFactory) throws IOException, PropertyParsingException, MojoExecutionException {
        final Map<String, Object> params = new HashMap<String, Object>();
        final Set<String> staticImports = new HashSet<String>();
        final String packageName = firstDependency.getClass().getPackage().getName();
        params.put("process.class", getClass().getName());
        params.put("process.date", DateFormat.getDateTimeInstance(SHORT, SHORT).format(new Date()));
        params.put("package.name", getPackage(firstDependency.getClass()));
        params.put("factory.class.name", factoryClassName(firstDependency));
        params.put("target.class.name", className(FIELD_DEPENDENCY, firstDependency));
        params.put("field.dependencies.tests", testFieldDependencies(packageName, deepDeps, staticImports));
        params.put("static.imports", formatStaticImports(staticImports));
        final String classTemplate = loadTemplate("TestDependencyClass.template");
        return MacroProcessor.replaceProperties(classTemplate, params, 5);
    }

    private String testOrderClass(List<ScreenID> screenIds, Map<FieldID, List<DependencyPath>> deepDeps,
                    BundleFieldFactoryDelegate bundleFactory) throws IOException, PropertyParsingException, MojoExecutionException {
        final Map<String, Object> params = new HashMap<String, Object>();
        final Set<String> staticImports = new HashSet<String>();
        final ScreenID firstScreenId = screenIds.get(0);
        params.put("process.class", getClass().getName());
        params.put("process.date", DateFormat.getDateTimeInstance(SHORT, SHORT).format(new Date()));
        params.put("package.name", getPackage(firstScreenId.getClass()));
        params.put("target.class.name", className(FIELD_ORDER, firstScreenId));
        params.put("factory.class.name", factoryClassName(firstScreenId));
        params.put("screen.blocks.tests", testScreenBlocks(screenIds, staticImports));
        params.put("screen.blocks.order.tests", testScreenBlocksOrder(screenIds, staticImports));
        params.put("screen.fields.order.tests", testScreenFieldsOrder(screenIds, staticImports));
        params.put("block.content.order.tests", testBlockContentOrder(collectBlocks(screenIds), staticImports));
        params.put("group.content.order.tests", testGroupContentOrder(collectGroups(screenIds), staticImports));
        params.put("static.imports", formatStaticImports(staticImports));
        final String classTemplate = loadTemplate("TestOrderClass.template");
        return MacroProcessor.replaceProperties(classTemplate, params, 5);
    }

    private String testMessageClass(List<ScreenID> screenIds, Map<FieldID, List<DependencyPath>> deepDeps,
                    BundleFieldFactoryDelegate bundleFactory) throws IOException, PropertyParsingException, MojoExecutionException {
        final Map<String, Object> params = new HashMap<String, Object>();
        final Set<String> staticImports = new HashSet<String>();
        final ScreenID firstScreenId = screenIds.get(0);
        params.put("process.class", getClass().getName());
        params.put("process.date", DateFormat.getDateTimeInstance(SHORT, SHORT).format(new Date()));
        params.put("package.name", getPackage(firstScreenId.getClass()));
        params.put("target.class.name", className(FIELD_MESSAGE, firstScreenId));
        params.put("group.text.tests", testGroupText(collectGroups(screenIds), bundleFactory, staticImports));
        params.put("block.text.tests", testBlockText(collectBlocks(screenIds), bundleFactory, staticImports));
        params.put("field.text.tests", testFieldText(collectFields(screenIds), bundleFactory, staticImports));
        params.put("static.imports", formatStaticImports(staticImports));
        final String classTemplate = loadTemplate("TestMessageClass.template");
        return MacroProcessor.replaceProperties(classTemplate, params, 5);
    }

    private String testFieldDependencies(String dependencyPackage, Map<FieldID, List<DependencyPath>> deepDeps,
                    Set<String> staticImports) throws IOException, PropertyParsingException, MojoExecutionException {
        final StringBuilder methodBuilder = new StringBuilder();
        final List<Entry<FieldID, List<DependencyPath>>> entries = new ArrayList<Map.Entry<FieldID, List<DependencyPath>>>(
                        deepDeps.entrySet());
        Collections.sort(entries, new Comparator<Entry<FieldID, List<DependencyPath>>>() {
            @Override
            public int compare(Entry<FieldID, List<DependencyPath>> deps1, Entry<FieldID, List<DependencyPath>> deps2) {
                return deps1.getKey().toString().compareTo(deps2.getKey().toString());
            }
        });
        // sort entries
        for (Entry<FieldID, List<DependencyPath>> entry : entries) {
            final Map<String, Object> params = new HashMap<String, Object>();
            final String fieldIdClassName = entry.getKey().getClass().getName();
            staticImports.add(fieldIdClassName);
            params.put("field.name", entry.getKey().toString());
            params.put("field.enum.class", entry.getKey().getClass().getName());
            final StringBuilder assertBuilder = new StringBuilder();
            final List<DependencyPath> deps = entry.getValue();
            // sort deps
            for (DependencyPath dep : deps) {
                staticImports.add(dep.getFieldId().getClass().getName());
                assertBuilder.append(TAB_TAB + "assertTrue(fields.contains(");
                assertBuilder.append(dep.getFieldId().toString());
                assertBuilder.append("));\n");
            }
            assertBuilder.append(assertSize(entry.getValue().size(), "fields"));
            params.put("assert.fields", assertBuilder.toString());
            final String classTemplate = loadTemplate("TestFieldDependencies.template");
            methodBuilder.append(MacroProcessor.replaceProperties(classTemplate, params, 5));
        }
        return methodBuilder.toString();
    }

    private String testScreenBlocks(List<ScreenID> screenIds, Set<String> staticImports) throws IOException,
                    PropertyParsingException, MojoExecutionException {
        final StringBuilder methodBuilder = new StringBuilder();
        for (ScreenID screenId : screenIds) {
            final Map<String, Object> params = new HashMap<String, Object>();
            final List<BlockID> blockIds = screenId.blocks();
            params.put("screen.enum.class", screenId.getClass().getSimpleName());
            params.put("screen.name", screenId.toString());
            final StringBuilder assertBuilder = new StringBuilder();
            for (BlockID blockId : blockIds) {
                assertBuilder.append(TAB_TAB + "assertTrue(blocks.contains(");
                assertBuilder.append(blockId.toString());
                assertBuilder.append("));\n");
            }
            params.put("screen.enum.fieldCount", screenId.fields().size());
            if (blockIds.size() == 0)
                assertBuilder.append(TAB_TAB + "assertTrue(blocks.isEmpty());");
            params.put("assert.blocks", assertBuilder.toString());
            final String classTemplate = loadTemplate("TestScreenBlocks.template");
            methodBuilder.append(MacroProcessor.replaceProperties(classTemplate, params, 5));
        }
        return methodBuilder.toString();
    }

    private String testScreenBlocksOrder(List<ScreenID> screenIds, Set<String> staticImports) throws IOException,
                    PropertyParsingException, MojoExecutionException {
        final StringBuilder methodBuilder = new StringBuilder();
        for (ScreenID screenId : screenIds) {
            staticImports.add(screenId.getClass().getName());
            final Map<String, Object> params = new HashMap<String, Object>();
            params.put("screen.enum.class", screenId.getClass().getSimpleName());
            params.put("screen.name", screenId.toString());
            final StringBuilder assertBuilder = new StringBuilder();
            final List<BlockID> blocks = screenId.blocks();
            for (BlockID blockId : blocks) {
                staticImports.add(blockId.getClass().getName());
                assertBuilder.append(TAB_TAB + "assertEquals(");
                assertBuilder.append(blocks.indexOf(blockId));
                assertBuilder.append(", blocks.indexOf(");
                assertBuilder.append(blockId.toString());
                assertBuilder.append("));\n");
            }
            assertBuilder.append(assertSize(blocks.size(), "blocks"));
            params.put("assert.blocks", assertBuilder.toString());
            final String methodTemplate = loadTemplate("TestScreenBlocksOrder.template");
            methodBuilder.append(MacroProcessor.replaceProperties(methodTemplate, params, 5));
        }
        return methodBuilder.toString();
    }

    private String testScreenFieldsOrder(List<ScreenID> screenIds, Set<String> staticImports) throws IOException,
                    PropertyParsingException, MojoExecutionException {
        final StringBuilder methodBuilder = new StringBuilder();
        for (ScreenID screenId : screenIds) {
            staticImports.add(screenId.getClass().getName());
            final Map<String, Object> params = new HashMap<String, Object>();
            params.put("screen.enum.class", screenId.getClass().getSimpleName());
            params.put("screen.name", screenId.toString());
            final StringBuilder assertBuilder = new StringBuilder();
            final List<FieldID> fields = screenId.fields();
            for (FieldID field : fields) {
                staticImports.add(field.getClass().getName());
                assertBuilder.append(TAB_TAB + "assertEquals(");
                assertBuilder.append(fields.indexOf(field));
                assertBuilder.append(", fields.indexOf(");
                assertBuilder.append(field.toString());
                assertBuilder.append("));\n");
            }
            assertBuilder.append(assertSize(fields.size(), "fields"));
            params.put("assert.fields", assertBuilder.toString());
            final String methodTemplate = loadTemplate("TestScreenFieldsOrder.template");
            methodBuilder.append(MacroProcessor.replaceProperties(methodTemplate, params, 5));
        }
        return methodBuilder.toString();
    }

    private String testBlockContentOrder(List<BlockID> blockIds, Set<String> staticImports) throws IOException,
                    PropertyParsingException, MojoExecutionException {
        final StringBuilder methodBuilder = new StringBuilder();
        for (BlockID blockId : blockIds) {
            staticImports.add(blockId.getClass().getName());
            final Map<String, Object> params = new HashMap<String, Object>();
            params.put("block.enum.class", blockId.getClass().getSimpleName());
            params.put("block.name", blockId.toString());
            final StringBuilder assertBuilder = new StringBuilder();
            final List<ElementID> items = Arrays.asList(blockId.childs());
            for (ElementID item : blockId.childs()) {
                staticImports.add(item.getClass().getName());
                assertBuilder.append(TAB_TAB + "assertEquals(");
                assertBuilder.append(items.indexOf(item));
                assertBuilder.append(", blockItems.indexOf(");
                assertBuilder.append(item.toString());
                assertBuilder.append("));\n");
            }
            assertBuilder.append(assertSize(items.size(), "blockItems"));
            params.put("assert.block.items", assertBuilder.toString());
            final String methodTemplate = loadTemplate("TestBlockContentOrder.template");
            methodBuilder.append(MacroProcessor.replaceProperties(methodTemplate, params, 5));
        }
        return methodBuilder.toString();
    }

    private String testGroupContentOrder(List<GroupID> groupIds, Set<String> staticImports) throws IOException,
                    PropertyParsingException, MojoExecutionException {
        final StringBuilder methodBuilder = new StringBuilder();
        for (GroupID group : groupIds) {
            staticImports.add(group.getClass().getName());
            final Map<String, Object> params = new HashMap<String, Object>();
            staticImports.add(group.getClass().getName());
            params.put("group.enum.class", group.getClass().getSimpleName());
            params.put("group.name", group.toString());
            final StringBuilder assertBuilder = new StringBuilder();
            final List<FieldID> fields = group.fields();
            for (FieldID field : fields) {
                staticImports.add(field.getClass().getName());
                assertBuilder.append(TAB_TAB + "assertEquals(");
                assertBuilder.append(fields.indexOf(field));
                assertBuilder.append(", fields.indexOf(");
                assertBuilder.append(field.toString());
                assertBuilder.append("));\n");
            }
            assertBuilder.append(assertSize(fields.size(), "fields"));
            params.put("assert.group.items", assertBuilder.toString());
            final String methodTemplate = loadTemplate("TestGroupContentOrder.template");
            methodBuilder.append(MacroProcessor.replaceProperties(methodTemplate, params, 5));
        }
        return methodBuilder.toString();
    }

    private String testFieldText(List<FieldID> fieldIds, BundleFieldFactoryDelegate bundleFactoy, Set<String> staticImports)
                    throws MojoExecutionException, PropertyParsingException, IOException {
        final StringBuilder methodBuilder = new StringBuilder();
        for (FieldID field : fieldIds) {
            staticImports.add(field.getClass().getName());
            final Map<String, Object> params = new HashMap<String, Object>();
            params.put("field.enum.class", field.getClass().getSimpleName());
            params.put("field.name", field.toString());
            final StringBuilder assertBuilder = new StringBuilder();
            assertMessage(assertBuilder, bundleFactoy.getLabel(field), "messageLabel");
            assertMessage(assertBuilder, bundleFactoy.getHelp(field), "messageHelp");
            assertMessage(assertBuilder, bundleFactoy.getPlaceHolder(field), "messagePlaceHolder");
            params.put("assert.message", assertBuilder.toString());
            final String methodTemplate = loadTemplate("TestFieldText.template");
            methodBuilder.append(MacroProcessor.replaceProperties(methodTemplate, params, 5));
        }
        return methodBuilder.toString();
    }

    private String testBlockText(List<BlockID> blockIds, BundleFieldFactoryDelegate bundleFactoy, Set<String> staticImports)
                    throws MojoExecutionException, IOException, PropertyParsingException {
        final StringBuilder methodBuilder = new StringBuilder();
        for (BlockID block : blockIds) {
            staticImports.add(block.getClass().getName());
            final Map<String, Object> params = new HashMap<String, Object>();
            params.put("block.enum.class", block.getClass().getSimpleName());
            params.put("block.name", block.toString());
            final StringBuilder assertBuilder = new StringBuilder();
            assertMessage(assertBuilder, bundleFactoy.getLabel(block), "messageLabel");
            assertMessage(assertBuilder, bundleFactoy.getHelp(block), "messageHelp");
            assertMessage(assertBuilder, bundleFactoy.getPlaceHolder(block), "messagePlaceHolder");
            params.put("assert.message", assertBuilder.toString());
            final String methodTemplate = loadTemplate("TestBlockText.template");
            methodBuilder.append(MacroProcessor.replaceProperties(methodTemplate, params, 5));
        }
        return methodBuilder.toString();
    }

    private String testGroupText(List<GroupID> groupIds, BundleFieldFactoryDelegate bundleFactoy, Set<String> staticImports)
                    throws MojoExecutionException, IOException, PropertyParsingException {
        final StringBuilder methodBuilder = new StringBuilder();
        for (GroupID groupId : groupIds) {
            staticImports.add(groupId.getClass().getName());
            final Map<String, Object> params = new HashMap<String, Object>();
            params.put("group.enum.class", groupId.getClass().getSimpleName());
            params.put("group.name", groupId.toString());
            final StringBuilder assertBuilder = new StringBuilder();
            assertMessage(assertBuilder, bundleFactoy.getLabel(groupId), "messageLabel");
            assertMessage(assertBuilder, bundleFactoy.getHelp(groupId), "messageHelp");
            assertMessage(assertBuilder, bundleFactoy.getPlaceHolder(groupId), "messagePlaceHolder");
            params.put("assert.message", assertBuilder.toString());
            final String methodTemplate = loadTemplate("TestGroupText.template");
            methodBuilder.append(MacroProcessor.replaceProperties(methodTemplate, params, 5));
        }
        return methodBuilder.toString();
    }

    private static void assertMessage(StringBuilder assertBuilder, String message, String varName) {
        if (message != null) {
            assertBuilder.append(TAB_TAB + "assertEquals(\"");
            assertBuilder.append(message);
            assertBuilder.append("\", ");
            assertBuilder.append(varName);
            assertBuilder.append(");\n");
        } else {
            assertBuilder.append(TAB_TAB + "assertNull(");
            assertBuilder.append(varName);
            assertBuilder.append(");\n");
        }
    }

    private static String assertSize(int size, String varName) {
        final StringBuilder assertBuilder = new StringBuilder();
        assertBuilder.append(TAB_TAB + "assertEquals(");
        assertBuilder.append(size);
        assertBuilder.append(", ");
        assertBuilder.append(varName);
        assertBuilder.append(".size());\n");
        return assertBuilder.toString();
    }

}
