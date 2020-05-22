package com.epam.cdp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ResultPage extends StartPage {
    private String query;
    public ResultPage(WebDriver driver, String query) {
        super(driver);
        this.query = query;
    }

    @FindBy (css = "a.gs-title")
    private WebElement firstRow;

    public boolean isOpened() {
        return getResultHeader() != null;
    }

    public CalculatorPage openFirstLink(){
        firstRow.click();
        return new CalculatorPage(driver);
    }

    private WebElement getResultHeader(){
        By resultHeaderSelector = By.xpath("//span[@class= 'devsite-search-term' and contains(text(), '" + query + "')]" );
        wait.until(ExpectedConditions.visibilityOfElementLocated(resultHeaderSelector));
        return driver.findElement(resultHeaderSelector);
    }
}
