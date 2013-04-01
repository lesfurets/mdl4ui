/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.ui.sample.selenium.screen;

import org.mdl4ui.ui.sample.EScreenSample;
import org.mdl4ui.ui.sample.selenium.block.PersonalInformationsBlock;
import org.mdl4ui.ui.sample.EBlockSample;
import org.mdl4ui.ui.sample.selenium.block.AccountBlock;
import org.mdl4ui.ui.sample.selenium.block.MailSettingsBlock;


import javax.annotation.Generated;

import org.openqa.selenium.WebDriver;

import org.mdl4ui.base.model.*;
import org.mdl4ui.ui.core.selenium.*;

/**
 * @see org.mdl4ui.ui.sample.EScreenSample
 */
@Generated(value = "org.mdl4ui.ui.sample.EScreenSample")
public class RegistrationByMailScreen extends AbstractSeleniumScreen<RegistrationByMailScreen> {

    private final PersonalInformationsBlock personalInformations;
    private final MailSettingsBlock mailSettings;
    private final AccountBlock account;

    public RegistrationByMailScreen(WebDriver driver) {
        super(driver);
        this.personalInformations = new PersonalInformationsBlock(this, driver);
        this.mailSettings = new MailSettingsBlock(this, driver);
        this.account = new AccountBlock(this, driver);
    }

    @Override
    public ScreenID getScreenID() {
        return EScreenSample.SCR_REGISTRATION_BY_MAIL;
    }

    @Override
    public org.openqa.selenium.By getIdentifyingElementLocator() {
        return personalInformations.getIdentifyingElementLocator();
    }
    
    @Override
    public SeleniumBlock getBlock(BlockID id) {
		if(id == EBlockSample.PERSONAL_INFORMATIONS)
			return personalInformations;
		if(id == EBlockSample.MAIL_SETTINGS)
			return mailSettings;
		if(id == EBlockSample.ACCOUNT)
			return account;
		return null;
    }
    
    @Override
    public SeleniumBlock getBlock(FieldID id) {
		if(personalInformations.has(id))
			return personalInformations;
		if(mailSettings.has(id))
			return mailSettings;
		if(account.has(id))
			return account;
		return null;
    }
    
    public PersonalInformationsBlock getPersonalInformations() {
        return personalInformations;
    }

    public MailSettingsBlock getMailSettings() {
        return mailSettings;
    }

    public AccountBlock getAccount() {
        return account;
    }

}
