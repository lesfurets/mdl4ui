package org.mdl4ui.ui.sample;

import static org.mdl4ui.base.model.UIElementImpl.containsRec;
import static org.mdl4ui.ui.sample.EBlockSample.ACCOUNT;
import static org.mdl4ui.ui.sample.EBlockSample.PERSONAL_INFORMATIONS;
import static org.mdl4ui.ui.sample.EBlockSample.SETTINGS;

import java.util.Arrays;
import java.util.List;

import org.mdl4ui.base.model.BlockID;
import org.mdl4ui.base.model.EElementType;
import org.mdl4ui.base.model.ElementID;
import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.base.model.GroupID;
import org.mdl4ui.base.model.ScreenID;
import org.mdl4ui.base.model.UIElementImpl;

public enum EScreenSample implements ScreenID {
    SCR_WIDGETS_SHOWCASE(PERSONAL_INFORMATIONS, SETTINGS, ACCOUNT);

    private final List<ElementID> childs;

    private EScreenSample(BlockID... blocks) {
        this.childs = Arrays.<ElementID> asList(blocks);
    }

    @Override
    public EElementType elementType() {
        return EElementType.SCREEN;
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

    @Override
    public BlockID nextBlock(BlockID block) {
        return UIElementImpl.<BlockID, ElementID> nextBlock(this, block);
    }
}
