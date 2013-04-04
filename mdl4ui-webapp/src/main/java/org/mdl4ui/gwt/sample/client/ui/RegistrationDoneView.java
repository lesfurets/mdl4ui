package org.mdl4ui.gwt.sample.client.ui;

import static org.mdl4ui.base.model.UIElementImpl.containsRec;
import static org.mdl4ui.gwt.sample.client.i18n.SampleMessagesFactory.MSG_SM;

import java.util.List;

import org.mdl4ui.base.model.ElementType;
import org.mdl4ui.base.model.UIElementImpl;
import org.mdl4ui.fields.model.Screen;
import org.mdl4ui.fields.model.WizardContext;
import org.mdl4ui.fields.sample.context.Person;
import org.mdl4ui.fields.sample.context.SampleContext;
import org.mdl4ui.fields.sample.context.UserAccount;
import org.mdl4ui.gwt.model.client.ui.BlockView;
import org.mdl4ui.gwt.model.client.ui.ElementView;
import org.mdl4ui.gwt.model.client.ui.FieldView;
import org.mdl4ui.gwt.model.client.ui.GroupView;
import org.mdl4ui.gwt.model.client.ui.ScreenView;

import com.github.gwtbootstrap.client.ui.Column;
import com.github.gwtbootstrap.client.ui.Heading;
import com.github.gwtbootstrap.client.ui.Hero;
import com.github.gwtbootstrap.client.ui.Row;
import com.google.gwt.user.client.ui.Widget;

public class RegistrationDoneView implements ScreenView {

    private final Hero container;
    private final Screen screen;

    public RegistrationDoneView(Screen screen) {
        this.screen = screen;
        container = new Hero();
    }

    @Override
    public void onDisplay(WizardContext context) {
        container.clear();

        // add title for registration success
        Heading heading = new Heading(1, MSG_SM().registration_done());
        heading.getElement().setId("registration_done");
        Row row = new Row();
        row.add(new Column(10, heading));
        container.add(row);

        SampleContext sampleContext = (SampleContext) context;
        Row rowDetails = new Row();
        container.add(rowDetails);

        // add section for personal information
        Person person = sampleContext.getPerson();
        PersonWidget personWidget = new PersonWidget();
        personWidget.setPerson(person);
        rowDetails.add(new Column(5, personWidget.asWidget()));

        // add section for user account
        UserAccount userAccount = sampleContext.getUserAccount();
        UserAccountWidget userAccountWidget = new UserAccountWidget();
        userAccountWidget.setUserAccount(userAccount);
        rowDetails.add(new Column(5, userAccountWidget.asWidget()));
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
