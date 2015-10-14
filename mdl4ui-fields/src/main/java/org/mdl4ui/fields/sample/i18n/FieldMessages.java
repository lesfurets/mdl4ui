package org.mdl4ui.fields.sample.i18n;

import org.ez18n.Message;
import org.ez18n.MessageBundle;
import org.mdl4ui.fields.sample.*;
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

    @InjectSampleLabel(@OnElement(fields = EFieldSample.MAX_EMAILS_PER_WEEK))
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

    @InjectSampleLabel(@OnElement(fields = EFieldSample.FAVORITE_SITE_NAME_1))
    @Message("1st Fav WebSite Name")
    String favoriteWebSiteName1();

    @InjectSampleLabel(@OnElement(fields = EFieldSample.FAVORITE_SITE_NAME_2))
    @Message("2nd Fav WebSite Name")
    String favoriteWebSiteName2();

    @InjectSampleLabel(@OnElement(fields = EFieldSample.FAVORITE_SITE_NAME_3))
    @Message("3rd Fav WebSite Name")
    String favoriteWebSiteName3();

    @InjectSampleLabel(@OnElement(fields = EFieldSample.FAVORITE_SITE_URL_1))
    @Message("1st Fav WebSite Url")
    String favoriteWebSiteUrl1();

    @InjectSampleLabel(@OnElement(fields = EFieldSample.FAVORITE_SITE_URL_2))
    @Message("2nd Fav WebSite Url")
    String favoriteWebSiteUrl2();

    @InjectSampleLabel(@OnElement(fields = EFieldSample.FAVORITE_SITE_URL_3))
    @Message("3rd Fav WebSite Url")
    String favoriteWebSiteUrl3();

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

    @InjectSampleLabel(@OnElement(fields = EFieldSample.PHONE_NUMBER))
    @Message("Phone Number")
    String phoneNumber();

    @InjectSamplePlaceholder(@OnElement(fields = EFieldSample.PHONE_NUMBER))
    @Message("01 02 03 04 06")
    String phonePlaceholder();

    @InjectSamplePlaceholder(@OnElement(fields = EFieldSample.FAVORITE_SITE_NAME_1))
    @Message("Google")
    String favoriteWebSiteName1Placeholder();

    @InjectSamplePlaceholder(@OnElement(fields = EFieldSample.FAVORITE_SITE_URL_1))
    @Message("www.google.com")
    String favoriteWebSiteUrl1Placeholder();

    @InjectSamplePlaceholder(@OnElement(fields = EFieldSample.FAVORITE_SITE_NAME_2))
    @Message("Yahoo")
    String favoriteWebSiteName2Placeholder();

    @InjectSamplePlaceholder(@OnElement(fields = EFieldSample.FAVORITE_SITE_URL_2))
    @Message("www.yahoo.com")
    String favoriteWebSiteUrl2Placeholder();

    @InjectSamplePlaceholder(@OnElement(fields = EFieldSample.FAVORITE_SITE_NAME_3))
    @Message("Bing")
    String favoriteWebSiteName3Placeholder();

    @InjectSamplePlaceholder(@OnElement(fields = EFieldSample.FAVORITE_SITE_URL_3))
    @Message("www.bing.com")
    String favoriteWebSiteUrl3Placeholder();
}
