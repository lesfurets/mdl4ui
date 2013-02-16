package org.mdl4ui.gwt.sample.client.ui.model;

import java.util.HashMap;
import java.util.Map;

import org.mdl4ui.base.model.ScreenID;
import org.mdl4ui.fields.model.Block;
import org.mdl4ui.fields.model.DefaultWizard;
import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.Screen;
import org.mdl4ui.fields.model.Wizard;

import com.github.gwtbootstrap.client.ui.FluidContainer;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class WizardView implements IsWidget {

    private final FluidContainer container;
    private final Map<ScreenID, ScreenView> screens = new HashMap<ScreenID, ScreenView>();

    public WizardView(final DefaultWizard wizard) {
        Screen firstScreen = null;
        for (ScreenID screenID : wizard.getScreens().keySet()) {
            final ScreenView screenView = new ScreenView(wizard.getScreens().get(screenID));
            if (firstScreen == null) {
                firstScreen = screenView.getScreen();
            }

            for (final Field field : screenView.getScreen().fields()) {
                @SuppressWarnings({ "rawtypes", "unchecked" })
                HasValueChangeHandlers<Object> hasChangeHandler = (HasValueChangeHandlers) field.getComponent();
                hasChangeHandler.addValueChangeHandler(new ValueChangeHandler<Object>() {
                    @Override
                    public void onValueChange(ValueChangeEvent<Object> event) {
                        wizard.updateField(field);
                        for (FieldView fieldView : screenView.fields()) {
                            fieldView.updateField();
                        }
                    }
                });
            }

            for (final BlockView blockView : screenView.blocks()) {
                blockView.getSubmit().addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        Block block = blockView.getBlock();
                        wizard.submit(block);
                        for (FieldView fieldView : screenView.fields()) {
                            fieldView.updateField();
                        }
                    }
                });
            }

            screens.put(screenID, screenView);
        }
        container = new FluidContainer();
    }

    public void displayScreen(Wizard wizard, ScreenID screenID) {
        container.clear();
        ScreenView screenView = screens.get(screenID);
        if (screenView == null) {
            throw new IllegalArgumentException("unknow screen id : " + screenView);
        }
        container.add(screenView);
        wizard.displayScreen(screenView.getScreen());
        for (FieldView fieldView : screenView.fields()) {
            fieldView.updateField();
        }
    }

    @Override
    public Widget asWidget() {
        return container;
    }

}
