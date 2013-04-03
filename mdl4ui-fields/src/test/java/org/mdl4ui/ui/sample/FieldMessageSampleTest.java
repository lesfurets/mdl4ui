package org.mdl4ui.ui.sample;

import static org.mdl4ui.ui.sample.EFieldSample.*;
import static org.mdl4ui.ui.sample.EBlockSample.*;
import static org.mdl4ui.ui.sample.EGroupSample.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.annotation.Generated;

import org.junit.Test;

import org.mdl4ui.fields.sample.BundleFieldFactory;

@Generated(value = "org.mdl4ui.maven.GenerateDepsTestMojo")
public final class FieldMessageSampleTest {

    @Test
    public void testGroupText_EMAIL_GROUP() {
        final String messageLabel = BundleFieldFactory.INSTANCE.getLabel(EMAIL_GROUP);
        final String messageHelp = BundleFieldFactory.INSTANCE.getHelp(EMAIL_GROUP);
        final String messagePlaceHolder = BundleFieldFactory.INSTANCE.getPlaceHolder(EMAIL_GROUP);
        assertNull(messageLabel);
        assertNull(messageHelp);
        assertNull(messagePlaceHolder);

    }


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
    public void testBlockText_MAIL_SETTINGS() {
        final String messageLabel = BundleFieldFactory.INSTANCE.getLabel(MAIL_SETTINGS);
        final String messageHelp = BundleFieldFactory.INSTANCE.getHelp(MAIL_SETTINGS);
        final String messagePlaceHolder = BundleFieldFactory.INSTANCE.getPlaceHolder(MAIL_SETTINGS);
        assertEquals("My Settings and Email", messageLabel);
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
    public void testFieldText_LANGUAGE() {
        final String messageLabel = BundleFieldFactory.INSTANCE.getLabel(LANGUAGE);
        final String messageHelp = BundleFieldFactory.INSTANCE.getHelp(LANGUAGE);
        final String messagePlaceHolder = BundleFieldFactory.INSTANCE.getPlaceHolder(LANGUAGE);
        assertEquals("Language", messageLabel);
        assertNull(messageHelp);
        assertNull(messagePlaceHolder);

    }

    @Test
    public void testFieldText_EMAIL_ACCEPTED() {
        final String messageLabel = BundleFieldFactory.INSTANCE.getLabel(EMAIL_ACCEPTED);
        final String messageHelp = BundleFieldFactory.INSTANCE.getHelp(EMAIL_ACCEPTED);
        final String messagePlaceHolder = BundleFieldFactory.INSTANCE.getPlaceHolder(EMAIL_ACCEPTED);
        assertEquals("I accept to receive email", messageLabel);
        assertNull(messageHelp);
        assertNull(messagePlaceHolder);

    }

    @Test
    public void testFieldText_EMAILS_PREFERENCES() {
        final String messageLabel = BundleFieldFactory.INSTANCE.getLabel(EMAILS_PREFERENCES);
        final String messageHelp = BundleFieldFactory.INSTANCE.getHelp(EMAILS_PREFERENCES);
        final String messagePlaceHolder = BundleFieldFactory.INSTANCE.getPlaceHolder(EMAILS_PREFERENCES);
        assertEquals("Email preference", messageLabel);
        assertNull(messageHelp);
        assertNull(messagePlaceHolder);

    }

    @Test
    public void testFieldText_MAX_WEEKLY_EMAILS() {
        final String messageLabel = BundleFieldFactory.INSTANCE.getLabel(MAX_WEEKLY_EMAILS);
        final String messageHelp = BundleFieldFactory.INSTANCE.getHelp(MAX_WEEKLY_EMAILS);
        final String messagePlaceHolder = BundleFieldFactory.INSTANCE.getPlaceHolder(MAX_WEEKLY_EMAILS);
        assertEquals("Emails limit / week", messageLabel);
        assertNull(messageHelp);
        assertNull(messagePlaceHolder);

    }

    @Test
    public void testFieldText_TIMEZONE() {
        final String messageLabel = BundleFieldFactory.INSTANCE.getLabel(TIMEZONE);
        final String messageHelp = BundleFieldFactory.INSTANCE.getHelp(TIMEZONE);
        final String messagePlaceHolder = BundleFieldFactory.INSTANCE.getPlaceHolder(TIMEZONE);
        assertEquals("Timezone", messageLabel);
        assertNull(messageHelp);
        assertNull(messagePlaceHolder);

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