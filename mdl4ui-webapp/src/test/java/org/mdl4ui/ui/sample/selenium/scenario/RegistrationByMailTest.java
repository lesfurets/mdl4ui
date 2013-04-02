package org.mdl4ui.ui.sample.selenium.scenario;

import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.StringContains.containsString;

import org.joda.time.DateMidnight;
import org.junit.Test;
import org.mdl4ui.ui.core.selenium.SeleniumTest;
import org.mdl4ui.ui.sample.selenium.screen.RegistrationByMailScreen;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationByMailTest extends SeleniumTest {

    @FindBy(id = "registration_done")
    private WebElement registrationDone;

    public RegistrationByMailTest() {
        PageFactory.initElements(getDriver(), this);
    }

    @Test
    public void testRegistration() {
        RegistrationByMailScreen registrationScreen = new RegistrationByMailScreen(getDriver());

        registrationScreen.assertDisplayed();

        registrationScreen.getPersonalInformations()//
                        .assertDisplayed()//
                        .setFirstName("John")//
                        .setLastName("Doe")//
                        .setBirthdate(new DateMidnight(1980, 1, 1))//
                        .setEmail("john@doe.com")//
                        .submit();

        registrationScreen.getMailSettings()//
                        .assertDisplayed()//
                        .setLanguage("English")//
                        .setEmailAccepted("Yes")//
                        .setEmailsPreferences("Newsletter")//
                        .submit();

        registrationScreen.getAccount()//
                        .assertDisplayed()//
                        .setLogin("john")//
                        .setPassword("doe")//
                        .setPasswordConfirmation("doe")//
                        .submit();

        assertThat(registrationDone.getText(), containsString("Registration done successfully!"));
    }
}
