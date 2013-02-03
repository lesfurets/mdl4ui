package org.mdl4ui.gwt.sample.client.ui.model;

import static org.mdl4ui.fields.model.event.SimpleEventBus.EVENT_BUS;

import org.mdl4ui.fields.model.Block;
import org.mdl4ui.fields.model.Screen;
import org.mdl4ui.fields.model.event.BlockSubmitedEvent;
import org.mdl4ui.fields.model.event.BlockSubmitedEvent.BlockSubmitedHandler;

import com.github.gwtbootstrap.client.ui.Column;
import com.github.gwtbootstrap.client.ui.FluidRow;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class ScreenView implements IsWidget {

    private final FluidRow row;

    public ScreenView(Screen screen) {
        row = new FluidRow();
        final Column column = new Column(4, 4);
        row.add(column);

        for (Block block : screen.getBlocks()) {
            column.add(new BlockView(block));
        }

        EVENT_BUS.subscribe(BlockSubmitedEvent.TYPE, new BlockSubmitedHandler() {
            @Override
            public void onSubmit(BlockSubmitedEvent event) {
                System.out.println("block " + event.getBlockID() + " submitted");
            }
        });
    }

    @Override
    public Widget asWidget() {
        return row;
    }
}
