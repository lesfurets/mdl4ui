/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.ui.sample.selenium.block;

import org.mdl4ui.ui.sample.EFieldSample;
import org.mdl4ui.ui.sample.EBlockSample;


import javax.annotation.Generated;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.mdl4ui.base.model.*;
import org.mdl4ui.ui.core.selenium.*;

/**
 * @see org.mdl4ui.ui.sample.EBlockSample
 */
@Generated(value = "org.mdl4ui.ui.sample.EBlockSample")
public class PhoneSettingsBlock extends AbstractSeleniumBlock<PhoneSettingsBlock> {

    @FindBy(id = "LANGUAGE")
    private WebElement language;

    @FindBy(id = "PHONE_NUMBER")
    private WebElement phoneNumber;

    @FindBy(id = "TIMEZONE")
    private WebElement timezone;

    @FindBy(id = "PHONE_SETTINGS_NEXT")
    private WebElement next;

    public PhoneSettingsBlock(SeleniumScreen screen, WebDriver driver) {
        super(screen, driver);
    }

    @Override
    protected WebElement getNextButton() {
        return next;
    }
    
    @Override
    public BlockID getID() {
        return EBlockSample.PHONE_SETTINGS;
    }

	@Override    
    public void setValue(FieldID fieldID, String value) {
    	if(value == null)
    		return;
		if(fieldID == EFieldSample.LANGUAGE)
			setLanguage(value);
		if(fieldID == EFieldSample.PHONE_NUMBER)
			setPhoneNumber(value);
		if(fieldID == EFieldSample.TIMEZONE)
			setTimezone(value);

    }
    
    @Override    
    public boolean has(FieldID fieldID) {
		if(fieldID == EFieldSample.LANGUAGE)
			return true;
		if(fieldID == EFieldSample.PHONE_NUMBER)
			return true;
		if(fieldID == EFieldSample.TIMEZONE)
			return true;

    	return false;
    }

    public PhoneSettingsBlock setLanguage(String value) {
	   	org.openqa.selenium.By xpath = org.openqa.selenium.By.xpath("//div[@id='LANGUAGE']//span[text()=' "+value+" ']");
        WebElement element = getScreen().getDriver().findElement(xpath);
       	getScreen().scrollAndClick(element);
        return this;
    }

    public PhoneSettingsBlock setPhoneNumber(String value) {
        getScreen().setValue(phoneNumber, value);
        phoneNumber.sendKeys("\n");
        return this;
    }

    public PhoneSettingsBlock setTimezoneLabel(String value) {
        getScreen().clickOptionByLabel(timezone, value);
        return this;
    }
    
    public PhoneSettingsBlock setTimezone(String value) {
        getScreen().clickOptionByValue(timezone, value);
        return this;
    }

	public WebElement getLanguage(){
		return language;
	}

	public WebElement getPhoneNumber(){
		return phoneNumber;
	}

	public WebElement getTimezone(){
		return timezone;
	}

}
