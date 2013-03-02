package org.mdl4ui.gwt.sample.client.ui;

import static org.mdl4ui.base.model.UIElementImpl.containsRec;

import java.util.List;

import org.mdl4ui.base.model.EElementType;
import org.mdl4ui.base.model.UIElementImpl;
import org.mdl4ui.gwt.model.client.ui.BlockView;
import org.mdl4ui.gwt.model.client.ui.ElementView;
import org.mdl4ui.gwt.model.client.ui.FieldView;
import org.mdl4ui.gwt.model.client.ui.GroupView;

import com.github.gwtbootstrap.client.ui.Heading;
import com.github.gwtbootstrap.client.ui.Hero;
import com.google.gwt.user.client.ui.Widget;

public class RegistrationDoneScreen implements ElementView {

    private final Hero container;

    public RegistrationDoneScreen() {
        container = new Hero();
        container.add(new Heading(1, "Registration done successfully!"));
    }

    @Override
    public Widget asWidget() {
        return container;
    }

    @Override
    public EElementType elementType() {
        return EElementType.SCREEN;
    }

    @Override
    public List<ElementView> childs() {
        return null;
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

}
