/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.model.event;

import org.mdl4ui.base.model.BlockID;
import org.mdl4ui.fields.model.event.BlockSubmitedEvent.BlockSubmitedHandler;

public class BlockSubmitedEvent extends Event<BlockSubmitedHandler> {

    public static Type<BlockSubmitedHandler> TYPE = new Type<BlockSubmitedHandler>();

    /**
     * string representation of the catched {@link Event}
     */
    private final BlockID blockID;

    public BlockSubmitedEvent(BlockID blockID) {
        this.blockID = blockID;
    }

    @Override
    public Type<BlockSubmitedHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(BlockSubmitedHandler handler) {
        handler.onSubmit(this);
    }

    public BlockID getBlockID() {
        return blockID;
    }

    public interface BlockSubmitedHandler {
        void onSubmit(BlockSubmitedEvent event);
    }
}
