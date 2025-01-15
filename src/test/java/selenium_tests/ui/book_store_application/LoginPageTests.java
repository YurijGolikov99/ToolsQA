package selenium_tests.ui.book_store_application;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import selenium.common_module.data.Credentials;
import selenium.common_module.property.PropertyHelper;
import selenium.ui_module.constants.UiEndpoints;
import selenium.ui_module.pages.MainPage;
import selenium.ui_module.steps.MainPageSteps;
import selenium.ui_module.steps.book_store_application.BookStoreCommonStepsUI;
import selenium.ui_module.steps.book_store_application.LoginPageSteps;
import selenium_tests.BaseTest;

public class LoginPageTests extends BaseTest {

    private MainPage mainPage;
    private MainPageSteps mainPageSteps;
    private BookStoreCommonStepsUI bookStoreCommonStepsUI;
    private LoginPageSteps loginPageSteps;

    @BeforeMethod
    public void setUpTest() {
        mainPage = new MainPage(this.driver);
        mainPageSteps = new MainPageSteps(this.driver);
        bookStoreCommonStepsUI = new BookStoreCommonStepsUI(this.driver);
        loginPageSteps = new LoginPageSteps(this.driver);
    }

    @Test
    public void testAuthorisationWithValidDate(){
        mainPage.openPage(UiEndpoints.BASE_URL.getUrl());
        mainPageSteps.clickBookStoreApplication();
        bookStoreCommonStepsUI.openLoginPage();
        loginPageSteps.authorisation(
                PropertyHelper.getProperty("login.user"),
                PropertyHelper.getProperty("password.user"));
    }

    @Test(dataProvider = "credentials")
    public void testAuthorisation(String login, String password){
        mainPageSteps.clickBookStoreApplication();
        bookStoreCommonStepsUI.openLoginPage();
        loginPageSteps.authorisation(login, password);
    }

    @DataProvider
    public Object[][] credentials(){
        return new Object[][]{
                {Credentials.TEST_USERNAME, Credentials.TEST_PASSWORD},
                {PropertyHelper.getProperty("login.user"), PropertyHelper.getProperty("password.user")}
        };
    }
}
