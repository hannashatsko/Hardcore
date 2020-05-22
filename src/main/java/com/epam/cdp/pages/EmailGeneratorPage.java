package com.epam.cdp.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EmailGeneratorPage extends BasePage {
    @FindBy(css = "div[id='copy_address']")
    WebElement copyEmailAddress;

    @FindBy(css = "div#get_more_time")
    WebElement getMoreTimeButton;

    @FindBy(css = "div.small_message_icon_box")
    WebElement emailMessage;

    @FindBy(css = "div#Email h2")
    WebElement estimatedCost;

    @FindBy(css = "#inbox_count_number")
    WebElement emailCount;

    public EmailGeneratorPage(WebDriver driver) {
        super(driver);
    }

    public EmailGeneratorPage openPage() {
        driver.get("https://10minutemail.com/");
        return this;
    }

    public EmailGeneratorPage getMoreTime() {
        wait.until(ExpectedConditions.elementToBeClickable(getMoreTimeButton));
        getMoreTimeButton.click();
        return this;
    }

    public EmailGeneratorPage copyEmailToClipboard() {
        wait.until(ExpectedConditions.elementToBeClickable(copyEmailAddress));
        copyEmailAddress.click();
        return this;
    }

    public String getEstimatedMonthlyCost() {
        wait.until(ExpectedConditions.textToBePresentInElement(emailCount, "1"));
        wait.until(ExpectedConditions.elementToBeClickable(emailMessage));
        jsClick(emailMessage);
        wait.until(ExpectedConditions.visibilityOf(estimatedCost));
        String cost = estimatedCost.getText().replaceAll("Estimated Monthly Cost: USD ", "");
        return cost;
    }
}
