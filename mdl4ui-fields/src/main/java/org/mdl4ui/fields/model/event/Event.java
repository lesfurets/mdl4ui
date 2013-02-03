package org.mdl4ui.fields.model.event;

/**
 * Base Event object.
 * 
 * @param <H> interface implemented by handlers of this kind of event
 */
public abstract class Event<H> {
    /**
     * Type class used to register events with an {@link EventBus}.
     * 
     * @param <H> handler type
     */
    public static class Type<H> {
        private static int nextHashCode;
        private final int index;

        /**
         * Constructor.
         */
        public Type() {
            index = ++nextHashCode;
        }

        @Override
        public final int hashCode() {
            return index;
        }

        @Override
        public String toString() {
            return "Event type";
        }
    }

    private Object source;

    /**
     * Constructor.
     */
    protected Event() {
    }

    /**
     * Returns the {@link Type} used to register this event, allowing an {@link EventBus} to find handlers of the
     * appropriate class.
     * 
     * @return the type
     */
    public abstract Type<H> getAssociatedType();

    /**
     * Returns the source for this event. The type and meaning of the source is arbitrary, and is most useful as a
     * secondary key for handler registration. (See {@link EventBus#addHandlerToSource}, which allows a handler to
     * register for events of a particular type, tied to a particular source.)
     * <p>
     * Note that the source is actually set at dispatch time, e.g. via {@link EventBus#publish(Event, Object)}.
     * 
     * @return object representing the source of this event
     */
    public Object getSource() {
        return source;
    }

    /**
     * The toString() for abstract event is overridden to avoid accidently including class literals in the the compiled
     * output. Use {@link Event} #toDebugString to get more information about the event.
     */
    @Override
    public String toString() {
        String name = this.getClass().getName();
        name = name.substring(name.lastIndexOf(".") + 1);
        return "event: " + name + ":";
    }

    /**
     * Implemented by subclasses to to invoke their handlers in a type safe manner. Intended to be called by
     * {@link EventBus#publish(Event)} or {@link EventBus#publish(Event, Object)}.
     * 
     * @param handler handler
     */
    protected abstract void dispatch(H handler);

    /**
     * Set the source that triggered this event. Intended to be called by the {@link EventBus} during dispatch.
     * 
     * @param source the source of this event.
     * @see EventBus#publish(Event, Object)
     */
    protected void setSource(Object source) {
        this.source = source;
    }
}
