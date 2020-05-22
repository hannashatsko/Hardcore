package com.epam.cdp.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected static final int DEFAULT_TIMEOUT = 2;
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected JavascriptExecutor js;
    protected final Logger logger = LogManager.getRootLogger();

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver,DEFAULT_TIMEOUT );
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
    }

    public BasePage refreshPage(){
        driver.navigate().refresh();
        return this;
    }

    public abstract BasePage openPage();

    protected void jsClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        js.executeScript("arguments[0].click();", element);
    }
}


