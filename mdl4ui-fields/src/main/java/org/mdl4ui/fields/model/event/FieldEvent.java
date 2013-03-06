/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.fields.model.event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.mdl4ui.base.model.ElementID;
import org.mdl4ui.base.model.FieldID;

public final class FieldEvent implements Serializable {

    private static final long initialTime = System.currentTimeMillis();
    private long currentTime;
    private long eventId = 0;
    private FieldID fieldId;
    private EventKind kind;
    private ElementID sourceId;
    private EventProperty sourceProperty;
    private String oldValue;
    private String newValue;
    private List<String> parameters = new ArrayList<String>();

    private static final List<ElementID> SOURCE_Q = new ArrayList<ElementID>();
    private static final FieldEvent INSTANCE = new FieldEvent();

    public FieldEvent() {
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public EventKind getKind() {
        return kind;
    }

    public void setKind(EventKind kind) {
        this.kind = kind;
    }

    public EventProperty getSourceProperty() {
        return sourceProperty;
    }

    public void setSourceProperty(EventProperty property) {
        this.sourceProperty = property;
    }

    public FieldID getFieldId() {
        return fieldId;
    }

    public void setFieldId(FieldID fieldId) {
        this.fieldId = fieldId;
    }

    public ElementID getSourceId() {
        return sourceId;
    }

    public void setSourceId(ElementID sourceId) {
        this.sourceId = sourceId;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public List<String> getParameters() {
        return this.parameters;
    }

    @Override
    public String toString() {
        return "FieldEvent [currentTime=" + currentTime + ", eventId=" + eventId + ", fieldId=" + fieldId + ", kind="
                        + kind + ", sourceId=" + sourceId + ", sourceProperty=" + sourceProperty + ", oldValue="
                        + oldValue + ", newValue=" + newValue + "]";
    }

    public static FieldEvent newEvent(final ElementID source, final EventProperty property) {
        INSTANCE.currentTime = System.currentTimeMillis() - initialTime;
        INSTANCE.oldValue = null;
        INSTANCE.newValue = null;
        INSTANCE.parameters.clear();
        if (SOURCE_Q.isEmpty()) {
            INSTANCE.eventId++;
            INSTANCE.sourceProperty = property;
            INSTANCE.sourceId = source;
        }
        SOURCE_Q.add(source);
        return INSTANCE;
    }

    public static void releaseSourceEvent() {
        if (!SOURCE_Q.isEmpty())
            SOURCE_Q.remove(SOURCE_Q.size() - 1);
    }

    public FieldEvent clone() {
        final FieldEvent event = new FieldEvent();
        event.eventId = INSTANCE.eventId;
        event.currentTime = INSTANCE.currentTime;
        event.fieldId = INSTANCE.fieldId;
        event.kind = INSTANCE.kind;
        event.sourceProperty = INSTANCE.sourceProperty;
        event.sourceId = INSTANCE.sourceId;
        event.newValue = INSTANCE.newValue;
        event.oldValue = INSTANCE.oldValue;
        return event;
    }
}
