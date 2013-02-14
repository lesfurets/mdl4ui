package org.mdl4ui.ui.sample;

import static org.mdl4ui.ui.sample.EFieldSample.*;
import static org.mdl4ui.ui.sample.EScreenSample.*;
import static org.mdl4ui.ui.sample.EBlockSample.*;
import static org.mdl4ui.ui.sample.EGroupSample.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.junit.Test;

import org.mdl4ui.base.model.*;

@Generated(value = "org.mdl4ui.maven.GenerateDepsTestMojo", date = "14/02/13 09:59")
public final class FieldOrderSampleTest {

    @Test
    public void testScreenBlocks_SCR_WIDGETS_SHOWCASE() {
        final List<BlockID> blocks = SCR_WIDGETS_SHOWCASE.blocks();
		assertEquals(7, SCR_WIDGETS_SHOWCASE.fields().size());
        assertTrue(blocks.contains(TEXT_FIELDS));
        assertTrue(blocks.contains(OTHER_FIELDS));

    }


    @Test
    public void testScreenBlocksOrder_SCR_WIDGETS_SHOWCASE() {
        final List<BlockID> blocks = SCR_WIDGETS_SHOWCASE.blocks();
        assertEquals(0, blocks.indexOf(TEXT_FIELDS));
        assertEquals(1, blocks.indexOf(OTHER_FIELDS));
        assertEquals(2, blocks.size());

    }


    @Test
    public void testScreenFieldsOrder_SCR_WIDGETS_SHOWCASE() {
        final List<FieldID> fields = SCR_WIDGETS_SHOWCASE.fields();
        assertEquals(0, fields.indexOf(TEXTBOX));
        assertEquals(1, fields.indexOf(PASSWORD));
        assertEquals(2, fields.indexOf(NUMERIC));
        assertEquals(3, fields.indexOf(LISTBOX));
        assertEquals(4, fields.indexOf(RADIO_GROUP));
        assertEquals(5, fields.indexOf(CHECKBOX_GROUP));
        assertEquals(6, fields.indexOf(DATE));
        assertEquals(7, fields.size());

    }


    @Test
    public void testBlockContentOrder_TEXT_FIELDS() {
        final List<ElementID> blockItems = Arrays.asList(TEXT_FIELDS.childs());
        assertEquals(0, blockItems.indexOf(TEXTBOX));
        assertEquals(1, blockItems.indexOf(SPECIAL_TEXT_FIELDS));
        assertEquals(2, blockItems.size());

    }

    @Test
    public void testBlockContentOrder_OTHER_FIELDS() {
        final List<ElementID> blockItems = Arrays.asList(OTHER_FIELDS.childs());
        assertEquals(0, blockItems.indexOf(LISTBOX));
        assertEquals(1, blockItems.indexOf(RADIO_GROUP));
        assertEquals(2, blockItems.indexOf(CHECKBOX_GROUP));
        assertEquals(3, blockItems.indexOf(DATE));
        assertEquals(4, blockItems.size());

    }


    @Test
    public void testGroupContentOrder_SPECIAL_TEXT_FIELDS() {
        final List<ElementID> fields = Arrays.asList(SPECIAL_TEXT_FIELDS.childs());
        assertEquals(0, fields.indexOf(PASSWORD));
        assertEquals(1, fields.indexOf(NUMERIC));
        assertEquals(2, fields.size());

    }


}