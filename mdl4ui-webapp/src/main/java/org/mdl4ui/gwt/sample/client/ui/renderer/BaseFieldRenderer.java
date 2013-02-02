package org.mdl4ui.gwt.sample.client.ui.renderer;

import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.FieldRenderer;
import org.mdl4ui.fields.model.component.FieldComponent;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public abstract class BaseFieldRenderer<C extends FieldComponent<?>> implements FieldRenderer<C>, IsWidget {

    private C component;

    @Override
    public C getFieldComponent(FieldID fieldID) {
        if (component == null) {
            component = createFieldComponent(fieldID);
        }
        return component;
    }

    @Override
    public Widget asWidget() {
        return (Widget) component;
    }

    @Override
    public boolean isLabelInline() {
        return false;
    }

    @Override
    public void setLabel(String label, FieldID fieldID) {
        // nothing by default
    }

    protected abstract C createFieldComponent(FieldID fieldID);

}
