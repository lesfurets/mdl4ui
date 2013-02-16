package org.mdl4ui.fields.model;

import static org.mdl4ui.base.model.UIElementImpl.containsRec;

import java.util.ArrayList;
import java.util.List;

import org.mdl4ui.base.model.EElementType;
import org.mdl4ui.base.model.ScreenID;
import org.mdl4ui.base.model.UIElementImpl;

public class Screen implements Element {

    private final ScreenID screenID;
    private final List<Block> blocks;

    public Screen(ScreenID screenId, final List<Block> blocks) {
        this.screenID = screenId;
        this.blocks = blocks;
    }

    public ScreenID getScreenID() {
        return screenID;
    }

    @Override
    public EElementType elementType() {
        return EElementType.SCREEN;
    }

    @Override
    public List<Element> childs() {
        return new ArrayList<Element>(blocks);
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
