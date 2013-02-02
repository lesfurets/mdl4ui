package org.mdl4ui.gwt.sample.client.ui.factory;

import org.mdl4ui.base.model.ElementID;

import com.google.gwt.core.shared.GWT;

public class FieldLabelFactory {

    public static final FieldLabelFactory LABEL = GWT.create(FieldLabelFactory.class);

    private FieldLabelFactory() {
    }

    public String get(ElementID elementID) {
        final String label = elementID.toString().toLowerCase().replace("_", " ");
        return Character.toUpperCase(label.charAt(0)) + label.substring(1);
    }
}
