/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.maven.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.maven.plugin.MojoExecutionException;
import org.mdl4ui.base.model.ElementID;

public final class BundleFactory {
    private final Class<?> clazz;
    private final Object instance;

    public BundleFactory(Class<?> clazz, Object instance) {
        this.clazz = clazz;
        this.instance = instance;
    }

    public String getLabel(ElementID elementId) throws MojoExecutionException {
        return get("getLabel", elementId, clazz, ElementID.class, instance);
    }

    public String getHelp(ElementID elementId) throws MojoExecutionException {
        return get("getHelp", elementId, clazz, ElementID.class, instance);
    }

    public String getPlaceHolder(ElementID elementId) throws MojoExecutionException {
        return get("getPlaceHolder", elementId, clazz, ElementID.class, instance);
    }

    private static final <A> String get(String methodName, A id, Class<?> clazz, Class<?> idType, Object instance)
                    throws MojoExecutionException {
        try {
            return (String) clazz.getMethod(methodName, idType).invoke(instance, id);
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
}
