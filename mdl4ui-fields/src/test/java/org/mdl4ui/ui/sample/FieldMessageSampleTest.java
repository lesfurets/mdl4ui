package org.mdl4ui.ui.sample;

import static org.mdl4ui.ui.sample.EFieldSample.*;
import static org.mdl4ui.ui.sample.EBlockSample.*;
import static org.mdl4ui.ui.sample.EGroupSample.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.annotation.Generated;

import org.junit.Test;

import org.mdl4ui.fields.sample.BundleFieldFactory;

@Generated(value = "org.mdl4ui.maven.GenerateDepsTestMojo", date = "13/02/13 18:11")
public final class FieldMessageSampleTest {

    @Test
    public void testGroupText_SPECIAL_TEXT_FIELDS() {
        final String messageLabel = BundleFieldFactory.INSTANCE.getLabel(SPECIAL_TEXT_FIELDS);
        final String messageHelp = BundleFieldFactory.INSTANCE.getHelp(SPECIAL_TEXT_FIELDS);
        final String messagePlaceHolder = BundleFieldFactory.INSTANCE.getPlaceHolder(SPECIAL_TEXT_FIELDS);
        assertNull(messageLabel);
        assertNull(messageHelp);
        assertNull(messagePlaceHolder);

    }


    @Test
    public void testBlockText_TEXT_FIELDS() {
        final String messageLabel = BundleFieldFactory.INSTANCE.getLabel(TEXT_FIELDS);
        final String messageHelp = BundleFieldFactory.INSTANCE.getHelp(TEXT_FIELDS);
        final String messagePlaceHolder = BundleFieldFactory.INSTANCE.getPlaceHolder(TEXT_FIELDS);
        assertEquals("Textbox-based fields", messageLabel);
        assertNull(messageHelp);
        assertNull(messagePlaceHolder);

    }

    @Test
    public void testBlockText_OTHER_FIELDS() {
        final String messageLabel = BundleFieldFactory.INSTANCE.getLabel(OTHER_FIELDS);
        final String messageHelp = BundleFieldFactory.INSTANCE.getHelp(OTHER_FIELDS);
        final String messagePlaceHolder = BundleFieldFactory.INSTANCE.getPlaceHolder(OTHER_FIELDS);
        assertEquals("other fields", messageLabel);
        assertNull(messageHelp);
        assertNull(messagePlaceHolder);

    }


    @Test
    public void testFieldText_TEXTBOX() {
        final String messageLabel = BundleFieldFactory.INSTANCE.getLabel(TEXTBOX);
        final String messageHelp = BundleFieldFactory.INSTANCE.getHelp(TEXTBOX);
        final String messagePlaceHolder = BundleFieldFactory.INSTANCE.getPlaceHolder(TEXTBOX);
        assertEquals("Texbox", messageLabel);
        assertNull(messageHelp);
        assertEquals("Type your name", messagePlaceHolder);

    }

    @Test
    public void testFieldText_PASSWORD() {
        final String messageLabel = BundleFieldFactory.INSTANCE.getLabel(PASSWORD);
        final String messageHelp = BundleFieldFactory.INSTANCE.getHelp(PASSWORD);
        final String messagePlaceHolder = BundleFieldFactory.INSTANCE.getPlaceHolder(PASSWORD);
        assertEquals("Password", messageLabel);
        assertEquals("Field content is hidden", messageHelp);
        assertEquals("Type your password", messagePlaceHolder);

    }

    @Test
    public void testFieldText_NUMERIC() {
        final String messageLabel = BundleFieldFactory.INSTANCE.getLabel(NUMERIC);
        final String messageHelp = BundleFieldFactory.INSTANCE.getHelp(NUMERIC);
        final String messagePlaceHolder = BundleFieldFactory.INSTANCE.getPlaceHolder(NUMERIC);
        assertEquals("Numeric Textbox", messageLabel);
        assertNull(messageHelp);
        assertNull(messagePlaceHolder);

    }

    @Test
    public void testFieldText_LISTBOX() {
        final String messageLabel = BundleFieldFactory.INSTANCE.getLabel(LISTBOX);
        final String messageHelp = BundleFieldFactory.INSTANCE.getHelp(LISTBOX);
        final String messagePlaceHolder = BundleFieldFactory.INSTANCE.getPlaceHolder(LISTBOX);
        assertEquals("Listbox", messageLabel);
        assertNull(messageHelp);
        assertNull(messagePlaceHolder);

    }

    @Test
    public void testFieldText_RADIO_GROUP() {
        final String messageLabel = BundleFieldFactory.INSTANCE.getLabel(RADIO_GROUP);
        final String messageHelp = BundleFieldFactory.INSTANCE.getHelp(RADIO_GROUP);
        final String messagePlaceHolder = BundleFieldFactory.INSTANCE.getPlaceHolder(RADIO_GROUP);
        assertEquals("Radiobox Group", messageLabel);
        assertNull(messageHelp);
        assertNull(messagePlaceHolder);

    }

    @Test
    public void testFieldText_CHECKBOX_GROUP() {
        final String messageLabel = BundleFieldFactory.INSTANCE.getLabel(CHECKBOX_GROUP);
        final String messageHelp = BundleFieldFactory.INSTANCE.getHelp(CHECKBOX_GROUP);
        final String messagePlaceHolder = BundleFieldFactory.INSTANCE.getPlaceHolder(CHECKBOX_GROUP);
        assertEquals("Checkbox Group", messageLabel);
        assertNull(messageHelp);
        assertNull(messagePlaceHolder);

    }

    @Test
    public void testFieldText_DATE() {
        final String messageLabel = BundleFieldFactory.INSTANCE.getLabel(DATE);
        final String messageHelp = BundleFieldFactory.INSTANCE.getHelp(DATE);
        final String messagePlaceHolder = BundleFieldFactory.INSTANCE.getPlaceHolder(DATE);
        assertEquals("Date textbox", messageLabel);
        assertEquals("A date picker is available", messageHelp);
        assertNull(messagePlaceHolder);

    }


}