package org.mdl4ui.gwt.sample.client.i18n;

import org.ez18n.Message;
import org.ez18n.MessageBundle;

@MessageBundle
public interface SampleMessages {

    @Message("Registration done!")
    String registration_done();

    @Message("Me")
    String me();

    @Message("My Account")
    String my_account();
}
