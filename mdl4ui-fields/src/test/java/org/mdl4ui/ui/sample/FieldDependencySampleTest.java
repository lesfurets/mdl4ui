package org.mdl4ui.ui.sample;

import static org.mdl4ui.ui.sample.EFieldSample.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.junit.Test;

import org.mdl4ui.base.model.FieldID;

@Generated(value = "org.mdl4ui.maven.GenerateDepsTestMojo", date = "14/02/13 10:44")
public final class FieldDependencySampleTest {
	private final FieldDependencySampleFactory factory = new FieldDependencySampleFactory();
	
    @Test
    public void testFieldDependencies_EMAIL() {
        final List<FieldID> fields = Arrays.asList(factory.get(EMAIL));
        assertTrue(fields.contains(EMAILS_PREFERENCES));
        assertTrue(fields.contains(MAX_WEEKLY_EMAILS));
        assertEquals(2, fields.size());

    }


}