/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.sample;

import org.mdl4ui.base.model.ElementID;
import org.mdl4ui.fields.model.FieldHelpFactory;
import org.mdl4ui.fields.model.FieldLabelFactory;
import org.mdl4ui.fields.model.FieldMessageFactory;
import org.mdl4ui.fields.model.FieldPlaceholderFactory;
import org.mdl4ui.fields.sample.i18n.BundleFieldHelpFactory;
import org.mdl4ui.fields.sample.i18n.BundleFieldLabelFactory;
import org.mdl4ui.fields.sample.i18n.BundleFieldPlaceholderFactory;

public enum BundleFactory {
    INSTANCE;

    private final FieldLabelFactory[] labelFactories;
    private final FieldHelpFactory[] helpFactories;
    private final FieldPlaceholderFactory[] placeholderFactories;

    BundleFactory() {
        labelFactories = new FieldLabelFactory[] { new BundleFieldLabelFactory() };
        helpFactories = new FieldHelpFactory[] { new BundleFieldHelpFactory() };
        placeholderFactories = new FieldPlaceholderFactory[] { new BundleFieldPlaceholderFactory() };
    }

    public String getLabel(ElementID elementId) {
        return get(labelFactories, elementId);
    }

    public String getHelp(ElementID elementId) {
        return get(helpFactories, elementId);
    }

    public String getPlaceHolder(ElementID elementId) {
        return get(placeholderFactories, elementId);
    }

    private static final <F extends FieldMessageFactory> String get(F[] labelFactories, ElementID elementId) {
        for (F factory : labelFactories) {
            final String label = factory.get(elementId);
            if (label != null)
                return label;
        }
        return null;
    }
}
