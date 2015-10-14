package org.mdl4ui.base.model;

public interface FieldID extends ElementID {

    FieldType type();

    TagId[] tags();

    int position();
}
