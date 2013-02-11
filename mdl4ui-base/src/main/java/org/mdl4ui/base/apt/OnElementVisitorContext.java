package org.mdl4ui.base.apt;

import java.util.HashSet;
import java.util.Set;

import javax.lang.model.element.Element;

final class OnElementVisitorContext {

    private final Set<Element> elementIds = new HashSet<Element>();

    public Set<Element> getElementIds() {
        return this.elementIds;
    }
}
