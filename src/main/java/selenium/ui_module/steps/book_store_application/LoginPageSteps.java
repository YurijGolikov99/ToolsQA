package selenium.ui_module.steps.book_store_application;

import io.qameta.allure.Step;
import selenium.ui_module.pages.LoginPage;

public class LoginPageSteps {

    private final LoginPage loginPage = new LoginPage();

    @Step("Залогиниться с валидными данными")
    public void auth(String userName, String password){
        loginPage.getUserNameField().sendKeys(userName);
        loginPage.getPasswordField().sendKeys(password);
        loginPage.getLoginButton().click();
    }
}
