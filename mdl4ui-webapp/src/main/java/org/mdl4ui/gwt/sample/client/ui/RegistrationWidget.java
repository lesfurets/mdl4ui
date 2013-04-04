/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.gwt.sample.client.ui;

import com.github.gwtbootstrap.client.ui.Column;
import com.github.gwtbootstrap.client.ui.Container;
import com.github.gwtbootstrap.client.ui.Legend;
import com.github.gwtbootstrap.client.ui.Paragraph;
import com.github.gwtbootstrap.client.ui.Row;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

abstract class RegistrationWidget implements IsWidget {

    protected final Container container;

    public RegistrationWidget() {
        this.container = new Container();
    }

    protected final void addRow(String label, String value) {
        Row row = new Row();
        Column labelColumn = new Column(3);
        labelColumn.add(new Paragraph(label));
        row.add(labelColumn);

        Column valueColumn = new Column(2);
        valueColumn.add(new Paragraph(value));
        row.add(valueColumn);

        container.add(row);
    }

    protected final void addLegend(String label) {
        Row row = new Row();
        row.add(new Column(5, new Legend(label)));
        container.add(row);
    }

    @Override
    public final Widget asWidget() {
        return container;
    }
}
