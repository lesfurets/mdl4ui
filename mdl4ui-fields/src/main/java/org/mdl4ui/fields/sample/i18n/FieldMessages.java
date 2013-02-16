package org.mdl4ui.fields.sample.i18n;

import org.ez18n.Message;
import org.ez18n.MessageBundle;
import org.mdl4ui.fields.sample.InjectSampleHelp;
import org.mdl4ui.fields.sample.InjectSampleLabel;
import org.mdl4ui.fields.sample.InjectSamplePlaceholder;
import org.mdl4ui.fields.sample.OnElement;
import org.mdl4ui.ui.sample.EFieldSample;

@MessageBundle
public interface FieldMessages {

    @InjectSampleLabel(@OnElement(fields = EFieldSample.FIRST_NAME))
    @Message("First Name")
    String firstName();

    @InjectSamplePlaceholder(@OnElement(fields = EFieldSample.FIRST_NAME))
    @Message("John")
    String john();

    @InjectSampleLabel(@OnElement(fields = EFieldSample.LAST_NAME))
    @Message("Last Name")
    String lastName();

    @InjectSamplePlaceholder(@OnElement(fields = EFieldSample.LAST_NAME))
    @Message("Doe")
    String doe();

    @InjectSampleLabel(@OnElement(fields = EFieldSample.EMAIL))
    @Message("Email")
    String email();

    @InjectSamplePlaceholder(@OnElement(fields = EFieldSample.EMAIL))
    @Message("john@doe.com")
    String john_doe_com();

    @InjectSampleHelp(@OnElement(fields = EFieldSample.EMAIL))
    @Message("You will receive a email to confirm your inscription")
    String confirmationEmail();

    @InjectSampleLabel(@OnElement(fields = EFieldSample.BIRTHDATE))
    @Message("Birth date")
    String birthDate();

    @InjectSamplePlaceholder(@OnElement(fields = EFieldSample.BIRTHDATE))
    @Message("dd/mm/yyyy")
    String ddmmyyyy();

    @InjectSampleLabel(@OnElement(fields = EFieldSample.LANGUAGE))
    @Message("Language")
    String language();

    @InjectSampleLabel(@OnElement(fields = EFieldSample.TIMEZONE))
    @Message("Timezone")
    String timezone();

    @InjectSampleLabel(@OnElement(fields = EFieldSample.EMAIL_ACCEPTED))
    @Message("I accept to receive email")
    String emailAccepted();

    @InjectSampleLabel(@OnElement(fields = EFieldSample.EMAILS_PREFERENCES))
    @Message("Email preference")
    String emailPreference();

    @InjectSampleLabel(@OnElement(fields = EFieldSample.MAX_WEEKLY_EMAILS))
    @Message("Emails limit / week")
    String max_mail_per_week();

    @InjectSampleLabel(@OnElement(fields = EFieldSample.LOGIN))
    @Message("Login")
    String login();

    @InjectSampleLabel(@OnElement(fields = EFieldSample.PASSWORD))
    @Message("Password")
    String password();

    @InjectSampleLabel(@OnElement(fields = EFieldSample.PASSWORD_CONFIRMATION))
    @Message("Confirm your password")
    String confirmPassword();

    @Message("Private messages")
    String privateMessage();

    @Message("Administrator")
    String administratorMessage();

    @Message("Newsletter")
    String newsletter();

    @Message("English")
    String english();

    @Message("Français")
    String francais();

    @Message("Yes")
    String yes();

    @Message("No")
    String no();
}
