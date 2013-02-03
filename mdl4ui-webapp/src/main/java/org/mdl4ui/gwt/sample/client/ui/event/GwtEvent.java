package org.mdl4ui.gwt.sample.client.ui.event;

import org.mdl4ui.fields.model.event.Event;

import com.google.gwt.event.shared.EventHandler;

public abstract class GwtEvent<H extends EventHandler> extends com.google.gwt.event.shared.GwtEvent<H> implements
                Event<H> {
}
