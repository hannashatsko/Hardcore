package com.epam.cdp.selenium;

import com.epam.cdp.model.ComputeEngineConfiguration;
import com.epam.cdp.pages.*;
import com.epam.cdp.utils.TabManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleCloudPlatformPricingCalculatorTest extends BaseTest {
    private static final String SEARCH_QUERY = "Google Cloud Platform Pricing Calculator";

    @Test(groups = {"SmokeTest", "P0"}, description="Fill in form for order and compare prices")
    public void testCalculateAndComparePriceTest() {
        TabManager tabManager = new TabManager(driver);

        ComputeEngineConfiguration calculatorConfiguration = new ComputeEngineConfiguration(
                Integer.parseInt(config.getProperty("instanceCount")),
                config.getProperty("os"),
                config.getProperty("vmClass"),
                config.getProperty("instanceType"),
                Integer.parseInt(config.getProperty("gpuCount")),
                config.getProperty("gpuType"),
                Integer.parseInt(config.getProperty("ssdCount")),
                config.getProperty("datacenterLocation"),
                Integer.parseInt(config.getProperty("commitedUsageYears"))
        );
        StartPage startPage = new StartPage(driver).openPage();
        tabManager.registerTab(driver.getWindowHandle());
        ResultPage resultPage = startPage.search(SEARCH_QUERY);
        Assert.assertTrue(resultPage.isOpened());
        CalculatorPage calculatorPage = resultPage.openFirstLink();
        calculatorPage = calculatorPage.configureCalculator(calculatorConfiguration).addToEstimate();
        String googleEstimatedPrice = calculatorPage.getEstimatedPrice();
        calculatorPage.emailEstimate().waitToBeOpened();
        tabManager.switchToUrl("https://10minutemail.com/");
        EmailGeneratorPage emailGeneratorPage = new EmailGeneratorPage(driver);
        String generatedEmail = emailGeneratorPage.getMoreTime().getGeneratedEmail();
        tabManager.switchToIndex(0);
        new CalculatorEmailModal(driver).enterEmail(generatedEmail).submitEmailForm();
        tabManager.switchToIndex(1);
        String emailedEstimatedCost = emailGeneratorPage.getEstimatedMonthlyCost();
        Assert.assertEquals(googleEstimatedPrice, emailedEstimatedCost);
    }
}
