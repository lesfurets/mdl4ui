/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.maven.naming;

public interface NameFactory<T> {
    String getClassName(T element);

    String getClassQualifiedName(T element);

    String getPackageName(T element);

    String getOutputFile(T element);
}
