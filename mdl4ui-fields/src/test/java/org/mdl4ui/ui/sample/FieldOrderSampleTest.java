package org.mdl4ui.ui.sample;

import static org.mdl4ui.ui.sample.EScreenSample.*;
import static org.mdl4ui.ui.sample.EFieldSample.*;
import static org.mdl4ui.ui.sample.EGroupSample.*;
import static org.mdl4ui.ui.sample.EBlockSample.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Generated;

import org.junit.Test;

import org.mdl4ui.base.model.*;

@Generated(value = "org.mdl4ui.maven.GenerateDepsTestMojo")
public final class FieldOrderSampleTest {

    @Test
    public void testScreenBlocks_SCR_DONE() {
        final List<BlockID> blocks = SCR_DONE.blocks();
		assertEquals(0, SCR_DONE.fields().size());
        assertTrue(blocks.isEmpty());
    }

    @Test
    public void testScreenBlocks_SCR_REGISTRATION_BY_MAIL() {
        final List<BlockID> blocks = SCR_REGISTRATION_BY_MAIL.blocks();
		assertEquals(12, SCR_REGISTRATION_BY_MAIL.fields().size());
        assertTrue(blocks.contains(PERSONAL_INFORMATIONS));
        assertTrue(blocks.contains(MAIL_SETTINGS));
        assertTrue(blocks.contains(ACCOUNT));

    }

    @Test
    public void testScreenBlocks_SCR_REGISTRATION_BY_PHONE() {
        final List<BlockID> blocks = SCR_REGISTRATION_BY_PHONE.blocks();
		assertEquals(10, SCR_REGISTRATION_BY_PHONE.fields().size());
        assertTrue(blocks.contains(PERSONAL_INFORMATIONS));
        assertTrue(blocks.contains(PHONE_SETTINGS));
        assertTrue(blocks.contains(ACCOUNT));

    }


    @Test
    public void testScreenBlocksOrder_SCR_DONE() {
        final List<BlockID> blocks = SCR_DONE.blocks();
        assertEquals(0, blocks.size());

    }

    @Test
    public void testScreenBlocksOrder_SCR_REGISTRATION_BY_MAIL() {
        final List<BlockID> blocks = SCR_REGISTRATION_BY_MAIL.blocks();
        assertEquals(0, blocks.indexOf(PERSONAL_INFORMATIONS));
        assertEquals(1, blocks.indexOf(MAIL_SETTINGS));
        assertEquals(2, blocks.indexOf(ACCOUNT));
        assertEquals(3, blocks.size());

    }

    @Test
    public void testScreenBlocksOrder_SCR_REGISTRATION_BY_PHONE() {
        final List<BlockID> blocks = SCR_REGISTRATION_BY_PHONE.blocks();
        assertEquals(0, blocks.indexOf(PERSONAL_INFORMATIONS));
        assertEquals(1, blocks.indexOf(PHONE_SETTINGS));
        assertEquals(2, blocks.indexOf(ACCOUNT));
        assertEquals(3, blocks.size());

    }


    @Test
    public void testScreenFieldsOrder_SCR_DONE() {
        final List<FieldID> fields = SCR_DONE.fields();
        assertEquals(0, fields.size());

    }

    @Test
    public void testScreenFieldsOrder_SCR_REGISTRATION_BY_MAIL() {
        final List<FieldID> fields = SCR_REGISTRATION_BY_MAIL.fields();
        assertEquals(0, fields.indexOf(FIRST_NAME));
        assertEquals(1, fields.indexOf(LAST_NAME));
        assertEquals(2, fields.indexOf(EMAIL));
        assertEquals(3, fields.indexOf(BIRTHDATE));
        assertEquals(4, fields.indexOf(LANGUAGE));
        assertEquals(5, fields.indexOf(EMAIL_ACCEPTED));
        assertEquals(6, fields.indexOf(EMAILS_PREFERENCES));
        assertEquals(7, fields.indexOf(MAX_WEEKLY_EMAILS));
        assertEquals(8, fields.indexOf(TIMEZONE));
        assertEquals(9, fields.indexOf(LOGIN));
        assertEquals(10, fields.indexOf(PASSWORD));
        assertEquals(11, fields.indexOf(PASSWORD_CONFIRMATION));
        assertEquals(12, fields.size());

    }

    @Test
    public void testScreenFieldsOrder_SCR_REGISTRATION_BY_PHONE() {
        final List<FieldID> fields = SCR_REGISTRATION_BY_PHONE.fields();
        assertEquals(0, fields.indexOf(FIRST_NAME));
        assertEquals(1, fields.indexOf(LAST_NAME));
        assertEquals(2, fields.indexOf(EMAIL));
        assertEquals(3, fields.indexOf(BIRTHDATE));
        assertEquals(4, fields.indexOf(LANGUAGE));
        assertEquals(5, fields.indexOf(PHONE_NUMBER));
        assertEquals(6, fields.indexOf(TIMEZONE));
        assertEquals(7, fields.indexOf(LOGIN));
        assertEquals(8, fields.indexOf(PASSWORD));
        assertEquals(9, fields.indexOf(PASSWORD_CONFIRMATION));
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
    public void testBlockContentOrder_MAIL_SETTINGS() {
        final List<ElementID> blockItems = MAIL_SETTINGS.childs();
        assertEquals(0, blockItems.indexOf(LANGUAGE));
        assertEquals(1, blockItems.indexOf(EMAIL_ACCEPTED));
        assertEquals(2, blockItems.indexOf(EMAIL_GROUP));
        assertEquals(3, blockItems.indexOf(TIMEZONE));
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

    @Test
    public void testBlockContentOrder_PHONE_SETTINGS() {
        final List<ElementID> blockItems = PHONE_SETTINGS.childs();
        assertEquals(0, blockItems.indexOf(LANGUAGE));
        assertEquals(1, blockItems.indexOf(PHONE_NUMBER));
        assertEquals(2, blockItems.indexOf(TIMEZONE));
        assertEquals(3, blockItems.size());

    }


    @Test
    public void testGroupContentOrder_EMAIL_GROUP() {
        final List<ElementID> fields = EMAIL_GROUP.childs();
        assertEquals(0, fields.indexOf(EMAILS_PREFERENCES));
        assertEquals(1, fields.indexOf(MAX_WEEKLY_EMAILS));
        assertEquals(2, fields.size());

    }


}