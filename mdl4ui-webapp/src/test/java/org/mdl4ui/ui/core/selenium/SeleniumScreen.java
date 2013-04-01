/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.ui.core.selenium;

import org.mdl4ui.base.model.BlockID;
import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.base.model.ScreenID;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface SeleniumScreen {
    SeleniumScreen assertDisplayed();

    WebDriver getDriver();

    ScreenID getScreenID();

    By getIdentifyingElementLocator();

    void clickOptionByLabel(WebElement optionWebElement, String value);

    void clickOptionByValue(WebElement optionWebElement, String value);

    void setValue(WebElement element, String value);

    void scrollAndClick(WebElement webElement);

    SeleniumBlock getBlock(BlockID id);

    SeleniumBlock getBlock(FieldID id);
}
