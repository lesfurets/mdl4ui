package org.mdl4ui.gwt.sample.client.ui.factory;

import org.mdl4ui.base.model.ElementID;
import org.mdl4ui.fields.model.FieldLabelFactory;

import com.google.gwt.core.shared.GWT;

public class GwtFieldLabelFactory implements FieldLabelFactory {

    public static final GwtFieldLabelFactory LABEL = GWT.create(GwtFieldLabelFactory.class);

    private GwtFieldLabelFactory() {
    }

    @Override
    public String get(ElementID elementID) {
        final String label = elementID.toString().toLowerCase().replace("_", " ");
        return Character.toUpperCase(label.charAt(0)) + label.substring(1);
    }
}
