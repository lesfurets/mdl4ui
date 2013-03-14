package org.mdl4ui.fields.sample.i18n;

import org.ez18n.Message;
import org.ez18n.MessageBundle;
import org.mdl4ui.fields.sample.InjectSampleLabel;
import org.mdl4ui.fields.sample.OnElement;
import org.mdl4ui.ui.sample.EBlockSample;

@MessageBundle
public interface BlockMessages {

    @InjectSampleLabel(@OnElement(blocks = EBlockSample.PERSONAL_INFORMATIONS))
    @Message("My Informations")
    String informations();

    @InjectSampleLabel(@OnElement(blocks = EBlockSample.MAIL_SETTINGS))
    @Message("My Settings & EMail")
    String emailSettings();

    @InjectSampleLabel(@OnElement(blocks = EBlockSample.PHONE_SETTINGS))
    @Message("My Settings & Phone Number")
    String phoneSettings();

    @InjectSampleLabel(@OnElement(blocks = EBlockSample.ACCOUNT))
    @Message("My Account")
    String otherFields();
}
