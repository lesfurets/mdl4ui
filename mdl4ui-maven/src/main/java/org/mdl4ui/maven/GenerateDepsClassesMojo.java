/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.maven;

import static java.text.DateFormat.SHORT;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.ez18n.apt.macro.MacroProcessor;
import org.ez18n.apt.macro.PropertyParsingException;
import org.mdl4ui.base.model.DependencyPath;
import org.mdl4ui.base.model.FieldID;

/**
 * @goal generate
 * @phase process-sources
 * @threadSafe
 * @requiresDependencyResolution compile
 */
public final class GenerateDepsClassesMojo extends AbstractDepsMojo {

    /**
     * @parameter expression="${basedir}/src/generated/java"
     */
    private File outputDirectory;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("outputDirectory : " + outputDirectory.getAbsolutePath());
        final ClassLoader classLoader = getClassLoader();
        final Map<FieldID, List<DependencyPath>> allDeepDeps = new HashMap<FieldID, List<DependencyPath>>();
        final Map<FieldID, List<FieldID>> allDeps = new HashMap<FieldID, List<FieldID>>();
        computeAllDeps(classLoader, allDeps, allDeepDeps);
        for (String fieldDependencyClass : getFieldDependencyClasses()) {
            final Class<?> dependencyClass = forName(fieldDependencyClass, classLoader);
            getLog().info("fieldDependencyClass : " + dependencyClass.getName());
            try {
                final String factoryClass = formatFactoryClass(dependencyClass, allDeepDeps);
                final File outputFile = new File(outputDirectory, getOutputFile(dependencyClass));
                createParentFolder(outputFile);
                IOUtils.write(factoryClass, new FileOutputStream(outputFile), "UTF-8");
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

    private final String getOutputFile(Class<?> clazz) {
        return getPackage(clazz).replace('.', File.separatorChar) + File.separatorChar + className(clazz) + ".java";
    }

    private final String enumName(Class<?> clazz) {
        return "EFieldDeepDependency" + clazz.getSimpleName().substring(1).replace("FieldDependency", "");
    }

    private final String className(Class<?> clazz) {
        return "FieldDependency" + clazz.getSimpleName().substring(1).replace("FieldDependency", "") + "Factory";
    }

    private String formatFactoryClass(Class<?> dependencyClass, Map<FieldID, List<DependencyPath>> deepDeps)
                    throws IOException, PropertyParsingException, MojoExecutionException {
        final Set<String> staticImports = new HashSet<String>();
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("process.class", getClass().getName());
        params.put("process.date", DateFormat.getDateTimeInstance(SHORT, SHORT).format(new Date()));
        params.put("package.name", getPackage(dependencyClass));
        params.put("dep.enum.constants", formatDepEnumConstants(dependencyClass, deepDeps, staticImports));
        params.put("static.imports", formatStaticImports(staticImports));
        params.put("target.class.name", className(dependencyClass));
        params.put("target.dep.enum.name", enumName(dependencyClass));
        final String classTemplate = loadTemplate("FieldDependencyFactory.template");
        return MacroProcessor.replaceProperties(classTemplate, params, 5);
    }

    /**
     * Genereate a code fragment like that <tt>
     *     DEP_PRI_CTRL(PRI_CTRL, //
     *              new DependencyPath(PRI_CTRL_DAT, PRI_CTRL, PRI_CTRL_DAT), //
     *              new DependencyPath(PRI_CTRL_TAUX, PRI_CTRL, PRI_CTRL_TAUX), //
     *              new DependencyPath(PRI_CTRL_MOD, PRI_CTRL, PRI_CTRL_MOD), //
     *              new DependencyPath(PRI_CTRL_ACC, PRI_CTRL, PRI_CTRL_ACC), //
     *              new DependencyPath(PRI_CTRL_CIRC, PRI_CTRL, PRI_CTRL_CIRC), //
     *              new DependencyPath(PRI_CTRL_ANN, PRI_CTRL, PRI_CTRL_ANN), //
     *              new DependencyPath(PRI_CTRL_NBR, PRI_CTRL, PRI_CTRL_NBR)), //
     */
    private String formatDepEnumConstants(Class<?> dependencyClass, Map<FieldID, List<DependencyPath>> deepDeps,
                    Set<String> staticImports) {
        final StringBuilder builder = new StringBuilder();
        final Iterator<Entry<FieldID, List<DependencyPath>>> it = deepDeps.entrySet().iterator();
        while (it.hasNext()) {
            final Entry<FieldID, List<DependencyPath>> entry = it.next();
            final String fieldIdClassName = entry.getKey().getClass().getName();
            staticImports.add(fieldIdClassName);
            builder.append("DEP_");
            builder.append(entry.getKey().toString());
            builder.append("(");
            builder.append(entry.getKey());
            if (entry.getValue() != null && !entry.getValue().isEmpty()) {
                builder.append(", //\n");
                final Iterator<DependencyPath> itDeps = entry.getValue().iterator();
                while (itDeps.hasNext()) {
                    final DependencyPath path = itDeps.next();
                    builder.append("                        ");
                    builder.append("new ");
                    builder.append(DependencyPath.class.getSimpleName());
                    builder.append("(");
                    builder.append(path.getFieldId());
                    staticImports.add(path.getFieldId().getClass().getName());
                    if (path.getPath().length > 0)
                        builder.append(", ");
                    for (int i = 0; i < path.getPath().length; i++) {
                        builder.append(path.getPath()[i]);
                        if (i < path.getPath().length - 1)
                            builder.append(", ");
                    }
                    builder.append(")");
                    if (itDeps.hasNext())
                        builder.append(", //\n");
                }
            }
            builder.append("), //");
            if (it.hasNext()) {
                builder.append("\n");
                builder.append("        ");
            }
        }
        return builder.toString();
    }
}
