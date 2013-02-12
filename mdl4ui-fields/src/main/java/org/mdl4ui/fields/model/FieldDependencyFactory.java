package org.mdl4ui.fields.model;

import org.mdl4ui.base.model.FieldID;

public interface FieldDependencyFactory {

    FieldID[] get(FieldID fieldId);
}
