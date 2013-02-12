package org.mdl4ui.fields.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mdl4ui.base.model.BlockID;
import org.mdl4ui.base.model.ElementID;
import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.base.model.GroupID;
import org.mdl4ui.base.model.ScreenID;

public class Wizard {

    private final FieldLabelFactory labelFactory;
    private final FieldRendererFactory rendererFactory;
    private final FieldPlaceholderFactory placeholderFactory;
    private final FieldHelpFactory helpFactory;

    private final Map<ScreenID, Screen> screens = new HashMap<ScreenID, Screen>();

    public Wizard(FieldLabelFactory labelFactory, FieldHelpFactory helpFactory,
                    FieldPlaceholderFactory placeholderFactory, FieldRendererFactory rendererFactory) {
        this.labelFactory = labelFactory;
        this.rendererFactory = rendererFactory;
        this.placeholderFactory = placeholderFactory;
        this.helpFactory = helpFactory;
    }

    public Map<ScreenID, Screen> getScreens() {
        return this.screens;
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
        return new Field(fieldId, labelFactory.get(fieldId), helpFactory.get(fieldId), placeholderFactory.get(fieldId),
                        rendererFactory.get(fieldId));
    }
}
