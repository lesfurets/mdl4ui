/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.maven;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.util.*;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.*;
import org.apache.maven.project.MavenProject;
import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.maven.field.*;

abstract class AbstractSeleniumMojo extends AbstractMojo {

    /**
     * @parameter
     * @required
     */
    private List<String> screenClasses;

    /**
     * @parameter default-value="${project.build.outputDirectory}"
     * @required
     */
    protected File buildDirectory;

    /**
     * @parameter default-value="${project}"
     * @required
     * @readonly
     */
    protected MavenProject project;

    /**
     * Set this to "true" to bypass unit tests entirely. Its use is NOT RECOMMENDED, especially if you enable it using
     * the "maven.test.skip" property, because maven.test.skip disables both running the tests and compiling the tests.
     * Consider using the <code>skipTests</code> parameter instead.
     * 
     * @parameter default-value="false" expression="${maven.test.skip}"
     */
    private boolean skip;

    /**
     * @parameter default-value="${project.build.testSourceDirectory}"
     * @required
     */
    private File outputTestDirectory;

    List<String> getScreenClasses() {
        return screenClasses;
    }

    File getOutputTestDirectory() {
        return outputTestDirectory;
    }

    boolean skip() {
        return skip;
    }

    final ClassLoader getClassLoader(boolean includeTest) throws MojoFailureException {
        final List<String> classpathFiles;
        try {
            classpathFiles = project.getCompileClasspathElements();
            if (includeTest) {
                classpathFiles.addAll(project.getTestClasspathElements());
            }
        } catch (DependencyResolutionRequiredException e) {
            throw new MojoFailureException(e.getMessage());
        }
        final URL[] urls = new URL[classpathFiles.size() + 1];
        try {
            for (int i = 0; i < classpathFiles.size(); ++i) {
                getLog().debug(classpathFiles.get(i));
                urls[i] = new File(classpathFiles.get(i)).toURI().toURL();
            }
            urls[classpathFiles.size()] = new File(buildDirectory + "/classes").toURI().toURL();
        } catch (MalformedURLException e) {
            throw new MojoFailureException(e.getMessage());
        }
        return new URLClassLoader(urls, Thread.currentThread().getContextClassLoader());
    }

    static final void createParentFolder(File file) {
        if (file.isDirectory())
            file.mkdirs();
        else
            file.getParentFile().mkdirs();
    }

    static Class<?> forName(String className, ClassLoader classLoader) throws MojoExecutionException {
        try {
            return Class.forName(className, true, classLoader);
        } catch (ClassNotFoundException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    static final <T> List<T> enumValues(List<String> classes, ClassLoader classLoader) throws MojoExecutionException {
        try {
            List<T> values = new ArrayList<T>();
            for (String className : classes) {
                final Class<?> clazz = forName(className, classLoader);
                values.addAll(Arrays.asList((T[]) clazz.getMethod("values").invoke(null)));
            }
            return values;
        } catch (IllegalArgumentException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        } catch (SecurityException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }
    }

    static final String formatImport(Set<String> classes) {
        StringBuilder imports = new StringBuilder();
        for (String clazz : classes) {
            imports.append("import ");
            imports.append(clazz);
            imports.append(";\n");
        }
        return imports.toString();
    }

    final FieldCodeGenerator getFieldCodeGenerator(FieldID fieldId, ClassLoader classLoader)
                    throws MojoExecutionException, MojoFailureException {
        switch (fieldId.type()) {
            case LISTBOX:
                return new ListboxCodeGenerator();
            case TEXTBOX:
            case PASSWORD:
            case NUMERIC:
                return new TextboxCodeGenerator();
            case DATE:
                return new DateCodeGenerator();
            case CHECKBOX_GROUP:
                return new CheckboxGroupCodeGenerator();
            case RADIO_GROUP:
                return new RadioGroupCodeGenerator();
            default:
                throw new MojoExecutionException("No FieldFactory found for field " + fieldId + " of type "
                                + fieldId.type());
        }
    }

    void ensureOutputTestDirectoryExists() {
        if (outputTestDirectory == null) {
            return;
        }
        final File f = outputTestDirectory;
        if (!f.exists()) {
            f.mkdirs();
        }
    }
}
