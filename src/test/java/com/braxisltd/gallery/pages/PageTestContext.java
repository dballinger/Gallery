package com.braxisltd.gallery.pages;

import com.google.common.base.Predicate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageTestContext {

    private static final int TIME_OUT_IN_SECONDS = 5;
    private final WebDriver driver;

    public PageTestContext(WebDriver driver) {
        this.driver = driver;
    }

    protected WebDriver driver() {
        return driver;
    }

    protected void goTo(String pageUrl) {
        driver.get(pageUrl);
    }
    
    protected void waitFor(Predicate<WebDriver> condition) {
        WebDriverWait driverWait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
        driverWait.ignoring(RuntimeException.class);
        driverWait.until(condition);
    }
    
    protected <T> T create(Class<T> context) {
        return PageFactory.initElements(driver, context);
    }
}
