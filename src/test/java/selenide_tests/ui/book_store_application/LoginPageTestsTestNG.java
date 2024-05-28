package selenide_tests.ui.book_store_application;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import selenide.common_module.data.ExcelDataProvider;
import selenide.ui_module.steps.MainPageSteps;
import selenide.ui_module.steps.book_store_application.BookStoreCommonStepsUI;
import selenide.ui_module.steps.book_store_application.LoginPageSteps;

public class LoginPageTestsTestNG {

    private final MainPageSteps mainPageSteps = new MainPageSteps();
    private final BookStoreCommonStepsUI bookStoreCommonStepsUI = new BookStoreCommonStepsUI();
    private final LoginPageSteps loginPageSteps = new LoginPageSteps();


    @Test(dataProvider = "data")
    public void testWithMethod(String param){
        System.out.println("hello " + param);
    }

    @DataProvider
    public Object[][] data(){
        return new Object[][]{
                {"Yurec"},
                {"Ogurec"},
                {"Golubec"},
                {"Molodec"}
        };
    }

    //то есть можно просто добавить столбец в екселе и добавить доп параметр в тесте,
    // и тест будет тогда уже обрабатывать больше данных
    @Test(dataProvider = "usersFromSheet1", dataProviderClass = ExcelDataProvider.class)
    public void testWithExcel(String userNumber, String userName, String surName){
        System.out.println("Пользователь под номером: " + userNumber + " имеет имя: " + userName + " " +surName);
    }

    //тоже-самое что и выше, но уже нам не надо указывать название каждого параметра,
    // будет автоматически подставлять данные
    @Test(dataProvider = "usersFromSheet1", dataProviderClass = ExcelDataProvider.class)
    public void testWithExcel(String ... params){
        System.out.println("Пользователь под номером: " + params[0] + " имеет имя: " + params[1] + " " + params[2]);
    }

    @Test(dataProvider = "credentials")
    public void testAuthorisationWithValidDate(String login, String password){
        mainPageSteps.clickBookStoreApplication();
        bookStoreCommonStepsUI.openLoginPage();
        loginPageSteps.authorisation(login, password);
    }

    @DataProvider
    public Object[][] credentials(){
        return new Object[][]{
              //  {"YurecAQA", "Yurij.Golikov_1999*"},
                {"Yurij", "Yurij_@1999"}
        };
    }
}
