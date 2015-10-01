package org.mdl4ui.fields.sample.i18n;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Generated;

@Generated(value = "org.ez18n.apt.processor.TestDesktopBundleProcessor",  date = "10/1/15 6:13 PM")
public class ValidationMessagesDesktopBundleTest {

	private ValidationMessages bundle;

	@org.junit.Before
	public void setUp() {
		bundle = ValidationMessagesDesktopBundle.MSG_VM;
	}


    @org.junit.Test
    public void please_specify_your_birth_date() {
    	assertNotNull(bundle.please_specify_your_birth_date());
    }

    @org.junit.Test
    public void please_specify_a_valid_date() {
    	assertNotNull(bundle.please_specify_a_valid_date(null,null));
    }

    @org.junit.Test
    public void do_your_want_receive_email_from_on_service() {
    	assertNotNull(bundle.do_your_want_receive_email_from_on_service());
    }

    @org.junit.Test
    public void please_specify_your_email() {
    	assertNotNull(bundle.please_specify_your_email());
    }

    @org.junit.Test
    public void please_specify_a_valid_email() {
    	assertNotNull(bundle.please_specify_a_valid_email());
    }

    @org.junit.Test
    public void please_specify_your_first_name() {
    	assertNotNull(bundle.please_specify_your_first_name());
    }

    @org.junit.Test
    public void please_specify_your_last_name() {
    	assertNotNull(bundle.please_specify_your_last_name());
    }

    @org.junit.Test
    public void please_specify_your_login() {
    	assertNotNull(bundle.please_specify_your_login());
    }

    @org.junit.Test
    public void please_specify_your_password() {
    	assertNotNull(bundle.please_specify_your_password());
    }

    @org.junit.Test
    public void passwords_does_not_match() {
    	assertNotNull(bundle.passwords_does_not_match());
    }

    @org.junit.Test
    public void please_specify_your_timezone() {
    	assertNotNull(bundle.please_specify_your_timezone());
    }

    @org.junit.Test
    public void please_specify_your_language() {
    	assertNotNull(bundle.please_specify_your_language());
    }

    @org.junit.Test
    public void please_specify_the_email_frequency() {
    	assertNotNull(bundle.please_specify_the_email_frequency());
    }

    @org.junit.Test
    public void please_specify_the_kind_of_email_you_wish_to_receive() {
    	assertNotNull(bundle.please_specify_the_kind_of_email_you_wish_to_receive());
    }

    @org.junit.Test
    public void please_specify_a_phone_number() {
    	assertNotNull(bundle.please_specify_a_phone_number());
    }

    @org.junit.Test
    public void please_specify_a_valid_phone_number() {
    	assertNotNull(bundle.please_specify_a_valid_phone_number());
    }


}