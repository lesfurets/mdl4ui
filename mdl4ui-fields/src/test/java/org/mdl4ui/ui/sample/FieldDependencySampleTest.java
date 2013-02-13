package org.mdl4ui.ui.sample;

import static org.mdl4ui.ui.sample.EFieldSample.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.junit.Test;

import org.mdl4ui.base.model.FieldID;

@Generated(value = "org.mdl4ui.maven.GenerateDepsTestMojo", date = "13/02/13 18:11")
public final class FieldDependencySampleTest {
	private final FieldDependencySampleFactory factory = new FieldDependencySampleFactory();
	
    @Test
    public void testFieldDependencies_TEXTBOX() {
        final List<FieldID> fields = Arrays.asList(factory.get(TEXTBOX));
        assertTrue(fields.contains(PASSWORD));
        assertTrue(fields.contains(NUMERIC));
        assertEquals(2, fields.size());

    }


}