/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.ui.core.selenium;

import java.util.List;

import org.mdl4ui.base.model.BlockID;
import org.mdl4ui.base.model.FieldID;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface SeleniumBlock {

    BlockID getID();

    SeleniumScreen getScreen();

    SeleniumBlock assertDisplayed();

    List<String> getDisplayedFields();

    boolean waitUntilDisplayed();

    boolean waitUntilHidden();

    WebElement getTitle();

    String getTitleID();

    By getIdentifyingElementLocator();

    SeleniumBlock submit();

    WebElement get(FieldID fieldID);

    boolean has(FieldID fieldID);

    void setValue(FieldID fieldID, String value);
}
