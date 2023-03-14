package org.example.pages;

import org.example.driver.SeleniumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends SeleniumDriver {
    @FindBy(xpath = "//input[@id=\"userName\"]")
    private WebElement userNameField;
    @FindBy(xpath = "//input[@id=\"password\"]")
    private WebElement passwordField;
    @FindBy(xpath =  "//button[@id=\"login\"]")
    private WebElement loginButton;
    public LoginPage() {
        PageFactory.initElements(driver, this);
    }
    public LoginPage auth(String userName, String password){
        userNameField.sendKeys(userName);
        passwordField.sendKeys(password);
        loginButton.click();
        return new LoginPage();
    }
}