package org.mdl4ui.fields.model;

import java.util.ArrayList;
import java.util.List;

import org.mdl4ui.base.model.BlockID;
import org.mdl4ui.base.model.ScreenID;

public class Block {

    private final String label;
    private final BlockID blockID;
    private final ScreenID screenID;
    private final List<BlockItem> items = new ArrayList<BlockItem>();

    public Block(String label, final BlockID blockID, ScreenID screenID) {
        this.label = label;
        this.blockID = blockID;
        this.screenID = screenID;
    }

    public String getTitle() {
        return label;
    }

    public void add(List<BlockItem> newItems) {
        items.addAll(newItems);
    }

    public List<BlockItem> getBlockItems() {
        return items;
    }

    public ScreenID getScreenID() {
        return screenID;
    }

    public BlockID getBlockID() {
        return blockID;
    }
}
