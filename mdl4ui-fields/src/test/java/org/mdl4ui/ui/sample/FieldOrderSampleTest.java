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

@Generated(value = "org.mdl4ui.maven.GenerateDepsTestMojo", date = "14/02/13 10:44")
public final class FieldOrderSampleTest {

    @Test
    public void testScreenBlocks_SCR_WIDGETS_SHOWCASE() {
        final List<BlockID> blocks = SCR_WIDGETS_SHOWCASE.blocks();
		assertEquals(13, SCR_WIDGETS_SHOWCASE.fields().size());
        assertTrue(blocks.contains(INFORMATIONS));
        assertTrue(blocks.contains(ACCOUNT));

    }


    @Test
    public void testScreenBlocksOrder_SCR_WIDGETS_SHOWCASE() {
        final List<BlockID> blocks = SCR_WIDGETS_SHOWCASE.blocks();
        assertEquals(0, blocks.indexOf(INFORMATIONS));
        assertEquals(1, blocks.indexOf(ACCOUNT));
        assertEquals(2, blocks.size());

    }


    @Test
    public void testScreenFieldsOrder_SCR_WIDGETS_SHOWCASE() {
        final List<FieldID> fields = SCR_WIDGETS_SHOWCASE.fields();
        assertEquals(0, fields.indexOf(FIRST_NAME));
        assertEquals(1, fields.indexOf(LAST_NAME));
        assertEquals(2, fields.indexOf(EMAIL));
        assertEquals(3, fields.indexOf(BIRTHDATE));
        assertEquals(4, fields.indexOf(LANGUAGE));
        assertEquals(5, fields.indexOf(TIMEZONE));
        assertEquals(6, fields.indexOf(EMAIL_ACCEPTED));
        assertEquals(7, fields.indexOf(EMAILS_PREFERENCES));
        assertEquals(8, fields.indexOf(MAX_WEEKLY_EMAILS));
        assertEquals(8, fields.indexOf(MAX_WEEKLY_EMAILS));
        assertEquals(10, fields.indexOf(LOGIN));
        assertEquals(11, fields.indexOf(PASSWORD));
        assertEquals(12, fields.indexOf(PASSWORD_CONFIRMATION));
        assertEquals(13, fields.size());

    }


    @Test
    public void testBlockContentOrder_INFORMATIONS() {
        final List<ElementID> blockItems = Arrays.asList(INFORMATIONS.childs());
        assertEquals(0, blockItems.indexOf(FIRST_NAME));
        assertEquals(1, blockItems.indexOf(LAST_NAME));
        assertEquals(2, blockItems.indexOf(EMAIL));
        assertEquals(3, blockItems.indexOf(BIRTHDATE));
        assertEquals(4, blockItems.indexOf(LANGUAGE));
        assertEquals(5, blockItems.indexOf(TIMEZONE));
        assertEquals(6, blockItems.indexOf(EMAIL_ACCEPTED));
        assertEquals(7, blockItems.indexOf(EMAIL_GROUP));
        assertEquals(8, blockItems.indexOf(MAX_WEEKLY_EMAILS));
        assertEquals(9, blockItems.size());

    }

    @Test
    public void testBlockContentOrder_ACCOUNT() {
        final List<ElementID> blockItems = Arrays.asList(ACCOUNT.childs());
        assertEquals(0, blockItems.indexOf(LOGIN));
        assertEquals(1, blockItems.indexOf(PASSWORD));
        assertEquals(2, blockItems.indexOf(PASSWORD_CONFIRMATION));
        assertEquals(3, blockItems.size());

    }


    @Test
    public void testGroupContentOrder_EMAIL_GROUP() {
        final List<ElementID> fields = Arrays.asList(EMAIL_GROUP.childs());
        assertEquals(0, fields.indexOf(EMAILS_PREFERENCES));
        assertEquals(1, fields.indexOf(MAX_WEEKLY_EMAILS));
        assertEquals(2, fields.size());

    }


}