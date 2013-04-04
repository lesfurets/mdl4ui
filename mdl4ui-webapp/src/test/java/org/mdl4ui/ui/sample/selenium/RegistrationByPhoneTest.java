package org.mdl4ui.ui.sample.selenium;

import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.StringContains.containsString;

import org.joda.time.DateMidnight;
import org.junit.Test;
import org.mdl4ui.ui.core.selenium.SeleniumTest;
import org.mdl4ui.ui.sample.EScenarioSample;
import org.mdl4ui.ui.sample.selenium.screen.RegistrationByPhoneScreen;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationByPhoneTest extends SeleniumTest {

    @FindBy(id = "registration_done")
    private WebElement registrationDone;

    public RegistrationByPhoneTest() {
        super(EScenarioSample.SCENARIO_PHONE);
    }

    @Test
    public void testRegistration() {
        RegistrationByPhoneScreen registrationScreen = new RegistrationByPhoneScreen(getDriver());
        registrationScreen.assertDisplayed();

        registrationScreen.getPersonalInformations()//
                        .assertDisplayed()//
                        .setFirstName("John")//
                        .setLastName("Doe")//
                        .setBirthdate(new DateMidnight(1980, 1, 1))//
                        .setEmail("john@doe.com")//
                        .submit();

        registrationScreen.getPhoneSettings()//
                        .assertDisplayed()//
                        .setLanguage("English")//
                        .setPhoneNumber("0102030409")//
                        .submit();

        registrationScreen.getAccount()//
                        .assertDisplayed()//
                        .setLogin("john")//
                        .setPassword("doe")//
                        .setPasswordConfirmation("doe")//
                        .submit();

        assertThat(registrationDone.getText(), containsString("Registration done!"));
    }
}
