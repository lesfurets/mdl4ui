package org.mdl4ui.gwt.sample.client.ui.factory;

import org.mdl4ui.base.model.FieldID;

import com.google.gwt.core.shared.GWT;

public class FieldHelplFactory {

    public static final FieldHelplFactory HELP = GWT.create(FieldHelplFactory.class);

    private FieldHelplFactory() {
    }

    public String get(FieldID fieldID) {
        return "help content for field " + fieldID.toString().toLowerCase().replace("_", " ");
    }
}
