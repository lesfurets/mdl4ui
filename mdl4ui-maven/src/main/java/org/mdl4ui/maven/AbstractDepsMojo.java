/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.maven;

import static java.util.Arrays.asList;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.util.*;

import org.apache.commons.io.IOUtils;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.*;
import org.apache.maven.project.MavenProject;
import org.mdl4ui.base.model.*;
import org.mdl4ui.maven.util.BundleFieldFactoryDelegate;

abstract class AbstractDepsMojo extends AbstractMojo {
    /**
     * @parameter
     * @required
     */
    private List<String> fieldDependencyClasses;
    /**
     * @parameter
     * @required
     */
    private List<String> screenClasses;

    /**
     * @parameter default-value="${project.build.outputDirectory}"
     * @required
     */
    private File buildDirectory;

    /**
     * @parameter default-value="${project}"
     * @required
     * @readonly
     */
    protected MavenProject project;

    /**
     * @parameter default-value="org.mdl4ui.fields.sample.BundleFieldFactory"
     * @required
     */
    private String bundleFieldFactoryClass;

    List<String> getFieldDependencyClasses() {
        return fieldDependencyClasses;
    }

    List<String> getScreenClasses() {
        return screenClasses;
    }

    String getBundleFieldFactoryClass() {
        return bundleFieldFactoryClass;
    }

    final List<ScreenID> filterScreenClass(String packageName, ClassLoader classLoader) throws MojoExecutionException {
        for (String screenClassName : screenClasses) {
            if (!screenClassName.startsWith(packageName))
                continue;
            final Class<?> screenClass = forName(screenClassName, classLoader);
            return getScreenID(screenClass);
        }
        throw new IllegalArgumentException(packageName);
    }

    final ClassLoader getClassLoader() throws MojoFailureException {
        final List<?> classpathFiles;
        try {
            classpathFiles = project.getCompileClasspathElements();
        } catch (DependencyResolutionRequiredException e) {
            throw new MojoFailureException(e.getMessage());
        }
        final URL[] urls = new URL[classpathFiles.size() + 1];
        try {
            for (int i = 0; i < classpathFiles.size(); ++i) {
                getLog().debug((String) classpathFiles.get(i));
                urls[i] = new File((String) classpathFiles.get(i)).toURI().toURL();
            }
            urls[classpathFiles.size()] = new File(buildDirectory + "/classes").toURI().toURL();
        } catch (MalformedURLException e) {
            throw new MojoFailureException(e.getMessage());
        }
        return new URLClassLoader(urls, Thread.currentThread().getContextClassLoader());
    }

    final void computeAllDeps(ClassLoader classLoader, Map<FieldID, List<FieldID>> allDeps,
                    Map<FieldID, List<DependencyPath>> allDeepDeps) throws MojoExecutionException {
        for (String fieldDependencyClass : getFieldDependencyClasses()) {
            final Class<?> clazz = forName(fieldDependencyClass, classLoader);
            final List<FieldDependency> dependencies = getFieldDependencies(clazz);
            for (FieldDependency dependency : dependencies) {
                if (dependency.from() == null)
                    continue;
                if (dependency.to() == null)
                    continue;
                if (dependency.to().length == 0)
                    continue;
                allDeps.put(dependency.from(), Arrays.asList(dependency.to()));
            }
            for (FieldDependency dependency : dependencies) {
                if (dependency.from() == null)
                    continue;
                if (dependency.to() == null)
                    continue;
                if (dependency.to().length == 0)
                    continue;
                final LinkedList<FieldID> path = new LinkedList<FieldID>();
                path.addLast(dependency.from());
                final List<DependencyPath> deepDeps = new ArrayList<DependencyPath>();
                computeDeepDeps(dependency.from(), path, allDeps, deepDeps, 0);
                allDeepDeps.put(dependency.from(), deepDeps);
            }
        }
    }

    final void computeDeepDeps(FieldID fieldId, LinkedList<FieldID> path, Map<FieldID, List<FieldID>> allDeps,
                    List<DependencyPath> deepDeps, int depth) {
        if (depth > 10)
            return;
        final List<FieldID> deps = allDeps.get(fieldId);
        if (deps == null)
            return;
        for (FieldID dep : deps) {
            path.addLast(dep);
            final DependencyPath existingDep = get(deepDeps, dep);
            if (existingDep == null) {
                if (path.getFirst() == path.getLast()) {
                    // detect potential cycle in dependencies graph
                    continue;
                }
                deepDeps.add(new DependencyPath(dep, path));
            } else {
                // detect potential multiple path to the same field
                List<FieldID> existingPath = asList(existingDep.getPath());
                if (path.size() != existingPath.size()
                                && (path.containsAll(existingPath) || existingPath.containsAll(path))) {
                    getLog().warn(path + " dependency " + dep + " aleady added to " + path.getFirst() + " with path "
                                    + existingPath);
                    return;
                }
            }
            path.removeLast();
        }
        for (FieldID dep : deps) {
            path.addLast(dep);
            computeDeepDeps(dep, path, allDeps, deepDeps, depth + 1);
            path.removeLast();
        }
    }

