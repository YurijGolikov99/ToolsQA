package selenide.ui_module.pages.book_store_application;

import selenide.common_module.driver.EasyInicialisation.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseSeleniumPage {

    @FindBy(xpath = "//input[@id=\"userName\"]")
    private WebElement userNameField;
    @FindBy(xpath = "//input[@id=\"password\"]")
    private WebElement passwordField;
    @FindBy(xpath =  "//button[@id=\"login\"]")
    private WebElement loginButton;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public WebElement getUserNameField(){
        return userNameField;
    }

    public WebElement getPasswordField(){
        return passwordField;
    }

    public WebElement getLoginButton(){
        return loginButton;
    }

    public LoginPage auth(String userName, String password){
        userNameField.sendKeys(userName);
        passwordField.sendKeys(password);
        loginButton.click();
        return new LoginPage();
    }
}
