package org.mdl4ui.fields.model;

import java.util.List;

import org.mdl4ui.base.model.ScreenID;

public class Screen {

    private final ScreenID screenId;
    private final List<Block> blocks;

    public Screen(ScreenID screenId, final List<Block> blocks) {
        this.screenId = screenId;
        this.blocks = blocks;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public ScreenID getScreenId() {
        return screenId;
    }

}
