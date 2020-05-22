package com.epam.cdp.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class TabManager {
    private List<String> registeredTabHandles;
    private WebDriver driver;

    public TabManager(WebDriver driver) {
        this.driver = driver;
        registeredTabHandles = new LinkedList<String>();
    }

    public String registerTab(String windowHandle) {
        registeredTabHandles.add(windowHandle);
        return windowHandle;
    }

    public void switchToUrl(String url) {
        driver.switchTo().window(createNewTab(url));
    }

    public void switchToIndex(int index) {
        driver.switchTo().window(registeredTabHandles.get(index));
    }

    private String createNewTab(String url) {
        ((JavascriptExecutor)driver).executeScript("window.open(arguments[0])", url);
        Set<String> windowHandles = driver.getWindowHandles();
        windowHandles.removeAll(registeredTabHandles);
        String newWindowHandle = (String)windowHandles.toArray()[0];
        registeredTabHandles.add(newWindowHandle);
        return newWindowHandle;
    }
}
