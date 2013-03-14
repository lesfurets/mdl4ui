package org.mdl4ui.fields.model;

import static org.mdl4ui.base.model.UIElementImpl.containsRec;

import java.util.ArrayList;
import java.util.List;

import org.mdl4ui.base.model.ElementType;
import org.mdl4ui.base.model.GroupID;
import org.mdl4ui.base.model.UIElementImpl;

public class Group implements Element {
    private final GroupID groupID;
    private final List<Field> fields;

    public Group(final GroupID groupID, List<Field> fields) {
        this.groupID = groupID;
        this.fields = fields;
    }

    public GroupID getGroupID() {
        return groupID;
    }

    @Override
    public ElementType elementType() {
        return ElementType.GROUP;
    }

    @Override
    public List<Element> childs() {
        return new ArrayList<Element>(fields);
    }

    @Override
    public boolean contains(Element child) {
        return containsRec(this, child);
    }

    @Override
    public List<Field> fields() {
        return UIElementImpl.<Field, Element> collectFields(this);
    }

    @Override
    public List<Block> blocks() {
        return UIElementImpl.<Block, Element> collectBlocks(this);
    }

    @Override
    public List<Group> groups() {
        return UIElementImpl.<Group, Element> collectGroups(this);
    }
}
