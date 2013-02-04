package org.mdl4ui.gwt.sample.client.ui.model;

import static org.mdl4ui.fields.model.event.SimpleEventBus.EVENT_BUS;

import org.mdl4ui.base.model.ScreenID;
import org.mdl4ui.fields.model.Block;
import org.mdl4ui.fields.model.BlockItem;
import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.Group;
import org.mdl4ui.fields.model.event.BlockModifiedEvent;
import org.mdl4ui.fields.model.event.BlockSubmitedEvent;
import org.mdl4ui.fields.model.event.ExpandBlockEvent;
import org.mdl4ui.fields.model.event.ExpandBlockEvent.ExpandBlockHandler;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Column;
import com.github.gwtbootstrap.client.ui.Fieldset;
import com.github.gwtbootstrap.client.ui.FormActions;
import com.github.gwtbootstrap.client.ui.Legend;
import com.github.gwtbootstrap.client.ui.Row;
import com.github.gwtbootstrap.client.ui.WellForm;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class BlockView implements IsWidget {

    private final WellForm form;
    private final Button modify;
    private final Fieldset fieldset;
    private final FormActions actions;

    public BlockView(final Block block) {
        form = new WellForm();

        Row row = new Row();

        Column column = new Column(5);
        row.add(column);
        column.add(new Legend(block.getTitle()));

        column = new Column(1);
        row.add(column);
        modify = new Button();
        modify.setIcon(IconType.EDIT);
        modify.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // modify block
                EVENT_BUS.publish(new BlockModifiedEvent(block));
            }
        });
        row.add(modify);

        form.add(row);

        fieldset = new Fieldset();
        form.add(fieldset);
        for (BlockItem item : block.getBlockItems()) {
            switch (item.getType()) {
                case FIELD:
                    fieldset.add(new FieldView((Field) item));
                    break;
                case GROUP:
                    fieldset.add(new GroupView((Group) item));
                    break;
            }
        }

        actions = new FormActions();
        Button validate = new Button("Submit");
        validate.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // submit current block
                EVENT_BUS.publish(new BlockSubmitedEvent(block));
            }
        });

        validate.setType(ButtonType.PRIMARY);
        actions.add(validate);

        form.add(actions);

        EVENT_BUS.subscribe(ExpandBlockEvent.TYPE, new ExpandBlockHandler() {
            @Override
            public void onExpand(ExpandBlockEvent event) {
                ScreenID screenID = block.getScreenID();

                // ignore event from others screens
                if (event.getBlock().getScreenID() != screenID) {
                    return;
                }

                // expand or collapse block
                if (event.getBlock() == block) {
                    expand();
                } else {
                    collapse();
                }

                // update modify icon visibility
                int position = screenID.blocks().indexOf(block.getBlockID());
                int otherPosition = screenID.blocks().indexOf(event.getBlock().getBlockID());
                modify.setVisible(position < otherPosition);
            }
        });
    }

    private void expand() {
        fieldset.setVisible(true);
        actions.setVisible(true);
    }

    private void collapse() {
        fieldset.setVisible(false);
        actions.setVisible(false);
    }

    @Override
    public Widget asWidget() {
        return form;
    }
}
