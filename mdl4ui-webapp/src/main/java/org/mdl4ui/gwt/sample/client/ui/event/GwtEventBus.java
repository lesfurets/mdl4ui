package org.mdl4ui.gwt.sample.client.ui.event;

import org.mdl4ui.fields.model.event.Event;
import org.mdl4ui.fields.model.event.EventBus;
import org.mdl4ui.fields.model.event.EventType;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class GwtEventBus extends SimpleEventBus implements EventBus {

    @Override
    public <H> void subscribe(EventType<H> type, H handler) {
        @SuppressWarnings("unchecked")
        final Type<H> eventType = (Type<H>) type;
        addHandler(eventType, handler);
    }

    @Override
    public <H> void subscribe(EventType<H> type, Object source, H handler) {
        @SuppressWarnings("unchecked")
        final Type<H> eventType = (Type<H>) type;
        addHandlerToSource(eventType, source, handler);
    }

    @Override
    public void publish(Event<?> event) {
        fireEvent((GwtEvent<?>) event);
    }

    @Override
    public void publish(Event<?> event, Object source) {
        fireEventFromSource((GwtEvent<?>) event, source);
    }
}
