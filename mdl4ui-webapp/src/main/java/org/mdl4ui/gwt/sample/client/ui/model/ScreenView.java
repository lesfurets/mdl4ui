package org.mdl4ui.gwt.sample.client.ui.model;

import org.mdl4ui.fields.model.Block;
import org.mdl4ui.fields.model.Screen;

import com.github.gwtbootstrap.client.ui.Column;
import com.github.gwtbootstrap.client.ui.FluidRow;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class ScreenView implements IsWidget {

    private final FluidRow row;

    public ScreenView(final Screen screen) {
        row = new FluidRow();
        final Column column = new Column(8);
        row.add(column);

        for (Block block : screen.getBlocks()) {
            final BlockView blockView = new BlockView(block);
            column.add(blockView);
            if (screen.getScreenId().blocks().get(0) == block.getBlockId()) {
                blockView.expand();
            } else {
                blockView.collapse();
            }
        }
    }

    @Override
    public Widget asWidget() {
        return row;
    }
}
