package org.mdl4ui.gwt.model.client.widget;

import java.util.Date;

import org.mdl4ui.fields.model.component.DateField;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;

public class DateBox extends com.github.gwtbootstrap.datepicker.client.ui.DateBox implements DateField,
                HasValueChangeHandlers<Date> {

    private Date startDate;
    private Date endDate;

    @Override
    public boolean isEmpty() {
        return getValue() == null;
    }

    @Override
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
        setStartDate_(startDate);
    }

    @Override
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
        setEndDate_(endDate);
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public Date getEndDate() {
        return endDate;
    }

    @Override
    public boolean isDateInRange() {
        Date value = getValue();
        if (startDate != null && startDate.after(value)) {
            return false;
        }
        if (endDate != null && endDate.before(value)) {
            return false;
        }
        return true;
    }
}
