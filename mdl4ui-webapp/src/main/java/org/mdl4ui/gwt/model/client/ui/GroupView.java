package org.mdl4ui.gwt.model.client.ui;

import static org.mdl4ui.base.model.UIElementImpl.containsRec;

import java.util.ArrayList;
import java.util.List;

import org.mdl4ui.base.model.EElementType;
import org.mdl4ui.base.model.UIElementImpl;
import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.Group;

import com.github.gwtbootstrap.client.ui.Column;
import com.github.gwtbootstrap.client.ui.Row;
import com.google.gwt.user.client.ui.Widget;

public class GroupView implements ElementView {

    private final List<ElementView> childs = new ArrayList<ElementView>();
    private final Row row;

    public GroupView(Group group) {
        row = new Row();
        final Column column = new Column(7, 1);
        row.add(column);

        for (Field field : group.fields()) {
            final FieldView fieldView = new FieldView(field);
            column.add(fieldView);
            childs.add(fieldView);
        }
    }

    @Override
    public Widget asWidget() {
        return row;
    }

    @Override
    public EElementType elementType() {
        return EElementType.GROUP;
    }

    @Override
    public boolean contains(ElementView child) {
        return containsRec(this, child);
    }

    @Override
    public List<FieldView> fields() {
        return UIElementImpl.<FieldView, ElementView> collectFields(this);
    }

    @Override
    public List<BlockView> blocks() {
        return UIElementImpl.<BlockView, ElementView> collectBlocks(this);
    }

    @Override
    public List<GroupView> groups() {
        return UIElementImpl.<GroupView, ElementView> collectGroups(this);
    }

    @Override
    public List<ElementView> childs() {
        return childs;
    }
}
