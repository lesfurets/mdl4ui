package org.mdl4ui.base.model;

import java.util.ArrayList;
import java.util.List;

public final class UIElementImpl {

    public static <E extends UIElement<E, ?, ?, ?>> boolean containsRec(E elementId, E childId) {
        if (elementId == null)
            return false;
        if (elementId == childId)
            return true;
        if (elementId.childs() == null)
            return false;
        for (E childRec : elementId.childs())
            if (containsRec(childRec, childId))
                return true;
        return false;
    }

    @SuppressWarnings("unchecked")
    public static <F, E extends UIElement<E, F, ?, ?>> List<F> collectFields(E elementId) {
        final List<E> fields = new ArrayList<E>();
        collectRec(elementId, fields, EElementType.FIELD);
        return (List<F>) fields;
    }

    @SuppressWarnings("unchecked")
    public static <B, E extends UIElement<E, ?, ?, B>> List<B> collectBlocks(E elementId) {
        final List<E> blocks = new ArrayList<E>();
        collectRec(elementId, blocks, EElementType.BLOCK);
        return (List<B>) blocks;
    }

    @SuppressWarnings("unchecked")
    public static <G, E extends UIElement<E, ?, G, ?>> List<G> collectGroups(E elementId) {
        final List<E> groups = new ArrayList<E>();
        collectRec(elementId, groups, EElementType.GROUP);
        return (List<G>) groups;
    }

    static <E extends UIElement<E, ?, ?, ?>> void collectRec(E elementId, List<E> elements, EElementType type) {
        if (elementId == null)
            return;
        if (elementId.elementType() == type)
            elements.add(elementId);
        if (elementId.childs() == null)
            return;
        for (E child : elementId.childs())
            collectRec(child, elements, type);
    }
}
