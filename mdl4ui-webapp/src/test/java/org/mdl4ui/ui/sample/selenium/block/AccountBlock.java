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
public class AccountBlock extends AbstractSeleniumBlock<AccountBlock> {

    @FindBy(id = "LOGIN")
    private WebElement login;

    @FindBy(id = "PASSWORD")
    private WebElement password;

    @FindBy(id = "PASSWORD_CONFIRMATION")
    private WebElement passwordConfirmation;

    @FindBy(id = "ACCOUNT_NEXT")
    private WebElement next;

    public AccountBlock(SeleniumScreen screen, WebDriver driver) {
        super(screen, driver);
    }

    @Override
    protected WebElement getNextButton() {
        return next;
    }
    
    @Override
    public BlockID getID() {
        return EBlockSample.ACCOUNT;
    }

	@Override    
    public void setValue(FieldID fieldID, String value) {
    	if(value == null)
    		return;
		if(fieldID == EFieldSample.LOGIN)
			setLogin(value);
		if(fieldID == EFieldSample.PASSWORD)
			setPassword(value);
		if(fieldID == EFieldSample.PASSWORD_CONFIRMATION)
			setPasswordConfirmation(value);

    }
    
    @Override    
    public boolean has(FieldID fieldID) {
		if(fieldID == EFieldSample.LOGIN)
			return true;
		if(fieldID == EFieldSample.PASSWORD)
			return true;
		if(fieldID == EFieldSample.PASSWORD_CONFIRMATION)
			return true;

    	return false;
    }

    public AccountBlock setLogin(String value) {
        getScreen().setValue(login, value);
        login.sendKeys("\n");
        return this;
    }

    public AccountBlock setPassword(String value) {
        getScreen().setValue(password, value);
        password.sendKeys("\n");
        return this;
    }

    public AccountBlock setPasswordConfirmation(String value) {
        getScreen().setValue(passwordConfirmation, value);
        passwordConfirmation.sendKeys("\n");
        return this;
    }

	public WebElement getLogin(){
		return login;
	}

	public WebElement getPassword(){
		return password;
	}

	public WebElement getPasswordConfirmation(){
		return passwordConfirmation;
	}

}
