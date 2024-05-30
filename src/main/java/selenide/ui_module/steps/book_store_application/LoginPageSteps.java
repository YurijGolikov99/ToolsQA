package selenide.ui_module.steps.book_store_application;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import selenide.ui_module.pages.book_store_application.LoginPage;

public class LoginPageSteps {

    protected static final Logger logger = LogManager.getLogger();

    private final LoginPage loginPage = new LoginPage();

    @Step("Залогиниться с валидными данными")
    public void auth(String userName, String password){
        loginPage.getUserNameField().sendKeys(userName);
        loginPage.getPasswordField().sendKeys(password);
        loginPage.getLoginButton().click();
    }
}
