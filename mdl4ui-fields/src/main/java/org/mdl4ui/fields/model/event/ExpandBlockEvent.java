package org.mdl4ui.fields.model.event;

import org.mdl4ui.fields.model.Block;
import org.mdl4ui.fields.model.event.ExpandBlockEvent.ExpandBlockHandler;

public class ExpandBlockEvent extends Event<ExpandBlockHandler> {

    public static Type<ExpandBlockHandler> TYPE = new Type<ExpandBlockHandler>();

    private final Block block;

    public ExpandBlockEvent(Block block) {
        this.block = block;
    }

    @Override
    public Type<ExpandBlockHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ExpandBlockHandler handler) {
        handler.onExpand(this);
    }

    public Block getBlock() {
        return block;
    }

    public interface ExpandBlockHandler {
        void onExpand(ExpandBlockEvent event);
    }
}
