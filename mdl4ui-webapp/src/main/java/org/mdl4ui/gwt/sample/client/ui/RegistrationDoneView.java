package org.mdl4ui.gwt.sample.client.ui;

import static org.mdl4ui.base.model.UIElementImpl.containsRec;

import java.util.List;

import org.mdl4ui.base.model.ElementType;
import org.mdl4ui.base.model.UIElementImpl;
import org.mdl4ui.fields.model.Screen;
import org.mdl4ui.gwt.model.client.ui.BlockView;
import org.mdl4ui.gwt.model.client.ui.ElementView;
import org.mdl4ui.gwt.model.client.ui.FieldView;
import org.mdl4ui.gwt.model.client.ui.GroupView;
import org.mdl4ui.gwt.model.client.ui.ScreenView;

import com.github.gwtbootstrap.client.ui.Heading;
import com.github.gwtbootstrap.client.ui.Hero;
import com.google.gwt.user.client.ui.Widget;

public class RegistrationDoneView implements ScreenView {

    private final Hero container;
    private final Screen screen;

    public RegistrationDoneView(Screen screen) {
        this.screen = screen;
        container = new Hero();
        // TODO add i18n resources
        container.add(new Heading(1, "Registration done successfully!"));
    }

    @Override
    public Screen getScreen() {
        return screen;
    }

    @Override
    public Widget asWidget() {
        return container;
    }

    @Override
    public ElementType elementType() {
        return ElementType.SCREEN;
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
