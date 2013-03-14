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
        collectRec(elementId, fields, ElementType.FIELD);
        return (List<F>) fields;
    }

    @SuppressWarnings("unchecked")
    public static <B, E extends UIElement<E, ?, ?, B>> List<B> collectBlocks(E elementId) {
        final List<E> blocks = new ArrayList<E>();
        collectRec(elementId, blocks, ElementType.BLOCK);
        return (List<B>) blocks;
    }

    @SuppressWarnings("unchecked")
    public static <G, E extends UIElement<E, ?, G, ?>> List<G> collectGroups(E elementId) {
        final List<E> groups = new ArrayList<E>();
        collectRec(elementId, groups, ElementType.GROUP);
        return (List<G>) groups;
    }

    static <E extends UIElement<E, ?, ?, ?>> void collectRec(E elementId, List<E> elements, ElementType type) {
        if (elementId == null)
            return;
        if (elementId.elementType() == type)
            elements.add(elementId);
        if (elementId.childs() == null)
            return;
        for (E child : elementId.childs())
            collectRec(child, elements, type);
    }

    @SuppressWarnings("unchecked")
    public static <T, E extends UIElement<E, ?, ?, ?>> T nextChild(E screen, T childType) {
        final int index = screen.childs().indexOf(childType);
        if (index < screen.childs().size() - 1) {
            return (T) screen.childs().get(index + 1);
        } else {
            return null;
        }
    }
}
