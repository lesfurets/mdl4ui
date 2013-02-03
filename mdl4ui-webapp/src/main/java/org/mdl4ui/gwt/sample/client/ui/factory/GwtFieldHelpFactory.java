package org.mdl4ui.gwt.sample.client.ui.factory;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.FieldHelpFactory;

import com.google.gwt.core.shared.GWT;

public class GwtFieldHelpFactory implements FieldHelpFactory {

    public static final GwtFieldHelpFactory HELP = GWT.create(GwtFieldHelpFactory.class);

    private GwtFieldHelpFactory() {
    }

    @Override
    public String get(FieldID fieldID) {
        return "help content for field " + fieldID.toString().toLowerCase().replace("_", " ");
    }
}
