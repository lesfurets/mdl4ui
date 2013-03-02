package org.mdl4ui.fields.model;

import static java.util.Arrays.asList;
import static org.mdl4ui.fields.model.DefaultEditor.valid;
import static org.mdl4ui.fields.model.EFieldState.ERROR;
import static org.mdl4ui.fields.model.EFieldState.SET;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.mdl4ui.base.model.ApplicationID;
import org.mdl4ui.base.model.BlockID;
import org.mdl4ui.base.model.ElementID;
import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.base.model.GroupID;
import org.mdl4ui.base.model.ScreenID;
import org.mdl4ui.fields.model.validation.FieldValidation;

public class DefaultWizard implements Wizard {

    private final ClientFactory clientFactory;
    private final Map<ScreenID, Screen> screens;
    private final WizardContext context;

    private ApplicationID application;
    private Screen currentScreen;

    public DefaultWizard(WizardContext context, ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
        this.screens = new HashMap<ScreenID, Screen>();
        this.context = context;
    }

    @Override
    public void displayScreen(Screen screen) {
        currentScreen = screen;
        for (Field field : screen.fields()) {
            updateBehaviour(field);
        }
    }

    @Override
    public WizardContext getContext() {
        return context;
    }

    @Override
    public Map<ScreenID, Screen> getScreens() {
        return screens;
    }

    public ClientFactory getClientFactory() {
        return clientFactory;
    }

    public ApplicationID getApplication() {
        return application;
    }

    @Override
    public void addScreens(ApplicationID application) {
        this.application = application;
        for (ScreenID screenID : application.screens()) {
            List<Block> blocks = new ArrayList<Block>();
            for (BlockID blockId : screenID.blocks()) {
                List<Element> blockItems = new ArrayList<Element>();
                for (ElementID child : blockId.childs()) {
                    switch (child.elementType()) {
                        case FIELD:
                            blockItems.add(createField(screenID, (FieldID) child));
                            break;
                        case GROUP:
                            ArrayList<Field> fields = new ArrayList<Field>();
                            for (FieldID fieldId : ((GroupID) child).fields()) {
                                fields.add(createField(screenID, fieldId));
                            }
                            blockItems.add(new Group((GroupID) child, fields));
                            break;
                    }
                }
                Block block = new Block(clientFactory.getLabelFactory().get(blockId), blockId);
                block.add(blockItems);
                blocks.add(block);
            }
            Screen screen = new Screen(screenID, blocks);
            screens.put(screenID, screen);
        }
    }

    private Field createField(ScreenID screenId, FieldID fieldId) {
        Field field = new Field(fieldId, clientFactory.getLabelFactory().get(fieldId),//
                        clientFactory.getHelpFactory().get(fieldId),//
                        clientFactory.getPlaceholderFactory().get(fieldId),//
                        clientFactory.getRendererFactory().get(fieldId));
        FieldInitializer initializer = clientFactory.getInitializerFactory().get(fieldId);
        if (initializer != null) {
            initializer.init(field);
        }
        return field;
    }

    @Override
    public boolean isVisible(FieldID fieldId) {
        return clientFactory.getBehaviourFactory().get(fieldId).isVisible(fieldId, getContext());
    }

    @Override
    public void updateField(Field field) {
        // validate & update context if visible
        if (isVisible(field.getFieldID())) {
            updateContext(field);
            validate(field);
        }

        // update field dependencies
        final List<FieldID> fieldDeps = asList(clientFactory.getDependencyFactory().get(field.getFieldID()));
        final Set<FieldID> deps = new HashSet<FieldID>(fieldDeps);
        if (!deps.isEmpty()) {
            for (Screen screen : screens.values()) {
                for (Field otherField : screen.fields()) {
                    if (deps.contains(otherField.getFieldID())) {
                        updateBehaviour(otherField);
                    }
                }
            }
        }

        // validate field dependencies already set
        for (FieldID dep : deps) {
            for (Field depField : currentScreen.fields()) {
                if (depField.getFieldID() == dep && depField.getState() == SET) {
                    validate(depField);
                }
            }
        }
    }

    private final void updateBehaviour(Field field) {
        clientFactory.getBehaviourFactory().get(field.getFieldID()).updateValue(field, context);
        boolean visibleBeforeUpdate = field.getState() != EFieldState.HIDDEN;
        boolean visibleAfterUpdate = isVisible(field.getFieldID());
        if (!visibleBeforeUpdate && visibleAfterUpdate) {
            // update field using context, if field became visible
            updateFromContext(field);
            field.setState(EFieldState.DEFAULT, null);
        } else if (!visibleAfterUpdate) {
            final FieldEditor editor = clientFactory.getEditorFactory().get(field.getFieldID());
            if (editor != null) {
                // reset field if not visible anymore
                editor.reset(field, context);
            }
            field.setState(EFieldState.HIDDEN, null);
        }
    }

    @Override
    public final void updateFromContext(Field field) {
        final FieldEditor editor = clientFactory.getEditorFactory().get(field.getFieldID());
        if (editor == null)
            return;
        editor.updateFromContext(field, getContext());
    }

    @Override
    public final void updateContext(Field field) {
        final FieldEditor editor = clientFactory.getEditorFactory().get(field.getFieldID());
        if (editor == null)
            return;
        editor.updateContext(field, getContext());
    }

    @Override
    public final FieldValidation validate(Field field) {
        final FieldEditor editor = clientFactory.getEditorFactory().get(field.getFieldID());

        // skip field if not visible or no visible field associated
        if (!isVisible(field.getFieldID()) || editor == null) {
            return valid(field);
        }

        // validate field
        final FieldValidation validation = editor.validate(field, getContext());

        // update field state according to validation result
        final Field validated = getField(getScreen(field).getScreenID(), validation.getFieldID());
        validated.setState(validation != null && !validation.isValid() ? ERROR : SET, validation); // update field state
        return validation;
    }

    private Field getField(ScreenID screenID, FieldID fieldID) {
        for (Screen screen : screens.values()) {
            if (screen.getScreenID() == screenID) {
                for (Field field : screen.fields()) {
                    if (field.getFieldID() == fieldID) {
                        return field;
                    }
                }
            }
        }
        return null;
    }

    private Screen getScreen(Field field) {
        for (Screen screen : screens.values()) {
            if (screen.fields().contains(field)) {
                return screen;
            }
        }
        return null;
    }

    @Override
    public void submit(Block block) {
        for (Field field : block.fields()) {
            updateField(field);
        }
        // TODO transition to next block/screen
    }
}
