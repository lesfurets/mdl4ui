package org.mdl4ui.gwt.sample.client.ui.model;

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
        for (final ScreenID screenID : wizard.getScreens().keySet()) {
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
        container = new FluidContainer();
    }

    private void updateField(final DefaultWizard wizard, final ScreenView screenView, final Field field) {
        wizard.updateField(field);
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
            for (BlockView screenBlock : screenView.blocks()) {
                if (screenBlock.getBlock().getBlockID() == nextBlock) {
                    screenBlock.expand();
                }
            }
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
        container.add(screenView);
        wizard.displayScreen(screenView.getScreen());
        for (FieldView fieldView : screenView.fields()) {
            fieldView.updateField();
        }

        List<BlockView> blocks = screenView.blocks();
        blocks.get(0).expand();
        blocks.remove(0);
        for (BlockView blockView : blocks) {
            blockView.collapse();
            blockView.getModify().setVisible(false);
        }
    }

    @Override
    public Widget asWidget() {
        return container;
    }

}
