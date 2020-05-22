package com.epam.cdp.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StartPage extends BasePage {

    @FindBy(css = "input[name='q']")
    private WebElement searchInput;


    public StartPage(WebDriver driver) {
        super(driver);
    }

    public StartPage openPage() {
        driver.get("https://cloud.google.com/ ");
        return this;
    }

    public ResultPage search(String query){
        searchInput.click();
        searchInput.sendKeys(query);
        searchInput.sendKeys(Keys.ENTER);
        return new ResultPage(driver,query);
    }


}
