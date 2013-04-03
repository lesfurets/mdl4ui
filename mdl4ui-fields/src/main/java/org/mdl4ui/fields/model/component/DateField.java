package org.mdl4ui.fields.model.component;

import java.util.Date;

public interface DateField extends FieldComponent<Date> {

    void setStartDate(Date startDate);

    void setEndDate(Date endDate);

    Date getStartDate();

    Date getEndDate();

    boolean isDateInRange();
}
