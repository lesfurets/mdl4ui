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
public class MailSettingsBlock extends AbstractSeleniumBlock<MailSettingsBlock> {

    @FindBy(id = "LANGUAGE")
    private WebElement language;

    @FindBy(id = "EMAIL_ACCEPTED")
    private WebElement emailAccepted;

    @FindBy(id = "EMAILS_PREFERENCES")
    private WebElement emailsPreferences;

    @FindBy(id = "MAX_WEEKLY_EMAILS")
    private WebElement maxWeeklyEmails;

    @FindBy(id = "TIMEZONE")
    private WebElement timezone;

    @FindBy(id = "MAIL_SETTINGS_NEXT")
    private WebElement next;

    public MailSettingsBlock(SeleniumScreen screen, WebDriver driver) {
        super(screen, driver);
    }

    @Override
    protected WebElement getNextButton() {
        return next;
    }
    
    @Override
    public BlockID getID() {
        return EBlockSample.MAIL_SETTINGS;
    }

	@Override    
    public void setValue(FieldID fieldID, String value) {
    	if(value == null)
    		return;
		if(fieldID == EFieldSample.LANGUAGE)
			setLanguage(value);
		if(fieldID == EFieldSample.EMAIL_ACCEPTED)
			setEmailAccepted(value);
		if(fieldID == EFieldSample.EMAILS_PREFERENCES)
			setEmailsPreferences(value);
		if(fieldID == EFieldSample.MAX_WEEKLY_EMAILS)
			setMaxWeeklyEmails(value);
		if(fieldID == EFieldSample.TIMEZONE)
			setTimezone(value);

    }
    
    @Override    
    public boolean has(FieldID fieldID) {
		if(fieldID == EFieldSample.LANGUAGE)
			return true;
		if(fieldID == EFieldSample.EMAIL_ACCEPTED)
			return true;
		if(fieldID == EFieldSample.EMAILS_PREFERENCES)
			return true;
		if(fieldID == EFieldSample.MAX_WEEKLY_EMAILS)
			return true;
		if(fieldID == EFieldSample.TIMEZONE)
			return true;

    	return false;
    }

    public MailSettingsBlock setLanguage(String value) {
	   	org.openqa.selenium.By xpath = org.openqa.selenium.By.xpath("//div[@id='LANGUAGE']//span[text()=' "+value+" ']");
        WebElement element = getScreen().getDriver().findElement(xpath);
       	getScreen().scrollAndClick(element);
        return this;
    }

    public MailSettingsBlock setEmailAccepted(String value) {
	   	org.openqa.selenium.By xpath = org.openqa.selenium.By.xpath("//div[@id='EMAIL_ACCEPTED']//span[text()=' "+value+" ']");
        WebElement element = getScreen().getDriver().findElement(xpath);
       	getScreen().scrollAndClick(element);
        return this;
    }

    public MailSettingsBlock setEmailsPreferences(String value) {
	    org.openqa.selenium.By xpath = org.openqa.selenium.By.xpath("//div[@id='EMAILS_PREFERENCES']//span[text()=' "+value+" ']");
        WebElement element = getScreen().getDriver().findElement(xpath);
       	getScreen().scrollAndClick(element);
        return this;
    }

    public MailSettingsBlock setMaxWeeklyEmails(String value) {
        getScreen().setValue(maxWeeklyEmails, value);
        maxWeeklyEmails.sendKeys("\n");
        return this;
    }

    public MailSettingsBlock setTimezoneLabel(String value) {
        getScreen().clickOptionByLabel(timezone, value);
        return this;
    }
    
    public MailSettingsBlock setTimezone(String value) {
        getScreen().clickOptionByValue(timezone, value);
        return this;
    }

	public WebElement getLanguage(){
		return language;
	}

	public WebElement getEmailAccepted(){
		return emailAccepted;
	}

	public WebElement getEmailsPreferences(){
		return emailsPreferences;
	}

	public WebElement getMaxWeeklyEmails(){
		return maxWeeklyEmails;
	}

	public WebElement getTimezone(){
		return timezone;
	}

}
