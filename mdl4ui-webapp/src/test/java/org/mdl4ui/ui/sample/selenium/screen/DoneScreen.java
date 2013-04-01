/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.ui.sample.selenium.screen;

import org.mdl4ui.ui.sample.EScreenSample;


import javax.annotation.Generated;

import org.openqa.selenium.WebDriver;

import org.mdl4ui.base.model.*;
import org.mdl4ui.ui.core.selenium.*;

/**
 * @see org.mdl4ui.ui.sample.EScreenSample
 */
@Generated(value = "org.mdl4ui.ui.sample.EScreenSample")
public class DoneScreen extends AbstractSeleniumScreen<DoneScreen> {



    public DoneScreen(WebDriver driver) {
        super(driver);

    }

    @Override
    public ScreenID getScreenID() {
        return EScreenSample.SCR_DONE;
    }

    @Override
    public org.openqa.selenium.By getIdentifyingElementLocator() {
        return null;
    }
    
    @Override
    public SeleniumBlock getBlock(BlockID id) {

		return null;
    }
    
    @Override
    public SeleniumBlock getBlock(FieldID id) {

		return null;
    }
    

}
