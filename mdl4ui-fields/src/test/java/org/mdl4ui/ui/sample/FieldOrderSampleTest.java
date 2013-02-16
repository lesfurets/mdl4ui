package org.mdl4ui.ui.sample;

import static org.mdl4ui.ui.sample.EFieldSample.*;
import static org.mdl4ui.ui.sample.EScreenSample.*;
import static org.mdl4ui.ui.sample.EBlockSample.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.junit.Test;

import org.mdl4ui.base.model.*;

@Generated(value = "org.mdl4ui.maven.GenerateDepsTestMojo", date = "16/02/13 14:43")
public final class FieldOrderSampleTest {

    @Test
    public void testScreenBlocks_SCR_WIDGETS_SHOWCASE() {
        final List<BlockID> blocks = SCR_WIDGETS_SHOWCASE.blocks();
		assertEquals(10, SCR_WIDGETS_SHOWCASE.fields().size());
        assertTrue(blocks.contains(PERSONAL_INFORMATIONS));
        assertTrue(blocks.contains(ACCOUNT));
        assertTrue(blocks.contains(ACCOUNT));

    }


    @Test
    public void testScreenBlocksOrder_SCR_WIDGETS_SHOWCASE() {
        final List<BlockID> blocks = SCR_WIDGETS_SHOWCASE.blocks();
        assertEquals(0, blocks.indexOf(PERSONAL_INFORMATIONS));
        assertEquals(1, blocks.indexOf(ACCOUNT));
        assertEquals(1, blocks.indexOf(ACCOUNT));
        assertEquals(3, blocks.size());

    }


    @Test
    public void testScreenFieldsOrder_SCR_WIDGETS_SHOWCASE() {
        final List<FieldID> fields = SCR_WIDGETS_SHOWCASE.fields();
        assertEquals(0, fields.indexOf(FIRST_NAME));
        assertEquals(1, fields.indexOf(LAST_NAME));
        assertEquals(2, fields.indexOf(EMAIL));
        assertEquals(3, fields.indexOf(BIRTHDATE));
        assertEquals(4, fields.indexOf(LOGIN));
        assertEquals(5, fields.indexOf(PASSWORD));
        assertEquals(6, fields.indexOf(PASSWORD_CONFIRMATION));
        assertEquals(4, fields.indexOf(LOGIN));
        assertEquals(5, fields.indexOf(PASSWORD));
        assertEquals(6, fields.indexOf(PASSWORD_CONFIRMATION));
        assertEquals(10, fields.size());

    }


    @Test
    public void testBlockContentOrder_PERSONAL_INFORMATIONS() {
        final List<ElementID> blockItems = PERSONAL_INFORMATIONS.childs();
        assertEquals(0, blockItems.indexOf(FIRST_NAME));
        assertEquals(1, blockItems.indexOf(LAST_NAME));
        assertEquals(2, blockItems.indexOf(EMAIL));
        assertEquals(3, blockItems.indexOf(BIRTHDATE));
        assertEquals(4, blockItems.size());

    }

    @Test
    public void testBlockContentOrder_ACCOUNT() {
        final List<ElementID> blockItems = ACCOUNT.childs();
        assertEquals(0, blockItems.indexOf(LOGIN));
        assertEquals(1, blockItems.indexOf(PASSWORD));
        assertEquals(2, blockItems.indexOf(PASSWORD_CONFIRMATION));
        assertEquals(3, blockItems.size());

    }



}