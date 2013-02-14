package org.mdl4ui.fields.sample.i18n;

import org.ez18n.Message;
import org.ez18n.MessageBundle;
import org.mdl4ui.fields.sample.InjectSampleLabel;
import org.mdl4ui.fields.sample.OnElement;
import org.mdl4ui.ui.sample.EBlockSample;

@MessageBundle
public interface BlockMessages {

    @InjectSampleLabel(@OnElement(blocks = EBlockSample.INFORMATIONS))
    @Message("My Profile")
    String textFields();

    @InjectSampleLabel(@OnElement(blocks = EBlockSample.ACCOUNT))
    @Message("My Account")
    String otherFields();
}
