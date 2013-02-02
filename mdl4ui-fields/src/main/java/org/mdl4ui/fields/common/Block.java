/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.common;

import java.util.ArrayList;
import java.util.List;

import org.mdl4ui.base.model.BlockID;

public class Block {

    private final String label;
    private final List<BlockItem> items;
    private final BlockID blockId;

    public Block(String label, final BlockID blockId, List<BlockItem> items) {
        this.label = label;
        this.blockId = blockId;
        this.items = items;
    }

    public String getTitre() {
        return label;
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

    public BlockID getBlockId() {
        return blockId;
    }
}
