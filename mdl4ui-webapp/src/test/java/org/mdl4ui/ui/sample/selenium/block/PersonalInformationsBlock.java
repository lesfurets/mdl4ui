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
public class PersonalInformationsBlock extends AbstractSeleniumBlock<PersonalInformationsBlock> {

    @FindBy(id = "FIRST_NAME")
    private WebElement firstName;

    @FindBy(id = "LAST_NAME")
    private WebElement lastName;

    @FindBy(id = "EMAIL")
    private WebElement email;

    @FindBy(xpath = "//div[@id='BIRTHDATE']/input")
    private WebElement birthdate;

    @FindBy(id = "PERSONAL_INFORMATIONS_NEXT")
    private WebElement next;

    public PersonalInformationsBlock(SeleniumScreen screen, WebDriver driver) {
        super(screen, driver);
    }

    @Override
    protected WebElement getNextButton() {
        return next;
    }
    
    @Override
    public BlockID getID() {
        return EBlockSample.PERSONAL_INFORMATIONS;
    }

	@Override    
    public void setValue(FieldID fieldID, String value) {
    	if(value == null)
    		return;
		if(fieldID == EFieldSample.FIRST_NAME)
			setFirstName(value);
		if(fieldID == EFieldSample.LAST_NAME)
			setLastName(value);
		if(fieldID == EFieldSample.EMAIL)
			setEmail(value);
		if(fieldID == EFieldSample.BIRTHDATE)
			setBirthdate(value);

    }
    
    @Override    
    public boolean has(FieldID fieldID) {
		if(fieldID == EFieldSample.FIRST_NAME)
			return true;
		if(fieldID == EFieldSample.LAST_NAME)
			return true;
		if(fieldID == EFieldSample.EMAIL)
			return true;
		if(fieldID == EFieldSample.BIRTHDATE)
			return true;

    	return false;
    }

    public PersonalInformationsBlock setFirstName(String value) {
        getScreen().setValue(firstName, value);
        firstName.sendKeys("\n");
        return this;
    }

    public PersonalInformationsBlock setLastName(String value) {
        getScreen().setValue(lastName, value);
        lastName.sendKeys("\n");
        return this;
    }

    public PersonalInformationsBlock setEmail(String value) {
        getScreen().setValue(email, value);
        email.sendKeys("\n");
        return this;
    }

 /*   public PersonalInformationsBlock setBirthdate(org.joda.time.ReadableDateTime date) {
    	final java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("dd/MM/yyyy");
    	format.setTimeZone(java.util.TimeZone.getTimeZone("GMT"));
        getScreen().setValue(birthdate, format.format(date.toDateTime().toDate()));
        birthdate.sendKeys("\n");
        return this;
     }
    */
    public PersonalInformationsBlock setBirthdate(String date) {
      //  try {
        	// import JODA time API
            // final java.text.SimpleDateFormat formatIso = new java.text.SimpleDateFormat("yyyyMMdd");
        	// formatIso.setTimeZone(java.util.TimeZone.getTimeZone("GMT"));
            // org.joda.time.DateTime dateTime = new org.joda.time.DateTime(formatIso.parse(date).getTime());
            // setBirthdate(dateTime);
            return this;
       // } catch (java.text.ParseException e) {
      //      throw new IllegalArgumentException("cannot parse date, check date format yyyyMMdd", e);
      //  }
    }


	public WebElement getFirstName(){
		return firstName;
	}

	public WebElement getLastName(){
		return lastName;
	}

	public WebElement getEmail(){
		return email;
	}

    public WebElement getBirthdate() {
        return birthdate;
    }

}
