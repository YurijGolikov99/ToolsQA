package selenide_tests.ui.book_store_application;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import selenide.common_module.driver.hard_initialization.DriverProvider;
import selenide.common_module.property.PropertyHelper;
import selenide.ui_module.steps.MainPageSteps;
import selenide.ui_module.steps.book_store_application.BookStoreCommonStepsUI;
import selenide.ui_module.steps.book_store_application.LoginPageSteps;

public class LoginPageTestsTestNG extends DriverProvider {

    private final MainPageSteps mainPageSteps = new MainPageSteps();
    private final BookStoreCommonStepsUI bookStoreCommonStepsUI = new BookStoreCommonStepsUI();
    private final LoginPageSteps loginPageSteps = new LoginPageSteps();

    // TODO
    //  изменить работу классов, чтобы можно было запускать параметризованные тесты
    //  в данный момент проблема в параметрах, тест не дает выполнить второй раз тоже самое
    @Test(dataProvider = "credentials")
    public void testAuthorisationWithValidDate(String login, String password){
        mainPageSteps.clickBookStoreApplication();
        bookStoreCommonStepsUI.openLoginPage();
        loginPageSteps.authorisation(login, password);
    }

    @DataProvider
    public Object[][] credentials(){
        return new Object[][]{
//                {Credentials.TEST_USERNAME, Credentials.TEST_PASSWORD},
                {PropertyHelper.getProperty("login.user"), PropertyHelper.getProperty("password.user")}
        };
    }
}
