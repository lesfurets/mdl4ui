package org.mdl4ui.gwt.model.client.widget;

import java.util.Date;

import org.mdl4ui.fields.model.component.DateField;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;

public class DateBox extends com.github.gwtbootstrap.datepicker.client.ui.DateBox implements DateField,
                HasValueChangeHandlers<Date> {
}
