package org.mdl4ui.gwt.model.client.ui;

import static org.mdl4ui.base.model.UIElementImpl.containsRec;

import java.util.ArrayList;
import java.util.List;

import org.mdl4ui.base.model.ElementType;
import org.mdl4ui.base.model.UIElementImpl;
import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.Group;

import com.github.gwtbootstrap.client.ui.Column;
import com.github.gwtbootstrap.client.ui.Row;
import com.github.gwtbootstrap.client.ui.Well;
import com.google.gwt.user.client.ui.Widget;

public class GroupView implements ElementView {

    private final Group group;
    private final List<ElementView> childs = new ArrayList<ElementView>();
    private final Row row;

    public GroupView(Group group) {
        this.group = group;
        row = new Row();
        final Column column = new Column(7, 2);
        row.add(column);

        Well well = new Well();
        column.add(well);

        for (Field field : group.fields()) {
            final FieldView fieldView = new FieldView(field);
            well.add(fieldView);
            childs.add(fieldView);
        }
    }

    public Group getGroup() {
        return this.group;
    }

    @Override
    public Widget asWidget() {
        return row;
    }

    @Override
    public ElementType elementType() {
        return ElementType.GROUP;
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
