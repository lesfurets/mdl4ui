package org.mdl4ui.ui.core.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class SeleniumTest {

    // FIXME
    public static final String DEFAULT_TEST_URL = "http://127.0.0.1:8080/mdl4ui-webapp/";

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @Before
    public void before() {
        FirefoxProfile profile = new FirefoxProfile();
        // disable unresponsive scripts popup
        profile.setPreference("dom.max_script_run_time", "999");//
        driver = new FirefoxDriver(profile);
        driver.navigate().to(DEFAULT_TEST_URL);
    }

    @After
    public void after() {
        driver.close();
    }
}
