package org.mdl4ui.base.model;

import java.util.ArrayList;
import java.util.List;

public final class ElementIDImpl {
    public static boolean containsRec(ElementID elementId, ElementID childId) {
        if (elementId == null)
            return false;
        if (elementId == childId)
            return true;
        if (elementId.childs() == null)
            return false;
        for (ElementID childRec : elementId.childs())
            if (containsRec(childRec, childId))
                return true;
        return false;
    }

    public static List<FieldID> collectFields(ElementID elementId) {
        final List<FieldID> fields = new ArrayList<FieldID>();
        collectRec(elementId, fields, EElementType.FIELD);
        return fields;
    }

    public static List<BlockID> collectBlocks(ElementID elementId) {
        final List<BlockID> blocks = new ArrayList<BlockID>();
        collectRec(elementId, blocks, EElementType.BLOCK);
        return blocks;
    }

    public static List<GroupID> collectGroups(ElementID elementId) {
        final List<GroupID> groups = new ArrayList<GroupID>();
        collectRec(elementId, groups, EElementType.GROUP);
        return groups;
    }

    @SuppressWarnings("unchecked")
    static <E extends ElementID> void collectRec(ElementID elementId, List<E> elements, EElementType type) {
        if (elementId == null)
            return;
        if (elementId.elementType() == type)
            elements.add((E) elementId);
        if (elementId.childs() == null)
            return;
        for (ElementID child : elementId.childs())
            collectRec(child, elements, type);
    }
}
