package org.mdl4ui.sample.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class Sample implements EntryPoint {

    @Override
    public void onModuleLoad() {
        RootPanel.get("content").add(new Label("It works!"));
    }
}
