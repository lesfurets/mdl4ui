/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.ui.sample.selenium.screen;

import org.mdl4ui.ui.sample.EScreenSample;
import org.mdl4ui.ui.sample.selenium.block.PhoneSettingsBlock;
import org.mdl4ui.ui.sample.selenium.block.PersonalInformationsBlock;
import org.mdl4ui.ui.sample.EBlockSample;
import org.mdl4ui.ui.sample.selenium.block.AccountBlock;


import javax.annotation.Generated;

import org.openqa.selenium.WebDriver;

import org.mdl4ui.base.model.*;
import org.mdl4ui.ui.core.selenium.*;

/**
 * @see org.mdl4ui.ui.sample.EScreenSample
 */
@Generated(value = "org.mdl4ui.ui.sample.EScreenSample")
public class RegistrationByPhoneScreen extends AbstractSeleniumScreen<RegistrationByPhoneScreen> {

    private final PersonalInformationsBlock personalInformations;
    private final PhoneSettingsBlock phoneSettings;
    private final AccountBlock account;

    public RegistrationByPhoneScreen(WebDriver driver) {
        super(driver);
        this.personalInformations = new PersonalInformationsBlock(this, driver);
        this.phoneSettings = new PhoneSettingsBlock(this, driver);
        this.account = new AccountBlock(this, driver);
    }

    @Override
    public ScreenID getScreenID() {
        return EScreenSample.SCR_REGISTRATION_BY_PHONE;
    }

    @Override
    public org.openqa.selenium.By getIdentifyingElementLocator() {
        return personalInformations.getIdentifyingElementLocator();
    }
    
    @Override
    public SeleniumBlock getBlock(BlockID id) {
		if(id == EBlockSample.PERSONAL_INFORMATIONS)
			return personalInformations;
		if(id == EBlockSample.PHONE_SETTINGS)
			return phoneSettings;
		if(id == EBlockSample.ACCOUNT)
			return account;
		return null;
    }
    
    @Override
    public SeleniumBlock getBlock(FieldID id) {
		if(personalInformations.has(id))
			return personalInformations;
		if(phoneSettings.has(id))
			return phoneSettings;
		if(account.has(id))
			return account;
		return null;
    }
    
    public PersonalInformationsBlock getPersonalInformations() {
        return personalInformations;
    }

    public PhoneSettingsBlock getPhoneSettings() {
        return phoneSettings;
    }

    public AccountBlock getAccount() {
        return account;
    }

}
