package org.mdl4ui.gwt.sample.client.ui.factory;

import org.mdl4ui.base.model.ElementID;

import com.google.gwt.core.shared.GWT;

public class FieldLabelFactory {

    public static final FieldLabelFactory LABEL = GWT.create(FieldLabelFactory.class);

    private FieldLabelFactory() {
    }

    public String get(ElementID elementID) {
        return "help content for field " + elementID.toString().toLowerCase().replace("_", " ");
    }
}
