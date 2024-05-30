package selenium_tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import selenium.common_module.data.ExcelDataProvider;

public class ExcelDataTests {

    @Test(dataProvider = "data")
    public void testWithMethod(String param){
        System.out.println("Hello " + param);
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
    @Test(dataProvider = "usersFromSheet", dataProviderClass = ExcelDataProvider.class)
    public void testWithExcel1(String userNumber, String userName, String surName){
        System.out.println("Пользователь под номером: " + userNumber + " имеет имя: " + userName + " " +surName);
    }

    //тоже-самое что и выше, но уже нам не надо указывать название каждого параметра,
    // будет автоматически подставлять данные
    @Test(dataProvider = "usersFromSheet", dataProviderClass = ExcelDataProvider.class)
    public void testWithExcel2(String ... params){
        System.out.println("Пользователь под номером: " + params[0] + " имеет имя: " + params[1] + " " + params[2]);
    }

    //берем данные с конкретного листа
    @Test(dataProvider = "usersFromSheet2", dataProviderClass = ExcelDataProvider.class)
    public void testWithExcel3(String userNumber, String userName){
        System.out.println("Логин: " + userNumber);
        System.out.println("Пароль: " + userName);
    }
}
