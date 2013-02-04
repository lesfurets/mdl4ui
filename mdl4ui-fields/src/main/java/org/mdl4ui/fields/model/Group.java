package org.mdl4ui.fields.model;

import java.util.List;

import org.mdl4ui.base.model.EElementType;
import org.mdl4ui.base.model.GroupID;

public class Group implements BlockItem {
    private final GroupID groupId;
    private final List<Field> fields;

    public Group(final GroupID groupId, List<Field> fields) {
        this.groupId = groupId;
        this.fields = fields;
    }

    @Override
    public EElementType getType() {
        return EElementType.GROUP;
    }

    public GroupID getGroupId() {
        return this.groupId;
    }

    public List<Field> getFields() {
        return fields;
    }
}
