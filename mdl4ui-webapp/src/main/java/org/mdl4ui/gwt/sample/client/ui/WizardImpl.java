package org.mdl4ui.gwt.sample.client.ui;

import java.util.ArrayList;
import java.util.List;

import org.mdl4ui.base.model.BlockID;
import org.mdl4ui.base.model.ElementID;
import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.base.model.GroupID;
import org.mdl4ui.base.model.ScreenID;
import org.mdl4ui.fields.model.Block;
import org.mdl4ui.fields.model.BlockItem;
import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.Group;
import org.mdl4ui.fields.model.Screen;
import org.mdl4ui.fields.model.Wizard;

public class WizardImpl implements Wizard {

    @Override
    public Screen createScreen(ScreenID screenId) {
        List<Block> blocks = new ArrayList<Block>();
        for (BlockID blockId : screenId.blocks()) {
            List<BlockItem> blockItems = new ArrayList<BlockItem>();
            for (ElementID blockItemId : blockId.childs()) {
                switch (blockItemId.elementType()) {
                    case FIELD:
                        blockItems.add(getField(screenId, (FieldID) blockItemId));
                        break;
                    case GROUP:
                        ArrayList<Field> fields = new ArrayList<Field>();
                        for (FieldID fieldId : ((GroupID) blockItemId).fields()) {
                            fields.add(getField(screenId, fieldId));
                        }
                        blockItems.add(new Group((GroupID) blockItemId, fields));
                        break;
                }

            }
            Block block = new Block(getLabel(blockId), blockId, blockItems);
            blocks.add(block);
        }
        return new Screen(screenId, blocks);
    }

    private String getLabel(ElementID elementID) {
        return elementID.toString().toLowerCase().replace("_", " ");
    }

    private Field getField(ScreenID screenId, FieldID fieldId) {
        return new Field(fieldId, screenId, null);
    }
}
