package org.mdl4ui.fields.model.event;

public interface EventBus {

    <H> void subscribe(EventType<H> type, H handler);

    <H> void subscribe(EventType<H> type, Object source, H handler);

    void publish(Event<?> event);

    void publish(Event<?> event, Object source);
}
