/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.maven;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.ez18n.apt.macro.MacroProcessor;
import org.ez18n.apt.macro.PropertyParsingException;
import org.mdl4ui.base.model.DependencyPath;
import org.mdl4ui.base.model.EElementType;
import org.mdl4ui.base.model.ElementID;
import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.base.model.ScreenID;
import org.mdl4ui.maven.util.BundleFieldFactoryDelegate;

/**
 * @goal generateModel
 * @phase process-classes
 * @threadSafe
 * @requiresDependencyResolution compile
 */
public final class GenerateDepsModelMojo extends AbstractDepsMojo {

    /**
     * @parameter expression="${project.build.directory}/generated-resources/apt"
     */
    private File outputDirectory;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("outputDirectory : " + outputDirectory.getAbsolutePath());
        final ClassLoader classLoader = getClassLoader();
        final Map<FieldID, List<DependencyPath>> allDeepDeps = new HashMap<FieldID, List<DependencyPath>>();
        final Map<FieldID, List<FieldID>> allDeps = new HashMap<FieldID, List<FieldID>>();
        computeAllDeps(classLoader, allDeps, allDeepDeps);
        final BundleFieldFactoryDelegate bundleFactory = getBundleFactoy(getBundleFieldFactoryClass(), classLoader);
        for (String fieldScreenClass : getScreenClasses()) {
            final Class<?> clazz = forName(fieldScreenClass, classLoader);
            getLog().info("fieldScreenClass : " + clazz.getName());
            final List<ScreenID> screenIds = getScreenID(clazz);
            try {
                final String xml = generateModel(screenIds, filter(allDeps, screenIds), bundleFactory);
                final File outputFile = new File(outputDirectory, getOutputFile(clazz));
                createParentFolder(outputFile);
                IOUtils.write(xml, new FileOutputStream(outputFile), "UTF-8");
                getLog().info("written " + outputFile.getAbsolutePath());
            } catch (IOException e) {
                throw new MojoExecutionException(e.getMessage());
            } catch (PropertyParsingException e) {
                throw new MojoExecutionException(e.getMessage());
            }
        }
    }

    private static final String getOutputFile(Class<?> clazz) {
        return clazz.getSimpleName() + "_dependency_model.mdxml";
    }

    private final String generateModel(List<ScreenID> screenIds, Map<FieldID, List<FieldID>> allDeps,
                    BundleFieldFactoryDelegate bundleFactory) throws IOException, PropertyParsingException, MojoExecutionException {
        final String umlModel = loadTemplate("UmlModel.template");
        final Map<String, Object> conf = new HashMap<String, Object>();

        conf.put("model.content", generatePackage(screenIds, allDeps, bundleFactory));
        return MacroProcessor.replaceProperties(umlModel, conf, 5);
    }

    private final String generatePackage(List<ScreenID> screenIds, Map<FieldID, List<FieldID>> allDeps,
                    BundleFieldFactoryDelegate bundleFactory) throws IOException, PropertyParsingException, MojoExecutionException {
        final String umlClass = loadTemplate("UmlClass.template");
        final StringBuilder builder = new StringBuilder();
        for (ScreenID screenId : screenIds)
            builder.append(generatePackage(screenId, allDeps, bundleFactory));
        builder.append(generateClasses(collectFields(screenIds), bundleFactory, umlClass));
        return builder.toString();
    }

    private static <E extends ElementID> String generateClasses(List<E> elementIds, BundleFieldFactoryDelegate bundleFactory,
                    String umlClass) throws IOException, MojoExecutionException, PropertyParsingException {
        final StringBuilder builder = new StringBuilder();
        for (E elementId : elementIds) {
            final String label = bundleFactory.getLabel(elementId);
            if (label == null)
                continue;
            final Map<String, Object> conf = new HashMap<String, Object>();
            conf.put("class.id", elementId.toString() + "_msg");
            conf.put("class.name", StringEscapeUtils.escapeXml(label));
            builder.append(MacroProcessor.replaceProperties(umlClass, conf, 5));
        }
        return builder.toString();
    }

    private final String generatePackage(ElementID parentId, Map<FieldID, List<FieldID>> allDeps,
                    BundleFieldFactoryDelegate bundleFactory) throws IOException, MojoExecutionException, PropertyParsingException {
        final String umlPackage = loadTemplate("UmlPackage.template");
        final Map<String, Object> packageConf = new HashMap<String, Object>();
        packageConf.put("package.id", parentId.toString());
        packageConf.put("package.name", label(parentId, bundleFactory));
        packageConf.put("class.id", label(parentId, bundleFactory));
        final StringBuilder builder = new StringBuilder();
        for (ElementID childId : parentId.childs()) {
            if (childId.elementType() == EElementType.GROUP || childId.elementType() == EElementType.BLOCK)
                builder.append(generatePackage(childId, allDeps, bundleFactory));
            else
                builder.append(generateField(childId, allDeps, bundleFactory));
        }
        packageConf.put("package.content", builder);
        return MacroProcessor.replaceProperties(umlPackage, packageConf, 5);
    }

    private final String generateField(ElementID fieldId, Map<FieldID, List<FieldID>> allDeps,
                    BundleFieldFactoryDelegate bundleFactory) throws IOException, MojoExecutionException, PropertyParsingException {
        final String umlInstance = loadTemplate("UmlInstanceSpecification.template");
        final String umlInstanceWithClassifier = loadTemplate("UmlInstanceSpecificationWithClassifier.template");
        final String umlDependency = loadTemplate("UmlDependency.template");
        final StringBuilder builder = new StringBuilder();
        final String label = bundleFactory.getLabel(fieldId);
        final Map<String, Object> conf = new HashMap<String, Object>();
        conf.put("instance.id", fieldId.toString());
        conf.put("instance.name", fieldId.toString());
        conf.put("class.id", fieldId.toString() + "_msg");
        final String template = label != null ? umlInstanceWithClassifier : umlInstance;
        builder.append(MacroProcessor.replaceProperties(template, conf, 5));
        final List<FieldID> deps = deps(allDeps, fieldId);
        for (FieldID targetField : deps) {
            final Map<String, Object> confDeps = new HashMap<String, Object>();
            confDeps.put("dependency.id", fieldId.toString() + '_' + targetField.toString());
            confDeps.put("client.id", fieldId.toString());
            confDeps.put("supplier.id", targetField.toString());
            builder.append(MacroProcessor.replaceProperties(umlDependency, confDeps, 5));
        }
        return builder.toString();
    }

    private static final <A> Map<FieldID, List<A>> filter(Map<FieldID, List<A>> map, List<ScreenID> screenIds) {
        final List<FieldID> collectFields = collectFields(screenIds);
        final Map<FieldID, List<A>> filtered = new HashMap<FieldID, List<A>>();
        for (Map.Entry<FieldID, List<A>> entry : map.entrySet()) {
            if (!collectFields.contains(entry.getKey()))
                continue;
            filtered.put(entry.getKey(), entry.getValue());
        }
        return filtered;
    }

    private static final List<FieldID> deps(Map<FieldID, List<FieldID>> allDeps, ElementID elementId) {
        final List<FieldID> fieldIds = new ArrayList<FieldID>();
        for (Entry<FieldID, List<FieldID>> depEntry : allDeps.entrySet()) {
            if (depEntry.getValue().contains(elementId))
                fieldIds.add(depEntry.getKey());
        }
        return fieldIds;
    }

    private static final String label(ElementID elementId, BundleFieldFactoryDelegate bundleFactoy) throws MojoExecutionException {
        if (StringUtils.isEmpty(bundleFactoy.getLabel(elementId)))
            return elementId.toString();
        return elementId.toString() + " : " + bundleFactoy.getLabel(elementId);
    }
}
