/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.model.event;

import org.mdl4ui.base.model.BlockID;
import org.mdl4ui.fields.model.event.BlockModifiedEvent.BlockModifiedEventHandler;

public class BlockModifiedEvent extends Event<BlockModifiedEventHandler> {

    public static Type<BlockModifiedEventHandler> TYPE = new Type<BlockModifiedEventHandler>();

    /**
     * string representation of the catched {@link Event}
     */
    private final BlockID blockID;

    public BlockModifiedEvent(BlockID blockID) {
        this.blockID = blockID;
    }

    @Override
    public Type<BlockModifiedEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(BlockModifiedEventHandler handler) {
        handler.onModify(this);
    }

    public BlockID getBlockID() {
        return blockID;
    }

    public interface BlockModifiedEventHandler {
        void onModify(BlockModifiedEvent event);
    }
}
