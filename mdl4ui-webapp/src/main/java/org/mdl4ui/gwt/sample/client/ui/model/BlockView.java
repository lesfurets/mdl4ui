package org.mdl4ui.gwt.sample.client.ui.model;

import org.mdl4ui.fields.model.Block;
import org.mdl4ui.fields.model.BlockItem;
import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.Group;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Fieldset;
import com.github.gwtbootstrap.client.ui.FormActions;
import com.github.gwtbootstrap.client.ui.Legend;
import com.github.gwtbootstrap.client.ui.WellForm;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class BlockView implements IsWidget {

    private final WellForm form;

    public BlockView(Block block) {
        form = new WellForm();

        Legend title = new Legend(block.getTitle());
        form.add(title);

        Fieldset fieldset = new Fieldset();
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

        FormActions actions = new FormActions();
        Button validate = new Button("Submit");
        validate.setType(ButtonType.PRIMARY);
        actions.add(validate);

        form.add(actions);
    }

    @Override
    public Widget asWidget() {
        return form;
    }
}
