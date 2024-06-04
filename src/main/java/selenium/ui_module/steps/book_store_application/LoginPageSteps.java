package selenium.ui_module.steps.book_store_application;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import selenium.common_module.driver.hard_initialization.DriverProvider;
import selenium.ui_module.pages.book_store_application.LogInPage;

public class LoginPageSteps {

    protected static final Logger logger = LogManager.getLogger();

    private final LogInPage loginInPage = new LogInPage(DriverProvider.getDriver());

    @Step()
    public void authorisation(String userName, String password){
        loginInPage.getUserNameField().sendKeys(userName);
        loginInPage.getPasswordField().sendKeys(password);
        loginInPage.getLoginButton().click();
    }
}
