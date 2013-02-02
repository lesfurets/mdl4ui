package org.mdl4ui.sample.ui;

import static org.mdl4ui.base.model.ElementIDImpl.collectBlocks;
import static org.mdl4ui.base.model.ElementIDImpl.collectFields;
import static org.mdl4ui.base.model.ElementIDImpl.collectGroups;
import static org.mdl4ui.base.model.ElementIDImpl.containsRec;
import static org.mdl4ui.sample.ui.EFieldSample.LOGIN;
import static org.mdl4ui.sample.ui.EFieldSample.PASSWORD;

import java.util.List;

import org.mdl4ui.base.model.BlockID;
import org.mdl4ui.base.model.EElementType;
import org.mdl4ui.base.model.ElementID;
import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.base.model.GroupID;

public enum EBlockSample implements BlockID {
    BLK_WIDGETS_SHOWCASE(LOGIN, PASSWORD);

    private final ElementID[] childs;

    private EBlockSample(ElementID... blocks) {
        this.childs = blocks;
    }

    @Override
    public EElementType elementType() {
        return EElementType.BLOCK;
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
