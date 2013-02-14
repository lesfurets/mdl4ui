package org.mdl4ui.gwt.sample.client.ui.model;

import static org.mdl4ui.base.model.UIElementImpl.containsRec;

import java.util.ArrayList;
import java.util.List;

import org.mdl4ui.base.model.EElementType;
import org.mdl4ui.base.model.UIElementImpl;
import org.mdl4ui.fields.model.Block;
import org.mdl4ui.fields.model.Element;
import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.Group;

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
import com.google.gwt.user.client.ui.Widget;

public class BlockView implements ElementView {

    private final WellForm form;
    private final Button modify;
    private final Fieldset fieldset;
    private final FormActions actions;
    private final List<ElementView> childs = new ArrayList<ElementView>();

    public BlockView(final Block block) {
        form = new WellForm();

        Row row = new Row();

        Column column = new Column(7);
        row.add(column);
        column.add(new Legend(block.getTitle()));

        column = new Column(1);
        row.add(column);
        modify = new Button();
        modify.setIcon(IconType.EDIT);
        modify.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                expand();
            }
        });
        row.add(modify);

        form.add(row);

        fieldset = new Fieldset();
        form.add(fieldset);
        for (Element item : block.childs()) {
            ElementView child = null;
            switch (item.elementType()) {
                case FIELD:
                    child = new FieldView((Field) item);
                    break;
                case GROUP:
                    child = new GroupView((Group) item);
                    break;
            }
            fieldset.add(child);
            childs.add(child);
        }

        actions = new FormActions();
        Button validate = new Button("Submit");
        validate.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                collapse();
            }
        });

        validate.setType(ButtonType.PRIMARY);
        actions.add(validate);

        form.add(actions);
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
    public EElementType elementType() {
        return EElementType.GROUP;
    }

    @Override
    public List<ElementView> childs() {
        return childs;
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
    public Widget asWidget() {
        return form;
    }
}
