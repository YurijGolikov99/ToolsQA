package selenium.ui_module.pages.book_store_application;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;


/**
 *6.Элементы страницы LogIn
 */
public class LogInPage  {

    private final SelenideElement userNameField = $x("//input[@id='userName']");
    private final SelenideElement passwordField = $x("//input[@id='password']");
    private final SelenideElement loginButton = $x("//button[@id=\"login\"]");
    private final SelenideElement newUserButton = $x("//button[@id='newUser']");

    public SelenideElement getUserNameField(){
        return userNameField;
    }

    public SelenideElement getPasswordField(){
        return passwordField;
    }

    public SelenideElement getLoginButton(){
        return loginButton;
    }

    public SelenideElement getNewUserButton(){
        return newUserButton;
    }
}
