package org.mdl4ui.gwt.sample.client.ui.model;

import org.mdl4ui.fields.model.Block;
import org.mdl4ui.fields.model.Screen;

import com.github.gwtbootstrap.client.ui.Column;
import com.github.gwtbootstrap.client.ui.Container;
import com.github.gwtbootstrap.client.ui.Row;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class ScreenView implements IsWidget {

    private final Container container = new Container();

    public ScreenView(final Screen screen) {
        for (Block block : screen.getBlocks()) {
            Row row = new Row();
            container.add(row);

            Column column = new Column(6, 3);
            row.add(column);

            BlockView blockView = new BlockView(block);
            column.add(blockView);
        }
    }

    @Override
    public Widget asWidget() {
        return container;
    }
}
