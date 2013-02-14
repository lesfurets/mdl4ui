/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.gwt.sample.client.ui.factory;

import org.mdl4ui.fields.model.ClientFactory;
import org.mdl4ui.fields.model.FieldBehaviourFactory;
import org.mdl4ui.fields.model.FieldDependencyFactory;
import org.mdl4ui.fields.model.FieldEditorFactory;
import org.mdl4ui.fields.model.FieldHelpFactory;
import org.mdl4ui.fields.model.FieldInitializerFactory;
import org.mdl4ui.fields.model.FieldLabelFactory;
import org.mdl4ui.fields.model.FieldPlaceholderFactory;
import org.mdl4ui.fields.model.FieldRendererFactory;
import org.mdl4ui.fields.sample.behaviour.GwtFieldBehaviourFactory;
import org.mdl4ui.fields.sample.editor.GwtFieldEditorFactory;
import org.mdl4ui.fields.sample.i18n.GwtFieldHelpFactory;
import org.mdl4ui.fields.sample.i18n.GwtFieldLabelFactory;
import org.mdl4ui.fields.sample.i18n.GwtFieldPlaceholderFactory;
import org.mdl4ui.fields.sample.initializer.GwtFieldInitializerFactory;
import org.mdl4ui.ui.sample.FieldDependencySampleFactory;

import com.google.gwt.core.shared.GWT;

public class GwtClientFactory implements ClientFactory {

    private final FieldLabelFactory labelFactory = GWT.create(GwtFieldLabelFactory.class);
    private final FieldRendererFactory rendererFactory = GWT.create(GwtFieldRendererFactory.class);
    private final FieldPlaceholderFactory placeholderFactory = GWT.create(GwtFieldPlaceholderFactory.class);
    private final FieldBehaviourFactory behaviourFactory = GWT.create(GwtFieldBehaviourFactory.class);
    private final FieldEditorFactory editorFactory = GWT.create(GwtFieldEditorFactory.class);
    private final FieldInitializerFactory initalizerFactory = GWT.create(GwtFieldInitializerFactory.class);
    private final FieldHelpFactory helpFactory = GWT.create(GwtFieldHelpFactory.class);
    private final FieldDependencyFactory dependencyFactory = GWT.create(FieldDependencySampleFactory.class);

    @Override
    public FieldLabelFactory getLabelFactory() {
        return labelFactory;
    }

    @Override
    public FieldRendererFactory getRendererFactory() {
        return rendererFactory;
    }

    @Override
    public FieldPlaceholderFactory getPlaceholderFactory() {
        return placeholderFactory;
    }

    @Override
    public FieldBehaviourFactory getBehaviourFactory() {
        return behaviourFactory;
    }

    @Override
    public FieldEditorFactory getEditorFactory() {
        return editorFactory;
    }

    @Override
    public FieldInitializerFactory getInitializerFactory() {
        return initalizerFactory;
    }

    @Override
    public FieldHelpFactory getHelpFactory() {
        return helpFactory;
    }

    @Override
    public FieldDependencyFactory getDependencyFactory() {
        return dependencyFactory;
    }

}
