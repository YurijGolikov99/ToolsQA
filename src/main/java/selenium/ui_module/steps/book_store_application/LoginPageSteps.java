package selenium.ui_module.steps.book_store_application;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import selenium.ui_module.pages.book_store_application.LogInPage;

public class LoginPageSteps {

    protected static final Logger logger = LogManager.getLogger();

    private LogInPage loginInPage;

    public LoginPageSteps(WebDriver driver){
        this.loginInPage = new LogInPage(driver);
    }

    @Step()
    public void authorisation(String userName, String password){
        loginInPage.getUserNameField().sendKeys(userName);
        loginInPage.getPasswordField().sendKeys(password);
        loginInPage.getLoginButton().click();
    }
}
