/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.model;

public interface ClientFactory {

    FieldLabelFactory getLabelFactory();

    FieldRendererFactory getRendererFactory();

    FieldPlaceholderFactory getPlaceholderFactory();

    FieldBehaviourFactory getBehaviourFactory();

    FieldEditorFactory getEditorFactory();

    FieldInitializerFactory getInitializerFactory();

    FieldHelpFactory getHelpFactory();

    FieldDependencyFactory getDependencyFactory();
}
