package org.mdl4ui.fields.model.event;

import org.mdl4ui.base.model.BlockID;
import org.mdl4ui.base.model.ScreenID;
import org.mdl4ui.fields.model.event.ExpandBlockEvent.ExpandBlockHandler;

public class ExpandBlockEvent extends Event<ExpandBlockHandler> {

    public static Type<ExpandBlockHandler> TYPE = new Type<ExpandBlockHandler>();

    private final ScreenID screenId;
    private final BlockID blockId;

    public ExpandBlockEvent(ScreenID screenId, BlockID blockId) {
        super();
        this.screenId = screenId;
        this.blockId = blockId;
    }

    @Override
    public Type<ExpandBlockHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ExpandBlockHandler handler) {
        handler.onExpand(this);
    }

    public BlockID getBlockId() {
        return blockId;
    }

    public ScreenID getScreenId() {
        return screenId;
    }

    public interface ExpandBlockHandler {
        void onExpand(ExpandBlockEvent event);
    }
}
