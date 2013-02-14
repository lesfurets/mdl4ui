/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.ui.sample;

import org.mdl4ui.fields.model.ClientFactory;
import org.mdl4ui.fields.model.FieldBehaviourFactory;
import org.mdl4ui.fields.model.FieldDependencyFactory;
import org.mdl4ui.fields.model.FieldEditorFactory;
import org.mdl4ui.fields.model.FieldHelpFactory;
import org.mdl4ui.fields.model.FieldInitializerFactory;
import org.mdl4ui.fields.model.FieldLabelFactory;
import org.mdl4ui.fields.model.FieldPlaceholderFactory;
import org.mdl4ui.fields.model.FieldRendererFactory;
import org.mdl4ui.fields.sample.behaviour.MockFieldBehaviourFactory;
import org.mdl4ui.fields.sample.editor.MockFieldEditorFactory;
import org.mdl4ui.fields.sample.i18n.BundleFieldHelpFactory;
import org.mdl4ui.fields.sample.i18n.BundleFieldLabelFactory;
import org.mdl4ui.fields.sample.i18n.BundleFieldPlaceholderFactory;
import org.mdl4ui.fields.sample.initializer.MockFieldInitializerFactory;

public class MockClientFactory implements ClientFactory {

    private final FieldLabelFactory labelFactory = new BundleFieldLabelFactory();
    private final FieldPlaceholderFactory placeholderFactory = new BundleFieldPlaceholderFactory();
    private final FieldBehaviourFactory behaviourFactory = new MockFieldBehaviourFactory();
    private final FieldEditorFactory editorFactory = new MockFieldEditorFactory();
    private final FieldInitializerFactory initalizerFactory = new MockFieldInitializerFactory();
    private final FieldHelpFactory helpFactory = new BundleFieldHelpFactory();
    private final FieldDependencyFactory dependencyFactory = new FieldDependencySampleFactory();

    @Override
    public FieldLabelFactory getLabelFactory() {
        return labelFactory;
    }

    @Override
    public FieldRendererFactory getRendererFactory() {
        throw new RuntimeException("no mock FieldRendererFactory implementation");
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
