package org.mdl4ui.fields.sample.i18n;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Generated;

@Generated(value = "org.ez18n.apt.processor.TestDesktopBundleProcessor",  date = "10/1/15 6:13 PM")
public class FieldMessagesDesktopBundleTest {

	private FieldMessages bundle;

	@org.junit.Before
	public void setUp() {
		bundle = FieldMessagesDesktopBundle.MSG_FM;
	}


    @org.junit.Test
    public void firstName() {
    	assertNotNull(bundle.firstName());
    }

    @org.junit.Test
    public void john() {
    	assertNotNull(bundle.john());
    }

    @org.junit.Test
    public void lastName() {
    	assertNotNull(bundle.lastName());
    }

    @org.junit.Test
    public void doe() {
    	assertNotNull(bundle.doe());
    }

    @org.junit.Test
    public void email() {
    	assertNotNull(bundle.email());
    }

    @org.junit.Test
    public void john_doe_com() {
    	assertNotNull(bundle.john_doe_com());
    }

    @org.junit.Test
    public void confirmationEmail() {
    	assertNotNull(bundle.confirmationEmail());
    }

    @org.junit.Test
    public void birthDate() {
    	assertNotNull(bundle.birthDate());
    }

    @org.junit.Test
    public void ddmmyyyy() {
    	assertNotNull(bundle.ddmmyyyy());
    }

    @org.junit.Test
    public void language() {
    	assertNotNull(bundle.language());
    }

    @org.junit.Test
    public void timezone() {
    	assertNotNull(bundle.timezone());
    }

    @org.junit.Test
    public void emailAccepted() {
    	assertNotNull(bundle.emailAccepted());
    }

    @org.junit.Test
    public void emailPreference() {
    	assertNotNull(bundle.emailPreference());
    }

    @org.junit.Test
    public void max_mail_per_week() {
    	assertNotNull(bundle.max_mail_per_week());
    }

    @org.junit.Test
    public void login() {
    	assertNotNull(bundle.login());
    }

    @org.junit.Test
    public void password() {
    	assertNotNull(bundle.password());
    }

    @org.junit.Test
    public void confirmPassword() {
    	assertNotNull(bundle.confirmPassword());
    }

    @org.junit.Test
    public void privateMessage() {
    	assertNotNull(bundle.privateMessage());
    }

    @org.junit.Test
    public void administratorMessage() {
    	assertNotNull(bundle.administratorMessage());
    }

    @org.junit.Test
    public void newsletter() {
    	assertNotNull(bundle.newsletter());
    }

    @org.junit.Test
    public void english() {
    	assertNotNull(bundle.english());
    }

    @org.junit.Test
    public void francais() {
    	assertNotNull(bundle.francais());
    }

    @org.junit.Test
    public void yes() {
    	assertNotNull(bundle.yes());
    }

    @org.junit.Test
    public void no() {
    	assertNotNull(bundle.no());
    }

    @org.junit.Test
    public void phoneNumber() {
    	assertNotNull(bundle.phoneNumber());
    }

    @org.junit.Test
    public void phonePlaceholder() {
    	assertNotNull(bundle.phonePlaceholder());
    }


}