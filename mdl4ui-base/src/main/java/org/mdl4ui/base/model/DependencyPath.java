/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.base.model;

import java.util.List;

public final class DependencyPath {
    private final FieldID fieldId;
    private final FieldID[] path;

    public DependencyPath(FieldID fieldId, List<FieldID> depPath) {
        this(fieldId, depPath.toArray(new FieldID[depPath.size()]));
    }

    public DependencyPath(FieldID fieldId, FieldID... depPath) {
        this.fieldId = fieldId;
        this.path = depPath;
    }

    public FieldID getFieldId() {
        return fieldId;
    }

    public FieldID[] getPath() {
        return path;
    }
}
