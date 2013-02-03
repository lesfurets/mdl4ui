package org.mdl4ui.fields.model.event;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

/**
 * A {@link RuntimeException} that collects a {@link Set} of child {@link Throwable}s together. Typically thrown after a
 * loop, with all of the exceptions thrown during that loop, but delayed so that the loop finishes executing.
 */
public class EventBusException extends RuntimeException {

    // Visible for testing
    static final String MULTIPLE = " exceptions caught: ";

    // Visible for testing
    static final String ONE = "Exception caught: ";

    protected static Throwable makeCause(Set<Throwable> causes) {
        Iterator<Throwable> iterator = causes.iterator();
        if (!iterator.hasNext()) {
            return null;
        }

        return iterator.next();
    }

    protected static String makeMessage(Set<Throwable> causes) {
        int count = causes.size();
        if (count == 0) {
            return null;
        }

        StringBuilder b = new StringBuilder(count == 1 ? ONE : count + MULTIPLE);
        boolean first = true;
        for (Throwable t : causes) {
            if (first) {
                first = false;
            } else {
                b.append("; ");
            }
            b.append(t.getMessage());
        }

        return b.toString();
    }

    /**
     * The causes of the exception.
     */
    private Set<Throwable> causes;

    public EventBusException(Set<Throwable> causes) {
        super(makeMessage(causes), makeCause(causes));
        this.causes = causes;
    }

    /**
     * Required for GWT RPC serialization.
     */
    protected EventBusException() {
        // Can't delegate to the other constructor or GWT RPC gets cranky
        super(MULTIPLE);
        this.causes = Collections.<Throwable> emptySet();
    }

    /**
     * Get the set of exceptions that caused the failure.
     * 
     * @return the set of causes
     */
    public Set<Throwable> getCauses() {
        return causes;
    }
}
