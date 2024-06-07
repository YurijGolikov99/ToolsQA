package selenide_tests.ui.book_store_application;

import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import selenide.common_module.driver.tg.DriverManagerTG;
import selenide.common_module.property.PropertyHelper;
import selenide.ui_module.constants.UiEndpoints;
import selenide.ui_module.pages.MainPage;
import selenide.ui_module.steps.MainPageSteps;
import selenide.ui_module.steps.book_store_application.BookStoreCommonStepsUI;
import selenide.ui_module.steps.book_store_application.LoginPageSteps;
import selenium.common_module.data.Credentials;

public class LoginPageTestsTestNG extends DriverManagerTG {

    private final MainPage mainPage = new MainPage();
    private final MainPageSteps mainPageSteps = new MainPageSteps();
    private final BookStoreCommonStepsUI bookStoreCommonStepsUI = new BookStoreCommonStepsUI();
    private final LoginPageSteps loginPageSteps = new LoginPageSteps();

    @Description("Открываем страницу, переходим в книжный, открываем логин пейдж и логинимся")
    @AllureId("7")
    @Issue("IDF-T1")
    @Epic("Книжный магазин")
    @Owner("Юра Голиков")
    @Test(dataProvider = "credentials", priority = 1)
    public void testAuthorisationWithValidDate(String login, String password){
        mainPage.openPage(UiEndpoints.BASE_URL.getUrl());
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
