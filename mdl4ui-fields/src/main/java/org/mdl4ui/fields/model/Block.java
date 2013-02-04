package org.mdl4ui.fields.model;

import java.util.ArrayList;
import java.util.List;

import org.mdl4ui.base.model.BlockID;
import org.mdl4ui.base.model.ScreenID;

public class Block {

    private final String label;
    private final BlockID blockId;
    private final ScreenID screenID;
    private final List<BlockItem> items = new ArrayList<BlockItem>();

    public Block(String label, final BlockID blockId, ScreenID screenID) {
        this.label = label;
        this.blockId = blockId;
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

    public List<Field> getFields() {
        List<Field> fields = new ArrayList<Field>();
        for (BlockItem blocItem : items) {
            switch (blocItem.getType()) {
                case FIELD:
                    fields.add((Field) blocItem);
                    break;
                case GROUP:
                    Group group = (Group) blocItem;
                    for (Field field : group.getFields()) {
                        fields.add(field);
                    }
                    break;
            }
        }
        return fields;
    }

    public ScreenID getScreenID() {
        return screenID;
    }

    public BlockID getBlockId() {
        return blockId;
    }
}
