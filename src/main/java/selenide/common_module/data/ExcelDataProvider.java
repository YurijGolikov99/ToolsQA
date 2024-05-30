package selenide.common_module.data;

import org.testng.annotations.DataProvider;

public class ExcelDataProvider {

    //брать данные с первого листа
    @DataProvider
    public Object[][] usersFromSheet() throws Exception {
        String path = "src/main/resources/names_data.xlsx";
        ExcelReader excelReader = new ExcelReader(path);
        return excelReader.getSheetDataForTDD();
    }

    // TODO
    //  доработать, чтобы можно было в самом тестовом методе указывать лист,
    //  и данный метод был универсальным
    //брать данные с выбранного нами листа
    @DataProvider
    public Object[][] usersFromSheet2() throws Exception {
        String path = "src/main/resources/names_data.xlsx";
        ExcelReader excelReader = new ExcelReader(path, ExcelPageNumber.SECOND_SHEET.getSheet());
        return excelReader.getCustomSheetDataForTDD();
    }
}
