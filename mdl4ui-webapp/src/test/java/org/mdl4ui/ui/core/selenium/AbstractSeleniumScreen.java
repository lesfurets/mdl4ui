/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.ui.core.selenium;

import org.apache.log4j.Logger;
import org.mdl4ui.base.model.BlockID;
import org.mdl4ui.base.model.FieldID;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public abstract class AbstractSeleniumScreen<T extends SeleniumScreen> implements SeleniumScreen {

    private final Logger LOGGER = Logger.getLogger(getClass().getSimpleName());

    @FindBy(xpath = "//button[@class='blue-button back-link']")
    private WebElement retourButton;

    protected final WebDriver driver;

    public AbstractSeleniumScreen(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    protected Logger getLogger() {
        return this.LOGGER;
    }

    @Override
    public SeleniumBlock getBlock(BlockID id) {
        throw new UnsupportedOperationException("not implements");
    }

    @Override
    public SeleniumBlock getBlock(FieldID id) {
        throw new UnsupportedOperationException("not implements");
    }

    @Override
    public void clickOptionByLabel(WebElement optionWebElement, String value) {
        optionWebElement.sendKeys("\n"); // force focus

        Select select = new Select(optionWebElement);
        LOGGER.info(String.format("Trying to find a list option by label: %s", value));
        for (WebElement option : select.getOptions()) {
            LOGGER.info(String.format("option found: %s", option.getText()));
        }
        select.selectByVisibleText(value);
    }

    @Override
    public void clickOptionByValue(WebElement optionWebElement, String value) {
        optionWebElement.sendKeys("\n"); // force focus

        Select select = new Select(optionWebElement);
        LOGGER.info(String.format("Trying to find a list option by value: %s", value));
        for (WebElement option : select.getOptions()) {
            LOGGER.info(String.format("option found: %s", option.getAttribute("value")));
        }
        select.selectByValue(value);
    }

    public void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            LOGGER.error("exception occured ", e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T assertDisplayed() {
        try {
            getDriver().findElement(getIdentifyingElementLocator());
        } catch (NoSuchElementException e) {
            LOGGER.error("Page attendue inexistante:" + getScreenID());
            throw new RuntimeException("Page attendue inexistante: " + getScreenID());
        }
        return (T) this;
    }

    public void back() {
        retourButton.sendKeys("\n");
    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }

    @Override
    public void setValue(WebElement element, String value) {
        LOGGER.info("Set Value : " + element + ", value:" + value);
        scrollAndClick(element);
        element.clear();
        element.sendKeys(value);
    }

    @Override
    public void scrollAndClick(WebElement webElement) {
        ((Locatable) webElement).getCoordinates().inViewPort();
        webElement.click();
    }
}
