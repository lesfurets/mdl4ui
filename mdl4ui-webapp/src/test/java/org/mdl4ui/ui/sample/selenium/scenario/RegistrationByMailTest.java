package org.mdl4ui.ui.sample.selenium.scenario;

import org.joda.time.DateMidnight;
import org.junit.Test;
import org.mdl4ui.ui.core.selenium.SeleniumTest;
import org.mdl4ui.ui.sample.selenium.screen.RegistrationByMailScreen;
import org.openqa.selenium.By;

public class RegistrationByMailTest extends SeleniumTest {

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

        getDriver().findElement(By.id("registration_done")).getText().contains("Registration done successfully!");
    }
}
