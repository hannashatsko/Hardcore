package com.epam.cdp.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class CalculatorEmailModal extends BaseCalculatorPage {
    @FindBy(css = "input[ng-model='emailQuote.user.email']")
    private WebElement emailInput;

    @FindBy(css = "button[aria-label='Send Email']")
    private WebElement sendEmailButton;

    @FindBy(css = "form[name='emailForm']")
    private WebElement form;

    public CalculatorEmailModal(WebDriver driver) {
        super(driver);
    }

    public BasePage openPage() {
        throw new NotImplementedException();
    }

    public CalculatorEmailModal waitToBeOpened() {
        wait.until(ExpectedConditions.elementToBeClickable(form));
        return this;
    }

    public CalculatorEmailModal enterEmailFromClipboard() {
        switchToCalculatorFrame();
        wait.until(ExpectedConditions.visibilityOf(emailInput)).sendKeys(Keys.chord(Keys.CONTROL, "v"));
        return this;
    }

    public CalculatorPage submitEmailForm() {
        wait.until(ExpectedConditions.elementToBeClickable(sendEmailButton));
        jsClick(sendEmailButton);
        return new CalculatorPage(driver);
    }
}
