package org.mdl4ui.fields.model;

import java.util.ArrayList;
import java.util.List;

import org.mdl4ui.base.model.EElementType;

public abstract class ElementImpl implements Element {

    @Override
    public boolean contains(Element child) {
        if (this == child)
            return true;
        if (childs() == null)
            return false;
        for (Element childRec : childs())
            if (childRec.contains(child))
                return true;
        return false;
    }

    @Override
    public List<Field> fields() {
        final List<Field> fields = new ArrayList<Field>();
        collectRec(fields, EElementType.FIELD);
        return fields;
    }

    @Override
    public List<Block> blocks() {
        final List<Block> blocks = new ArrayList<Block>();
        collectRec(blocks, EElementType.BLOCK);
        return blocks;
    }

    @Override
    public List<Group> groups() {
        final List<Group> groups = new ArrayList<Group>();
        collectRec(groups, EElementType.GROUP);
        return groups;
    }

    @SuppressWarnings("unchecked")
    <E extends Element> void collectRec(List<E> elements, EElementType type) {
        if (elementType() == type)
            elements.add((E) this);
        if (childs() == null)
            return;
        for (Element child : childs())
            ((ElementImpl) child).collectRec(elements, type);
    }
}
