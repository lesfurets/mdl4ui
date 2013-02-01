package org.mdl4ui.sample.gwt.client;

import org.mdl4ui.sample.gwt.client.ui.FieldView;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Collapse;
import com.github.gwtbootstrap.client.ui.Fieldset;
import com.github.gwtbootstrap.client.ui.FormActions;
import com.github.gwtbootstrap.client.ui.Legend;
import com.github.gwtbootstrap.client.ui.PasswordTextBox;
import com.github.gwtbootstrap.client.ui.WellForm;
import com.github.gwtbootstrap.client.ui.base.TextBox;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.RootPanel;

public class Sample implements EntryPoint {

    @Override
    public void onModuleLoad() {
        WellForm form = new WellForm();

        Legend legend = new Legend("Form Validation with Control Decoration");
        form.add(legend);

        Fieldset fieldset = new Fieldset();
        form.add(fieldset);

        final TextBox userName = new TextBox();
        userName.getElement().setAttribute("placeholder", "Username");
        final FieldView userNameField = new FieldView(userName, "Username");
        fieldset.add(userNameField.asWidget());

        final PasswordTextBox password = new PasswordTextBox();
        password.getElement().setAttribute("placeholder", "Password");
        final FieldView passwordField = new FieldView(password, "Password");
        fieldset.add(passwordField.asWidget());

        FormActions actions = new FormActions();
        Button validate = new Button("Validate Login");
        validate.setType(ButtonType.PRIMARY);
        actions.add(validate);
        form.add(actions);

        validate.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                String text = userName.getText();
                if (text == null || "".equals(text.trim())) {
                    userNameField.error("Please enter your username");
                } else {
                    userNameField.valid();
                }
                text = password.getText();
                if (text == null || "".equals(text.trim())) {
                    passwordField.error("Please enter your password");
                } else {
                    passwordField.valid();
                }
            }
        });

        Collapse collapse = new Collapse();
        collapse.add(form);
        collapse.setDefaultOpen(true);

        RootPanel.get("content").add(collapse);
    }
}
