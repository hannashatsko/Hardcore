package com.epam.cdp.selenium;

import com.epam.cdp.driver.DriverSingleton;
import com.epam.cdp.utils.ConfigReader;
import com.epam.cdp.utils.TestListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.util.Properties;

@Listeners({TestListener.class})
public abstract class BaseTest {
    protected WebDriver driver;
    protected Properties config;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    protected void startBrowser() {
        driver = DriverSingleton.getDriver();
        config = new ConfigReader("/development.properties").getProperties();
        driver.manage().window().maximize();
    }

    @AfterMethod
    protected void browserTearDown() {
        driver.quit();
        driver = null;
    }
}