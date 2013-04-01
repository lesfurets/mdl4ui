/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.maven.naming;

import static java.io.File.separatorChar;

import org.apache.commons.lang3.StringUtils;

abstract class DefaultNameFactory<T> implements NameFactory<T> {

    @Override
    public final String getClassQualifiedName(T element) {
        return getPackageName(element) + "." + getClassName(element);
    }

    @Override
    public final String getOutputFile(T blockID) {
        final String packageName = getPackageName(blockID);
        final String className = getClassName(blockID);
        return packageName.replace('.', separatorChar) + separatorChar + className;
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
        if (StringUtils.isEmpty(str)) {
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
