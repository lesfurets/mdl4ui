package org.mdl4ui.gwt.model.client.ui;

import static org.mdl4ui.fields.model.event.FieldEvent.newEvent;
import static org.mdl4ui.fields.model.event.FieldEvent.releaseSourceEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mdl4ui.base.model.BlockID;
import org.mdl4ui.base.model.ScreenID;
import org.mdl4ui.fields.model.Block;
import org.mdl4ui.fields.model.DefaultWizard;
import org.mdl4ui.fields.model.Field;
import org.mdl4ui.fields.model.Screen;
import org.mdl4ui.fields.model.Wizard;
import org.mdl4ui.fields.model.event.EventProperty;
import org.mdl4ui.fields.model.event.FieldEvent;
import org.mdl4ui.gwt.model.client.factory.GwtScreenFactory;

import com.github.gwtbootstrap.client.ui.Container;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class WizardView implements IsWidget {

    private final Container container;
    private final GwtScreenFactory screenFactory = GWT.create(GwtScreenFactory.class);
    private final Map<ScreenID, ScreenView> screens = new HashMap<ScreenID, ScreenView>();

    public WizardView(final DefaultWizard wizard) {
        Screen firstScreen = null;
        for (final ScreenID screenID : wizard.getScreens().keySet()) {
            final ScreenView screenView = screenFactory.getView(wizard.getScreens().get(screenID));
            if (firstScreen == null) {
                firstScreen = screenView.getScreen();
            }

            for (final Field field : screenView.getScreen().fields()) {
                @SuppressWarnings({ "rawtypes", "unchecked" })
                HasValueChangeHandlers<Object> hasChangeHandler = (HasValueChangeHandlers) field.getComponent();
                hasChangeHandler.addValueChangeHandler(new ValueChangeHandler<Object>() {
                    @Override
                    public void onValueChange(ValueChangeEvent<Object> event) {
                        updateField(wizard, screenView, field);
                    }
                });
            }

            for (final BlockView blockView : screenView.blocks()) {
                blockView.getSubmit().addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        submitBlock(wizard, screenID, screenView, blockView);
                    }
                });

                blockView.getModify().addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        modifyBlock(screenView, blockView);
                    }
                });
            }

            screens.put(screenID, screenView);
        }
        container = new Container();
    }

    @Override
    public Widget asWidget() {
        return container;
    }

    private void updateField(final DefaultWizard wizard, final ScreenView screenView, final Field field) {
        final FieldEvent event = newEvent(field.getFieldID(), EventProperty.FIELD);
        try {
            wizard.updateField(field, event);
        } finally {
            releaseSourceEvent();
        }
        for (FieldView fieldView : screenView.fields()) {
            fieldView.updateField();
        }
    }

    private void submitBlock(final DefaultWizard wizard, final ScreenID screenID, final ScreenView screenView,
                    final BlockView blockView) {
        Block block = blockView.getBlock();
        wizard.submit(block);
        for (FieldView fieldView : screenView.fields()) {
            fieldView.updateField();
        }
        if (block.isValid()) {
            blockView.collapse();
            blockView.getModify().setVisible(true);
            BlockID nextBlock = screenID.nextBlock(block.getBlockID());
            if (nextBlock != null) {
                for (BlockView screenBlock : screenView.blocks()) {
                    if (screenBlock.getBlock().getBlockID() == nextBlock) {
                        screenBlock.expand();
                    }
                }
            } else {
                submitScreen(wizard, screenID);
            }
        }
    }

    private void submitScreen(final DefaultWizard wizard, final ScreenID screenID) {
        ScreenID nextScreen = wizard.getScenario().nextScreen(screenID);
        if (nextScreen != null) {
            displayScreen(wizard, nextScreen);
        }
    }

    private void modifyBlock(ScreenView screenView, BlockView blockView) {
        final List<BlockView> blocks = screenView.blocks();
        boolean canBeModified = true;
        for (BlockView otherBlock : blocks) {
            if (blockView == otherBlock) {
                otherBlock.expand();
                canBeModified = false;
            } else {
                otherBlock.collapse();
                otherBlock.getModify().setVisible(canBeModified);
            }
        }
    }

    public void displayScreen(Wizard wizard, ScreenID screenID) {
        container.clear();
        ScreenView screenView = screens.get(screenID);
        if (screenView == null) {
            throw new IllegalArgumentException("unknow screen id : " + screenView);
        }

        screenView.onDisplay(wizard.getContext());
        container.add(screenView);

        wizard.displayScreen(screenView.getScreen());
        for (FieldView fieldView : screenView.fields()) {
            fieldView.updateField();
        }

        List<BlockView> blocks = screenView.blocks();
        if (!blocks.isEmpty()) {
            blocks.get(0).expand();
            blocks.remove(0);
            for (BlockView blockView : blocks) {
                blockView.collapse();
                blockView.getModify().setVisible(false);
            }
        }
    }

}
