package org.example.driver;

import org.openqa.selenium.WebDriver;

abstract public class SeleniumDriver { //BaseSeleniumPage
    protected static WebDriver driver;
    public static void setDriver(WebDriver webDriver){
        driver = webDriver;
    }
}
