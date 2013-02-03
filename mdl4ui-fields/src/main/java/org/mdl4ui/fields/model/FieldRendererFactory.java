package org.mdl4ui.fields.model;

import org.mdl4ui.base.model.FieldID;

public interface FieldRendererFactory {

    FieldRenderer<?> get(FieldID fieldID);
}
