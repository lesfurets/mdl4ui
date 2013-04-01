/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.ui.core.selenium;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.mdl4ui.base.model.FieldID;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public abstract class AbstractSeleniumBlock<B extends SeleniumBlock> implements SeleniumBlock {

    private final Logger LOGGER = Logger.getLogger(getClass().getSimpleName());

    protected final SeleniumScreen page;
    protected final WebDriver driver;
    protected final Wait<WebDriver> waitBlock;

    public AbstractSeleniumBlock(SeleniumScreen page, WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.page = page;
        this.driver = page.getDriver();
        this.waitBlock = new FluentWait<WebDriver>(driver).ignoring(NoSuchElementException.class);
    }

    @Override
    public SeleniumScreen getScreen() {
        return page;
    }

    @Override
    public final WebElement get(FieldID fieldID) {
        try {
            return getScreen().getDriver().findElement(By.id(fieldID.toString()));
        } catch (TimeoutException e) {
            return null;
        }
    }

    @Override
    public final List<String> getDisplayedFields() {
        List<String> ids = new ArrayList<String>();
        String xpath = "//div[@id='" + getContentPanelID() + "']//*[contains(@class, 'field-component')]";
        for (WebElement found : page.getDriver().findElements(By.xpath(xpath))) {
            if (found.isDisplayed())
                ids.add(found.getAttribute("id"));
        }
        return ids;
    }

    @Override
    public final By getIdentifyingElementLocator() {
        return By.id(getContentPanelID());
    }

    protected String getContentPanelID() {
        return getID().toString().toUpperCase() + "_CONTENT";
    }

    @Override
    @SuppressWarnings("unchecked")
    public B assertDisplayed() {
        if (!waitUntilDisplayed()) {
            LOGGER.error("Bloc attendu non visible:" + this);
            throw new RuntimeException("Bloc attendu non visible:" + this);
        }
        return (B) this;
    }

    @Override
    public boolean waitUntilDisplayed() {
        try {
            waitBlock.until(visibilityOfElementLocated(getIdentifyingElementLocator()));
        } catch (TimeoutException e) {
            return false;
        } catch (Exception e) {
            LOGGER.error("Bloc attendu inexistant:" + getID(), e);
            throw new RuntimeException("Bloc attendu inexistant:" + getID(), e);
        }
        return true;
    }

    @Override
    public boolean waitUntilHidden() {
        try {
            waitBlock.until(invisibilityOfElementLocated(getIdentifyingElementLocator()));
        } catch (TimeoutException e) {
            return false;
        } catch (Exception e) {
            LOGGER.error("Bloc attendu inexistant:" + getID(), e);
            throw new RuntimeException("Bloc attendu inexistant:" + getID(), e);
        }
        return true;
    }

    protected abstract WebElement getNextButton();

    @Override
    @SuppressWarnings("unchecked")
    public final B submit() {
        assertDisplayed();
        getNextButton().sendKeys("\n");
        return (B) this;
    }
}
