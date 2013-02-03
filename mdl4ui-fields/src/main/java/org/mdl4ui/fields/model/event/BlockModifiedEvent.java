/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.model.event;

import org.mdl4ui.fields.model.Block;
import org.mdl4ui.fields.model.event.BlockModifiedEvent.BlockModifiedHandler;

public class BlockModifiedEvent extends Event<BlockModifiedHandler> {

    public static Type<BlockModifiedHandler> TYPE = new Type<BlockModifiedHandler>();

    private final Block block;

    public BlockModifiedEvent(Block block) {
        this.block = block;
    }

    @Override
    public Type<BlockModifiedHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(BlockModifiedHandler handler) {
        handler.onModify(this);
    }

    public Block getBlock() {
        return block;
    }

    public interface BlockModifiedHandler {
        void onModify(BlockModifiedEvent event);
    }
}
