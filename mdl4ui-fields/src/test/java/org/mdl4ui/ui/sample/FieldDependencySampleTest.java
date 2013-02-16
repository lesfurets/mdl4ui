package org.mdl4ui.ui.sample;

import static org.mdl4ui.ui.sample.EFieldSample.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.junit.Test;

import org.mdl4ui.base.model.FieldID;

@Generated(value = "org.mdl4ui.maven.GenerateDepsTestMojo", date = "16/02/13 14:53")
public final class FieldDependencySampleTest {
	private final FieldDependencySampleFactory factory = new FieldDependencySampleFactory();
	
    @Test
    public void testFieldDependencies_EMAIL_ACCEPTED() {
        final List<FieldID> fields = Arrays.asList(factory.get(EMAIL_ACCEPTED));
        assertTrue(fields.contains(EMAILS_PREFERENCES));
        assertTrue(fields.contains(MAX_WEEKLY_EMAILS));
        assertEquals(2, fields.size());

    }

    @Test
    public void testFieldDependencies_PASSWORD() {
        final List<FieldID> fields = Arrays.asList(factory.get(PASSWORD));
        assertTrue(fields.contains(PASSWORD_CONFIRMATION));
        assertEquals(1, fields.size());

    }

    @Test
    public void testFieldDependencies_PASSWORD_CONFIRMATION() {
        final List<FieldID> fields = Arrays.asList(factory.get(PASSWORD_CONFIRMATION));
        assertTrue(fields.contains(PASSWORD));
        assertEquals(1, fields.size());

    }


}