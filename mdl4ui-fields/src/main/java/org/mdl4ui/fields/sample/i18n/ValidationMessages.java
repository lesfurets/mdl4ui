package org.mdl4ui.fields.sample.i18n;

import org.ez18n.Message;
import org.ez18n.MessageBundle;

@MessageBundle
public interface ValidationMessages {

    @Message("Please specify your birth date")
    String please_specify_your_birth_date();

    @Message("Do your want receive email from on service?")
    String do_your_want_receive_email_from_on_service();

    @Message("Please specify your email")
    String please_specify_your_email();

    @Message("Please specify your phone number")
    String please_specify_your_phone_number();

    @Message("Please specify your first name")
    String please_specify_your_first_name();

    @Message("Please specify your last name")
    String please_specify_your_last_name();

    @Message("Please specify your login")
    String please_specify_your_login();

    @Message("Please specify your password")
    String please_specify_your_password();

    @Message("Passwords does not match")
    String passwords_does_not_match();

    @Message("Please specify your timezone")
    String please_specify_your_timezone();

    @Message("Please specify your language")
    String please_specify_your_language();

    @Message("Please specify the email frequency")
    String please_specify_the_email_frequency();

    @Message("Please specify the kind of email you wish to receive")
    String please_specify_the_kind_of_email_you_wish_to_receive();

}
