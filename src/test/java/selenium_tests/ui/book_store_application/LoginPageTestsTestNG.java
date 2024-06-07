package selenium_tests.ui.book_store_application;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import selenium.common_module.data.Credentials;
import selenium.ui_module.steps.MainPageSteps;
import selenium.ui_module.steps.book_store_application.BookStoreCommonStepsUI;
import selenium.ui_module.steps.book_store_application.LoginPageSteps;
import selenium_tests.BaseTest;

public class LoginPageTestsTestNG extends BaseTest {

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
                {Credentials.TEST_USERNAME, Credentials.TEST_PASSWORD},
//                {PropertyHelper.getProperty("login.user"), PropertyHelper.getProperty("password.user")}
        };
    }
}
