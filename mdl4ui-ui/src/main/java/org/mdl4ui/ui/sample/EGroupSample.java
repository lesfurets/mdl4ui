package org.mdl4ui.ui.sample;

import static org.mdl4ui.base.model.UIElementImpl.containsRec;
import static org.mdl4ui.ui.sample.EFieldSample.EMAILS_PREFERENCES;
import static org.mdl4ui.ui.sample.EFieldSample.MAX_WEEKLY_EMAILS;

import java.util.Arrays;
import java.util.List;

import org.mdl4ui.base.model.BlockID;
import org.mdl4ui.base.model.EElementType;
import org.mdl4ui.base.model.ElementID;
import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.base.model.GroupID;
import org.mdl4ui.base.model.UIElementImpl;

public enum EGroupSample implements GroupID {
    EMAIL_GROUP(EMAILS_PREFERENCES, MAX_WEEKLY_EMAILS);

    private final List<ElementID> childs;

    private EGroupSample(FieldID... fields) {
        this.childs = Arrays.<ElementID> asList(fields);
    }

    @Override
    public EElementType elementType() {
        return EElementType.GROUP;
    }

    @Override
    public List<ElementID> childs() {
        return childs;
    }

    @Override
    public boolean contains(ElementID child) {
        return containsRec(this, child);
    }

    @Override
    public List<FieldID> fields() {
        return UIElementImpl.<FieldID, ElementID> collectFields(this);
    }

    @Override
    public List<BlockID> blocks() {
        return UIElementImpl.<BlockID, ElementID> collectBlocks(this);
    }

    @Override
    public List<GroupID> groups() {
        return UIElementImpl.<GroupID, ElementID> collectGroups(this);
    }
}
