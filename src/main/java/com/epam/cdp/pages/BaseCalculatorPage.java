package com.epam.cdp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class BaseCalculatorPage extends BasePage {
    @FindBy(xpath = "//devsite-iframe/child::*")
    WebElement devsiteFrame;

    @FindBy(xpath = "//iframe[@id='myFrame']")
    WebElement calculatorFrame;

    public BaseCalculatorPage(WebDriver driver) {
        super(driver);
    }

    protected void switchToCalculatorFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(devsiteFrame));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(calculatorFrame));
    }
}
