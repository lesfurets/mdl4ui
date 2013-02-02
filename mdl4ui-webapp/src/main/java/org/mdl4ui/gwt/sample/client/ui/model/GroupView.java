package org.mdl4ui.gwt.sample.client.ui.model;

import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.Group;

import com.github.gwtbootstrap.client.ui.Column;
import com.github.gwtbootstrap.client.ui.FluidRow;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class GroupView implements IsWidget {

    private final FluidRow row;

    public GroupView(Group group) {
        row = new FluidRow();
        final Column column = new Column(10, 2);
        row.add(column);

        for (Field field : group.getFields()) {
            column.add(new FieldView(field));
        }
    }

    @Override
    public Widget asWidget() {
        return row;
    }

}
