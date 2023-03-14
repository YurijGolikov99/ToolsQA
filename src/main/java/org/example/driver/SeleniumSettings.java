package org.example.driver;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

abstract public class SeleniumSettings { //BaseSeleniumTest
    protected WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage()
                .window().maximize();
        driver.manage()
                .timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage()
                .timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        SeleniumDriver.setDriver(driver);
    }
    @After
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}