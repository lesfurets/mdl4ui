package org.mdl4ui.fields.model.event;

import org.mdl4ui.fields.model.Block;
import org.mdl4ui.fields.model.event.BlockSubmitedEvent.BlockSubmitedHandler;

public class BlockSubmitedEvent extends Event<BlockSubmitedHandler> {

    public static Type<BlockSubmitedHandler> TYPE = new Type<BlockSubmitedHandler>();

    private final Block block;

    public BlockSubmitedEvent(Block block) {
        this.block = block;
    }

    @Override
    public Type<BlockSubmitedHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(BlockSubmitedHandler handler) {
        handler.onSubmit(this);
    }

    public Block getBlock() {
        return block;
    }

    public interface BlockSubmitedHandler {
        void onSubmit(BlockSubmitedEvent event);
    }
}
