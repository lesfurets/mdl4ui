package org.mdl4ui.fields.model;

import java.util.ArrayList;
import java.util.List;

import org.mdl4ui.base.model.BlockID;
import org.mdl4ui.base.model.EElementType;
import org.mdl4ui.base.model.ScreenID;

public class Block extends ElementImpl {

    private final String label;
    private final BlockID blockID;
    private final ScreenID screenID;
    private final List<Element> items = new ArrayList<Element>();

    public Block(String label, final BlockID blockID, ScreenID screenID) {
        this.label = label;
        this.blockID = blockID;
        this.screenID = screenID;
    }

    public String getTitle() {
        return label;
    }

    public void add(List<Element> newItems) {
        items.addAll(newItems);
    }

    public List<Element> getBlockItems() {
        return items;
    }

    public ScreenID getScreenID() {
        return screenID;
    }

    public BlockID getBlockID() {
        return blockID;
    }

    @Override
    public EElementType elementType() {
        return EElementType.BLOCK;
    }

    @Override
    public List<Element> childs() {
        return items;
    }
}
