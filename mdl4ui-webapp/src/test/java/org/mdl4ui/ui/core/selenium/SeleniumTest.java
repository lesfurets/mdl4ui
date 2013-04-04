package org.mdl4ui.ui.core.selenium;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.mdl4ui.ui.sample.EScenarioSample;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.PageFactory;

public class SeleniumTest {

    private static final Logger LOGGER = Logger.getLogger(SeleniumTest.class);
    private static final String DEFAULT_TEST_URL = "http://127.0.0.1:8080/mdl4ui-webapp/";

    private final EScenarioSample scenario;
    private WebDriver driver;

    public SeleniumTest(EScenarioSample scenario) {
        this.scenario = scenario;
    }

    public WebDriver getDriver() {
        return driver;
    }

    @Before
    public void before() {
        FirefoxProfile profile = new FirefoxProfile();

        // disable unresponsive scripts popup
        profile.setPreference("dom.max_script_run_time", "999");//

        // launch browser and
        driver = new FirefoxDriver(profile);

        // open webapp open url
        String url = DEFAULT_TEST_URL + "#" + scenario.toString();
        driver.navigate().to(url);
        LOGGER.info(url);

        // look up the injected web elements
        PageFactory.initElements(getDriver(), this);
    }

    @After
    public void after() {
        driver.quit();
    }
}
