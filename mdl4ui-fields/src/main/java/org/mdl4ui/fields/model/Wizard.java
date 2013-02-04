package org.mdl4ui.fields.model;

import static org.mdl4ui.fields.model.event.SimpleEventBus.EVENT_BUS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mdl4ui.base.model.BlockID;
import org.mdl4ui.base.model.ElementID;
import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.base.model.GroupID;
import org.mdl4ui.base.model.ScreenID;
import org.mdl4ui.fields.model.event.BlockModifiedEvent;
import org.mdl4ui.fields.model.event.BlockModifiedEvent.BlockModifiedHandler;
import org.mdl4ui.fields.model.event.BlockSubmitedEvent;
import org.mdl4ui.fields.model.event.BlockSubmitedEvent.BlockSubmitedHandler;
import org.mdl4ui.fields.model.event.ExpandBlockEvent;

public class Wizard {

    private final FieldLabelFactory labelFactory;
    private final FieldRendererFactory rendererFactory;
    private final FieldHelpFactory helpFactory;

    private final Map<ScreenID, Screen> screens = new HashMap<ScreenID, Screen>();

    public Wizard(FieldLabelFactory labelFactory, FieldRendererFactory rendererFactory, FieldHelpFactory helpFactory) {
        this.labelFactory = labelFactory;
        this.rendererFactory = rendererFactory;
        this.helpFactory = helpFactory;
        initHandlers();
    }

    private void initHandlers() {
        EVENT_BUS.subscribe(BlockSubmitedEvent.TYPE, new BlockSubmitedHandler() {
            @Override
            public void onSubmit(BlockSubmitedEvent event) {
                // TODO validate block
                final Screen screen = getScreen(event.getBlock());
                final Block nextBlock = screen.getNextBlock(event.getBlock());
                if (nextBlock != null) {
                    EVENT_BUS.publish(new ExpandBlockEvent(nextBlock));
                }
            }
        });

        EVENT_BUS.subscribe(BlockModifiedEvent.TYPE, new BlockModifiedHandler() {
            @Override
            public void onModify(BlockModifiedEvent event) {
                EVENT_BUS.publish(new ExpandBlockEvent(event.getBlock()));
            }
        });
    }

    public Map<ScreenID, Screen> getScreens() {
        return this.screens;
    }

    private Screen getScreen(Block block) {
        for (Screen screen : screens.values()) {
            if (screen.getBlocks().contains(block)) {
                return screen;
            }
        }
        return null;
    }

    public void setScreens(ScreenID... screenIds) {
        for (ScreenID screenID : screenIds) {
            List<Block> blocks = new ArrayList<Block>();
            for (BlockID blockId : screenID.blocks()) {
                List<BlockItem> blockItems = new ArrayList<BlockItem>();
                for (ElementID child : blockId.childs()) {
                    switch (child.elementType()) {
                        case FIELD:
                            blockItems.add(getField(screenID, (FieldID) child));
                            break;
                        case GROUP:
                            ArrayList<Field> fields = new ArrayList<Field>();
                            for (FieldID fieldId : ((GroupID) child).fields()) {
                                fields.add(getField(screenID, fieldId));
                            }
                            blockItems.add(new Group((GroupID) child, fields));
                            break;
                    }
                }
                Block block = new Block(labelFactory.get(blockId), blockId, screenID);
                block.add(blockItems);
                blocks.add(block);
            }
            final Screen screen = new Screen(screenID, blocks);
            screens.put(screenID, screen);
        }
    }

    private Field getField(ScreenID screenId, FieldID fieldId) {
        return new Field(fieldId, labelFactory.get(fieldId), helpFactory.get(fieldId), rendererFactory.get(fieldId));
    }
}
