package org.mdl4ui.fields.sample.i18n;

import org.ez18n.Message;
import org.ez18n.MessageBundle;
import org.mdl4ui.fields.sample.InjectSampleHelp;
import org.mdl4ui.fields.sample.InjectSampleLabel;
import org.mdl4ui.fields.sample.OnElement;
import org.mdl4ui.ui.sample.EFieldSample;

@MessageBundle
public interface FieldMessages {

    @InjectSampleLabel(@OnElement(fields = EFieldSample.TEXTBOX))
    @Message("Texbox")
    String texbox();

    @InjectSampleLabel(@OnElement(fields = EFieldSample.PASSWORD))
    @Message("Password")
    String password();

    @InjectSampleHelp(@OnElement(fields = EFieldSample.PASSWORD))
    @Message("Field content is hidden")
    String passwordHelp();

    @InjectSampleLabel(@OnElement(fields = EFieldSample.NUMERIC))
    @Message("Numeric Textbox")
    String numeric();

    @InjectSampleLabel(@OnElement(fields = EFieldSample.LISTBOX))
    @Message("Listbox")
    String listbox();

    @InjectSampleLabel(@OnElement(fields = EFieldSample.RADIO_GROUP))
    @Message("Radiobox Group")
    String radioboxGroup();

    @InjectSampleLabel(@OnElement(fields = EFieldSample.CHECKBOX_GROUP))
    @Message("Checkbox Group")
    String checkBoxGroup();

    @InjectSampleLabel(@OnElement(fields = EFieldSample.DATE))
    @Message("Date textbox")
    String date();

    @InjectSampleHelp(@OnElement(fields = EFieldSample.DATE))
    @Message("A date picker is available")
    String dateHelp();
}
