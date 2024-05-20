package selenide.ui_module.steps.book_store_application;

import io.qameta.allure.Step;
import selenide.ui_module.pages.book_store_application.LogInPage;

public class LoginPageSteps {

    private final LogInPage loginInPage = new LogInPage();

    @Step()
    public void authorisation(String userName, String password){
        loginInPage.getUserNameField().sendKeys(userName);
        loginInPage.getPasswordField().sendKeys(password);
        loginInPage.getLoginButton().scrollTo();
        loginInPage.getLoginButton().click();
    }
}
