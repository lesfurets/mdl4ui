package org.mdl4ui.fields.model;

import java.util.ArrayList;
import java.util.List;

import org.mdl4ui.base.model.BlockID;
import org.mdl4ui.base.model.ElementID;
import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.base.model.GroupID;
import org.mdl4ui.base.model.ScreenID;

public class Wizard {

    private final FieldLabelFactory labelFactory;
    private final FieldRendererFactory rendererFactory;
    private final FieldHelpFactory helpFactory;

    public Wizard(FieldLabelFactory labelFactory, FieldRendererFactory rendererFactory, FieldHelpFactory helpFactory) {
        this.labelFactory = labelFactory;
        this.rendererFactory = rendererFactory;
        this.helpFactory = helpFactory;
    }

    public Screen getScreen(ScreenID screenId) {
        List<Block> blocks = new ArrayList<Block>();
        for (BlockID blockId : screenId.blocks()) {
            List<BlockItem> blockItems = new ArrayList<BlockItem>();
            for (ElementID child : blockId.childs()) {
                switch (child.elementType()) {
                    case FIELD:
                        blockItems.add(getField(screenId, (FieldID) child));
                        break;
                    case GROUP:
                        ArrayList<Field> fields = new ArrayList<Field>();
                        for (FieldID fieldId : ((GroupID) child).fields()) {
                            fields.add(getField(screenId, fieldId));
                        }
                        blockItems.add(new Group((GroupID) child, fields));
                        break;
                }
            }
            Block block = new Block(labelFactory.get(blockId), blockId, blockItems);
            blocks.add(block);
        }
        return new Screen(screenId, blocks);
    }

    private Field getField(ScreenID screenId, FieldID fieldId) {
        return new Field(fieldId, labelFactory.get(fieldId), helpFactory.get(fieldId), rendererFactory.get(fieldId));
    }
}
