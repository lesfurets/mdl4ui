package org.mdl4ui.ui.sample;

import static org.mdl4ui.base.model.ElementIDImpl.collectBlocks;
import static org.mdl4ui.base.model.ElementIDImpl.collectFields;
import static org.mdl4ui.base.model.ElementIDImpl.collectGroups;
import static org.mdl4ui.base.model.ElementIDImpl.containsRec;
import static org.mdl4ui.ui.sample.EFieldSample.EMAILS_PREFERENCES;
import static org.mdl4ui.ui.sample.EFieldSample.MAX_WEEKLY_EMAILS;

import java.util.List;

import org.mdl4ui.base.model.BlockID;
import org.mdl4ui.base.model.EElementType;
import org.mdl4ui.base.model.ElementID;
import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.base.model.GroupID;

public enum EGroupSample implements GroupID {
    EMAIL_GROUP(EMAILS_PREFERENCES, MAX_WEEKLY_EMAILS);

    private final ElementID[] childs;

    private EGroupSample(FieldID... blocks) {
        this.childs = blocks;
    }

    @Override
    public EElementType elementType() {
        return EElementType.GROUP;
    }

    @Override
    public ElementID[] childs() {
        return childs;
    }

    @Override
    public boolean contains(ElementID child) {
        return containsRec(this, child);
    }

    @Override
    public List<FieldID> fields() {
        return collectFields(this);
    }

    @Override
    public List<BlockID> blocks() {
        return collectBlocks(this);
    }

    @Override
    public List<GroupID> groups() {
        return collectGroups(this);
    }
}
