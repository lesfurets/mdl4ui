package org.mdl4ui.gwt.sample.client;

import org.mdl4ui.fields.model.Screen;
import org.mdl4ui.fields.model.Wizard;
import org.mdl4ui.gwt.sample.client.ui.FieldView;
import org.mdl4ui.gwt.sample.client.ui.model.ScreenView;
import org.mdl4ui.ui.sample.EScreenSample;

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
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.RootPanel;

public class Sample implements EntryPoint {

    @Override
    public void onModuleLoad() {

        Wizard wizard = GWT.create(Wizard.class);
        final Screen screen = wizard.createScreen(EScreenSample.SCR_WIDGETS_SHOWCASE);
        final ScreenView view = new ScreenView(screen);


        RootPanel.get("content").add(view);
    }
}
