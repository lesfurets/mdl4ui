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

    /**
     * @return <code>true</code> if the widget contains the label, <code>false</code> by default
     */
    @Override
    public boolean isLabelInline() {
        return false;
    }

    /**
     * called if the label is inlined, set widget label
     */
    @Override
    public void setLabel(String label, FieldID fieldID) {
        // nothing by default
    }

    protected abstract C createFieldComponent(FieldID fieldID);

}
