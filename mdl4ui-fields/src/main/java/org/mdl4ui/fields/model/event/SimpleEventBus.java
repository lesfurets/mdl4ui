package org.mdl4ui.fields.model.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.mdl4ui.fields.model.event.Event.Type;

/**
 * Basic implementation of {@link EventBus}.
 */
public class SimpleEventBus implements EventBus {
    private interface Command {
        void execute();
    }

    private int firingDepth = 0;

    /**
     * Add and remove operations received during dispatch.
     */
    private List<Command> deferredDeltas;

    /**
     * Map of event type to map of event source to list of their handlers.
     */
    private final Map<Event.Type<?>, Map<Object, List<?>>> map = new HashMap<Event.Type<?>, Map<Object, List<?>>>();

    @Override
    public <H> void subscribe(Type<H> type, H handler) {
        doAdd(type, null, handler);
    }

    @Override
    public <H> void subscribe(final Event.Type<H> type, final Object source, final H handler) {
        if (source == null) {
            throw new NullPointerException("Cannot add a handler with a null source");
        }

        doAdd(type, source, handler);
    }

    @Override
    public void publish(Event<?> event) {
        doFire(event, null);
    }

    @Override
    public void publish(Event<?> event, Object source) {
        if (source == null) {
            throw new NullPointerException("Cannot fire from a null source");
        }
        doFire(event, source);
    }

    private void defer(Command command) {
        if (deferredDeltas == null) {
            deferredDeltas = new ArrayList<Command>();
        }
        deferredDeltas.add(command);
    }

    private <H> void doAdd(final Event.Type<H> type, final Object source, final H handler) {
        if (type == null) {
            throw new NullPointerException("Cannot add a handler with a null type");
        }
        if (handler == null) {
            throw new NullPointerException("Cannot add a null handler");
        }

        if (firingDepth > 0) {
            enqueueAdd(type, source, handler);
        } else {
            doAddNow(type, source, handler);
        }
    }

    private <H> void doAddNow(Event.Type<H> type, Object source, H handler) {
        List<H> l = ensureHandlerList(type, source);
        l.add(handler);
    }

    private <H> void doFire(Event<H> event, Object source) {
        if (event == null) {
            throw new NullPointerException("Cannot fire null event");
        }
        try {
            firingDepth++;

            if (source != null) {
                event.setSource(source);
            }

            List<H> handlers = getDispatchList(event.getAssociatedType(), source);
            Set<Throwable> causes = null;

            ListIterator<H> it = handlers.listIterator();
            while (it.hasNext()) {
                H handler = it.next();

                try {
                    event.dispatch(handler);
                } catch (Throwable e) {
                    if (causes == null) {
                        causes = new HashSet<Throwable>();
                    }
                    causes.add(e);
                }
            }

            if (causes != null) {
                throw new EventBusException(causes);
            }
        } finally {
            firingDepth--;
            if (firingDepth == 0) {
                handleQueuedAddsAndRemoves();
            }
        }
    }

    private <H> void enqueueAdd(final Event.Type<H> type, final Object source, final H handler) {
        defer(new Command() {
            @Override
            public void execute() {
                doAddNow(type, source, handler);
            }
        });
    }

    private <H> List<H> ensureHandlerList(Event.Type<H> type, Object source) {
        Map<Object, List<?>> sourceMap = map.get(type);
        if (sourceMap == null) {
            sourceMap = new HashMap<Object, List<?>>();
            map.put(type, sourceMap);
        }

        // safe, we control the puts.
        @SuppressWarnings("unchecked")
        List<H> handlers = (List<H>) sourceMap.get(source);
        if (handlers == null) {
            handlers = new ArrayList<H>();
            sourceMap.put(source, handlers);
        }

        return handlers;
    }

    private <H> List<H> getDispatchList(Event.Type<H> type, Object source) {
        List<H> directHandlers = getHandlerList(type, source);
        if (source == null) {
            return directHandlers;
        }

        List<H> globalHandlers = getHandlerList(type, null);

        List<H> rtn = new ArrayList<H>(directHandlers);
        rtn.addAll(globalHandlers);
        return rtn;
    }

    private <H> List<H> getHandlerList(Event.Type<H> type, Object source) {
        Map<Object, List<?>> sourceMap = map.get(type);
        if (sourceMap == null) {
            return Collections.emptyList();
        }

        // safe, we control the puts.
        @SuppressWarnings("unchecked")
        List<H> handlers = (List<H>) sourceMap.get(source);
        if (handlers == null) {
            return Collections.emptyList();
        }

        return handlers;
    }

    private void handleQueuedAddsAndRemoves() {
        if (deferredDeltas != null) {
            try {
                for (Command c : deferredDeltas) {
                    c.execute();
                }
            } finally {
                deferredDeltas = null;
            }
        }
    }
}
