package org.mdl4ui.base.model;

import java.util.List;

/**
 * @param <E> the {@link UIElement} type
 * @param <F> the field type
 * @param <G> the group type
 * @param <B> the block type
 */
public interface UIElement<E, F, G, B> {
    ElementType elementType();

    List<E> childs();

    boolean contains(E child);

    List<F> fields();

    List<B> blocks();

    List<G> groups();
}
