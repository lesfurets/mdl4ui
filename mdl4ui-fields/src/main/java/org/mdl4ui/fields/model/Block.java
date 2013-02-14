package org.mdl4ui.fields.model;

import java.util.ArrayList;
import java.util.List;

import org.mdl4ui.base.model.BlockID;
import org.mdl4ui.base.model.EElementType;

public class Block extends ElementImpl {

    private final String label;
    private final BlockID blockID;
    private final List<Element> items = new ArrayList<Element>();

    public Block(String label, final BlockID blockID) {
        this.label = label;
        this.blockID = blockID;
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
