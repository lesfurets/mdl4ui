package org.mdl4ui.fields.sample.i18n;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Generated;

@Generated(value = "org.ez18n.apt.processor.TestDesktopBundleProcessor",  date = "10/1/15 6:13 PM")
public class BlockMessagesDesktopBundleTest {

	private BlockMessages bundle;

	@org.junit.Before
	public void setUp() {
		bundle = BlockMessagesDesktopBundle.MSG_BM;
	}


    @org.junit.Test
    public void informations() {
    	assertNotNull(bundle.informations());
    }

    @org.junit.Test
    public void emailSettings() {
    	assertNotNull(bundle.emailSettings());
    }

    @org.junit.Test
    public void phoneSettings() {
    	assertNotNull(bundle.phoneSettings());
    }

    @org.junit.Test
    public void otherFields() {
    	assertNotNull(bundle.otherFields());
    }


}