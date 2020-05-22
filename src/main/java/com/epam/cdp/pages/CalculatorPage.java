package com.epam.cdp.pages;

import com.epam.cdp.model.ComputeEngineConfiguration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class CalculatorPage extends BaseCalculatorPage {
    @FindBy(xpath = "//devsite-iframe/child::*")
    WebElement devsiteFrame;

    @FindBy(xpath = "//iframe[@id='myFrame']")
    WebElement calculatorFrame;

    @FindBy(css = "input[ng-model='listingCtrl.computeServer.quantity']")
    private WebElement instanceCountInput;

    @FindBy(css = "md-select[ng-model='listingCtrl.computeServer.os']")
    private WebElement operatingSystemSelect;

    @FindBy(css = "md-select[ng-model='listingCtrl.computeServer.class']")
    private WebElement vmTypeSelect;

    @FindBy(css = "md-select[ng-model='listingCtrl.computeServer.instance']")
    private WebElement inctanceTypeSelect;

    @FindBy(css = "md-checkbox[ng-model='listingCtrl.computeServer.addGPUs']")
    private WebElement addGpuCheckbox;

    @FindBy(css = "md-select[ng-model='listingCtrl.computeServer.gpuCount']")
    private WebElement gpuCountSelect;

    @FindBy(css = "md-select[ng-model='listingCtrl.computeServer.gpuType']")
    private WebElement gpuTypeSelect;

    @FindBy(css = "md-select[ng-model='listingCtrl.computeServer.ssd']")
    private WebElement ssdCountSelect;

    @FindBy(css = "md-select[ng-model='listingCtrl.computeServer.location']")
    private  WebElement datacenterLocationSelect;

    @FindBy(css = "md-select[ng-model='listingCtrl.computeServer.cud']")
    private  WebElement commitesUsageYearsSelect;

    @FindBy(css = "button[ng-click='listingCtrl.addComputeServer(ComputeEngineForm);']")
    private WebElement addToEstimateButton;

    @FindBy(css = "button[ng-click='cloudCartCtrl.showEmailForm();']")
    private WebElement emailEstimateButton;

    @FindBy(css = "h2.md-title b.ng-binding")
    private WebElement totalEstimateCost;

    public CalculatorPage(WebDriver driver) {
        super(driver);
    }

    public BasePage openPage() {
        throw new NotImplementedException();
    }

    public CalculatorPage configureCalculator(ComputeEngineConfiguration calculatorConfiguration) {
        switchToCalculatorFrame();
        fillInstancesCount(calculatorConfiguration.getInstanceCount());
        chooseSelectOption(operatingSystemSelect, calculatorConfiguration.getOs());
        chooseSelectOption(vmTypeSelect, calculatorConfiguration.getVmClass());
        chooseSelectOption(inctanceTypeSelect, calculatorConfiguration.getInstanceType());
        markCheckbox(addGpuCheckbox);
        chooseSelectOption(gpuCountSelect, Integer.toString(calculatorConfiguration.getGpuCount()));
        chooseSelectOption(gpuTypeSelect, calculatorConfiguration.getGpuType());
        chooseSelectOption(ssdCountSelect, Integer.toString(calculatorConfiguration.getSsdCount()));
        chooseSelectOption(datacenterLocationSelect, calculatorConfiguration.getDatacenterLocation());
        chooseSelectOption(commitesUsageYearsSelect, Integer.toString(calculatorConfiguration.getCommitedUsageYears()));
        logger.info("Calculator is configured");
        return new CalculatorPage(driver);
    }

    public CalculatorPage addToEstimate() {
        clickButton(addToEstimateButton);
        return this;
    }

    public String getEstimatedPrice() {
        wait.until(ExpectedConditions.visibilityOf(totalEstimateCost));
        String cost = totalEstimateCost.getText().replaceAll("Total Estimated Cost: USD ", "")
                .replaceAll(" per 1 month", "");
        return cost;
    }

    public CalculatorEmailModal emailEstimate() {
        clickButton(emailEstimateButton);
        return new CalculatorEmailModal(driver);
    }

    private void fillInstancesCount(int count) {
        wait.until(ExpectedConditions.elementToBeClickable(instanceCountInput));
        instanceCountInput.sendKeys(Integer.toString(count));
    }

    private void markCheckbox(WebElement checkbox) {
        wait.until(ExpectedConditions.elementToBeClickable(checkbox));
        jsClick(checkbox);
    }

    private void chooseSelectOption(WebElement select, String value) {
        jsClick(select);
        jsClick(getSelectorOption(select.getAttribute("aria-owns"), value));
    }

    private void clickButton(WebElement button) {
        wait.until(ExpectedConditions.elementToBeClickable(button));
        jsClick(button);
    }

    private WebElement getSelectorOption(String selectId, String value) {
        By optionSelector = By.cssSelector("#" + selectId + " md-option[value='" + value + "']");
        wait.until(ExpectedConditions.elementToBeClickable(optionSelector));
        return driver.findElement(optionSelector);
    }
}
