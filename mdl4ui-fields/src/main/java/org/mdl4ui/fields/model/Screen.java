package org.mdl4ui.fields.model;

import java.util.ArrayList;
import java.util.List;

import org.mdl4ui.base.model.EElementType;
import org.mdl4ui.base.model.ScreenID;

public class Screen extends ElementImpl {

    private final ScreenID screenID;
    private final List<Block> blocks;

    public Screen(ScreenID screenId, final List<Block> blocks) {
        this.screenID = screenId;
        this.blocks = blocks;
    }

    public ScreenID getScreenID() {
        return screenID;
    }

    public Block getNextBlock(Block block) {
        final int index = blocks.indexOf(block);
        if (index < blocks.size() - 1) {
            return blocks.get(index + 1);
        } else {
            return null;
        }
    }

    @Override
    public EElementType elementType() {
        return EElementType.SCREEN;
    }

    @Override
    public List<Element> childs() {
        return new ArrayList<Element>(blocks);
    }
}
