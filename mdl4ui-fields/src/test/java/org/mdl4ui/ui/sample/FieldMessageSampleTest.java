package org.mdl4ui.ui.sample;

import static org.mdl4ui.ui.sample.EFieldSample.*;
import static org.mdl4ui.ui.sample.EBlockSample.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.annotation.Generated;

import org.junit.Test;

import org.mdl4ui.fields.sample.BundleFieldFactory;

@Generated(value = "org.mdl4ui.maven.GenerateDepsTestMojo", date = "16/02/13 14:43")
public final class FieldMessageSampleTest {


    @Test
    public void testBlockText_PERSONAL_INFORMATIONS() {
        final String messageLabel = BundleFieldFactory.INSTANCE.getLabel(PERSONAL_INFORMATIONS);
        final String messageHelp = BundleFieldFactory.INSTANCE.getHelp(PERSONAL_INFORMATIONS);
        final String messagePlaceHolder = BundleFieldFactory.INSTANCE.getPlaceHolder(PERSONAL_INFORMATIONS);
        assertEquals("My Informations", messageLabel);
        assertNull(messageHelp);
        assertNull(messagePlaceHolder);

    }

    @Test
    public void testBlockText_ACCOUNT() {
        final String messageLabel = BundleFieldFactory.INSTANCE.getLabel(ACCOUNT);
        final String messageHelp = BundleFieldFactory.INSTANCE.getHelp(ACCOUNT);
        final String messagePlaceHolder = BundleFieldFactory.INSTANCE.getPlaceHolder(ACCOUNT);
        assertEquals("My Account", messageLabel);
        assertNull(messageHelp);
        assertNull(messagePlaceHolder);

    }


    @Test
    public void testFieldText_FIRST_NAME() {
        final String messageLabel = BundleFieldFactory.INSTANCE.getLabel(FIRST_NAME);
        final String messageHelp = BundleFieldFactory.INSTANCE.getHelp(FIRST_NAME);
        final String messagePlaceHolder = BundleFieldFactory.INSTANCE.getPlaceHolder(FIRST_NAME);
        assertEquals("First Name", messageLabel);
        assertNull(messageHelp);
        assertEquals("John", messagePlaceHolder);

    }

    @Test
    public void testFieldText_LAST_NAME() {
        final String messageLabel = BundleFieldFactory.INSTANCE.getLabel(LAST_NAME);
        final String messageHelp = BundleFieldFactory.INSTANCE.getHelp(LAST_NAME);
        final String messagePlaceHolder = BundleFieldFactory.INSTANCE.getPlaceHolder(LAST_NAME);
        assertEquals("Last Name", messageLabel);
        assertNull(messageHelp);
        assertEquals("Doe", messagePlaceHolder);

    }

    @Test
    public void testFieldText_EMAIL() {
        final String messageLabel = BundleFieldFactory.INSTANCE.getLabel(EMAIL);
        final String messageHelp = BundleFieldFactory.INSTANCE.getHelp(EMAIL);
        final String messagePlaceHolder = BundleFieldFactory.INSTANCE.getPlaceHolder(EMAIL);
        assertEquals("Email", messageLabel);
        assertEquals("You will receive a email to confirm your inscription", messageHelp);
        assertEquals("john@doe.com", messagePlaceHolder);

    }

    @Test
    public void testFieldText_BIRTHDATE() {
        final String messageLabel = BundleFieldFactory.INSTANCE.getLabel(BIRTHDATE);
        final String messageHelp = BundleFieldFactory.INSTANCE.getHelp(BIRTHDATE);
        final String messagePlaceHolder = BundleFieldFactory.INSTANCE.getPlaceHolder(BIRTHDATE);
        assertEquals("Birth date", messageLabel);
        assertNull(messageHelp);
        assertEquals("dd/mm/yyyy", messagePlaceHolder);

    }

    @Test
    public void testFieldText_LOGIN() {
        final String messageLabel = BundleFieldFactory.INSTANCE.getLabel(LOGIN);
        final String messageHelp = BundleFieldFactory.INSTANCE.getHelp(LOGIN);
        final String messagePlaceHolder = BundleFieldFactory.INSTANCE.getPlaceHolder(LOGIN);
        assertEquals("Login", messageLabel);
        assertNull(messageHelp);
        assertNull(messagePlaceHolder);

    }

    @Test
    public void testFieldText_PASSWORD() {
        final String messageLabel = BundleFieldFactory.INSTANCE.getLabel(PASSWORD);
        final String messageHelp = BundleFieldFactory.INSTANCE.getHelp(PASSWORD);
        final String messagePlaceHolder = BundleFieldFactory.INSTANCE.getPlaceHolder(PASSWORD);
        assertEquals("Password", messageLabel);
        assertNull(messageHelp);
        assertNull(messagePlaceHolder);

    }

    @Test
    public void testFieldText_PASSWORD_CONFIRMATION() {
        final String messageLabel = BundleFieldFactory.INSTANCE.getLabel(PASSWORD_CONFIRMATION);
        final String messageHelp = BundleFieldFactory.INSTANCE.getHelp(PASSWORD_CONFIRMATION);
        final String messagePlaceHolder = BundleFieldFactory.INSTANCE.getPlaceHolder(PASSWORD_CONFIRMATION);
        assertEquals("Confirm your password", messageLabel);
        assertNull(messageHelp);
        assertNull(messagePlaceHolder);

    }


}