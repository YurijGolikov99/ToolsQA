package org.example.pages;

import org.example.driver.SeleniumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookStore extends SeleniumDriver { //MainPage
    @FindBy(xpath = "//button[@id=\"login\"]")
    private  WebElement loginButton;
    public BookStore() {
        driver.get("https://demoqa.com/books");
        PageFactory.initElements(driver, this);
    }
    public LoginPage openLoginPage(){ //createTicket
        loginButton.click();
        return new LoginPage();
    }
}