    static DependencyPath get(List<DependencyPath> deps, FieldID fieldId) {
        for (DependencyPath dep : deps) {
            if (dep.getFieldId().equals(fieldId))
                return dep;
        }
        return null;
    }

    static final void createParentFolder(File file) {
        if (file.isDirectory())
            file.mkdirs();
        else
            file.getParentFile().mkdirs();
    }

    static BundleFieldFactoryDelegate getBundleFactoy(String bundleFactoryClass, ClassLoader classLoader)
                    throws MojoExecutionException {
        final Class<?> clazz = forName(bundleFactoryClass, classLoader);
        try {
            return new BundleFieldFactoryDelegate(clazz, clazz.getField("INSTANCE").get(null));
        } catch (IllegalArgumentException e) {
            throw new MojoExecutionException(e.getMessage());
        } catch (SecurityException e) {
            throw new MojoExecutionException(e.getMessage());
        } catch (IllegalAccessException e) {
            throw new MojoExecutionException(e.getMessage());
        } catch (NoSuchFieldException e) {
            throw new MojoExecutionException(e.getMessage());
        }
    }

    static List<ScreenID> getScreenID(Class<?> fieldScreenClass) throws MojoExecutionException {
        try {
            final List<ScreenID> screens = Arrays
                            .asList((ScreenID[]) fieldScreenClass.getMethod("values").invoke(null));
            Collections.sort(screens, new Comparator<ScreenID>() {
                @Override
                public int compare(ScreenID s1, ScreenID s2) {
                    return s1.toString().compareTo(s2.toString());
                }
            });
            return screens;
        } catch (IllegalArgumentException e) {
            throw new MojoExecutionException(e.getMessage());
        } catch (SecurityException e) {
            throw new MojoExecutionException(e.getMessage());
        } catch (IllegalAccessException e) {
            throw new MojoExecutionException(e.getMessage());
        } catch (InvocationTargetException e) {
            throw new MojoExecutionException(e.getMessage());
        } catch (NoSuchMethodException e) {
            throw new MojoExecutionException(e.getMessage());
        }
    }

    static List<FieldDependency> getFieldDependencies(Class<?> fieldDependencyClass) throws MojoExecutionException {
        try {
            final List<FieldDependency> deps = Arrays.asList((FieldDependency[]) fieldDependencyClass.getMethod(
                            "values").invoke(null));
            Collections.sort(deps, new Comparator<FieldDependency>() {
                @Override
                public int compare(FieldDependency b1, FieldDependency b2) {
                    return b1.toString().compareTo(b2.toString());
                }
            });
            return deps;
        } catch (IllegalArgumentException e) {
            throw new MojoExecutionException(e.getMessage());
        } catch (SecurityException e) {
            throw new MojoExecutionException(e.getMessage());
        } catch (IllegalAccessException e) {
            throw new MojoExecutionException(e.getMessage());
        } catch (InvocationTargetException e) {
            throw new MojoExecutionException(e.getMessage());
        } catch (NoSuchMethodException e) {
            throw new MojoExecutionException(e.getMessage());
        }
    }

    static Class<?> forName(String className, ClassLoader classLoader) throws MojoExecutionException {
        try {
            return Class.forName(className, true, classLoader);
        } catch (ClassNotFoundException e) {
            throw new MojoExecutionException(e.getMessage());
        }
    }

    static final String formatStaticImports(Set<String> staticImports) {
        final StringBuilder builder = new StringBuilder();
        for (String fqcn : staticImports) {
            builder.append("import static ");
            builder.append(fqcn);
            builder.append(".*;\n");
        }
        return builder.toString();
    }

    String loadTemplate(String templateFile) throws IOException {
        return IOUtils.toString(getClass().getResourceAsStream(templateFile));
    }

    static final List<FieldID> collectFields(List<ScreenID> screenIds) {
        final List<FieldID> fieldIds = new ArrayList<FieldID>();
        for (ScreenID screenId : screenIds) {
            final List<FieldID> screenFieldIds = screenId.fields();
            for (FieldID fieldId : screenFieldIds) {
                if (fieldIds.contains(fieldId))
                    continue;
                fieldIds.add(fieldId);
            }
        }
        return fieldIds;
    }

    static final List<GroupID> collectGroups(List<ScreenID> screenIds) {
        final List<GroupID> groupIds = new ArrayList<GroupID>();
        for (ScreenID screenId : screenIds) {
            final List<GroupID> screenGroupIds = screenId.groups();
            for (GroupID groupId : screenGroupIds) {
                if (groupIds.contains(groupId))
                    continue;
                groupIds.add(groupId);
            }
        }
        return groupIds;
    }

    static final List<BlockID> collectBlocks(List<ScreenID> screenIds) {
        final List<BlockID> blockIds = new ArrayList<BlockID>();
        for (ScreenID screenId : screenIds) {
            final List<BlockID> screenBlockIds = screenId.blocks();
            for (BlockID blockId : screenBlockIds) {
                if (blockIds.contains(blockId))
                    continue;
                blockIds.add(blockId);
            }
        }
        return blockIds;
    }

    static final String getPackage(Class<?> clazz) {
        return clazz.getPackage().getName();
    }

}
