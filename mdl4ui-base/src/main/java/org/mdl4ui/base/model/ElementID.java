package org.mdl4ui.base.model;

import java.util.List;

public interface ElementID {
    EElementType elementType();

    ElementID[] childs();

    boolean contains(ElementID child);

    List<FieldID> fields();

    List<BlockID> blocks();

    List<GroupID> groups();
}
