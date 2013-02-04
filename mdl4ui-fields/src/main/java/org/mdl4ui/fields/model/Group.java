package org.mdl4ui.fields.model;

import java.util.List;

import org.mdl4ui.base.model.EElementType;
import org.mdl4ui.base.model.GroupID;

public class Group implements BlockItem {
    private final GroupID groupID;
    private final List<Field> fields;

    public Group(final GroupID groupID, List<Field> fields) {
        this.groupID = groupID;
        this.fields = fields;
    }

    @Override
    public EElementType getType() {
        return EElementType.GROUP;
    }

    public GroupID getGroupID() {
        return groupID;
    }

    public List<Field> getFields() {
        return fields;
    }
}
