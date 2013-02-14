package org.mdl4ui.fields.model;

import java.util.List;

import org.mdl4ui.base.model.EElementType;

public interface Element {
    EElementType elementType();

    List<Element> childs();

    boolean contains(Element child);

    List<Field> fields();

    List<Block> blocks();

    List<Group> groups();
}
