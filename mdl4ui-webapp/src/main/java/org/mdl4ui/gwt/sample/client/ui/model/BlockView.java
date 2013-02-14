package org.mdl4ui.gwt.sample.client.ui.model;

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
                expand();
            }
        });
        row.add(modify);

        form.add(row);

        fieldset = new Fieldset();
        form.add(fieldset);
        for (Element item : block.childs()) {
            switch (item.elementType()) {
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
    public Widget asWidget() {
        return form;
    }
}
