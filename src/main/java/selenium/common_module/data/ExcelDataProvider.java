package selenium.common_module.data;

import org.testng.annotations.DataProvider;
import selenium.common_module.constants.ExcelPageNumber;

public class ExcelDataProvider {

    static final String path = "src/main/resources/names_data.xlsx";

    //брать данные с первого листа
    @DataProvider
    public Object[][] usersFromSheet() throws Exception {
        ExcelReader excelReader = new ExcelReader(path);
        return excelReader.getSheetDataForTDD();
    }

    // TODO
    //  доработать, чтобы можно было в самом тестовом методе указывать лист,
    //  и данный метод был универсальным
    //брать данные с выбранного нами листа
    @DataProvider
    public Object[][] usersFromSheet2() throws Exception {
        ExcelReader excelReader = new ExcelReader(path, ExcelPageNumber.SECOND_SHEET.getSheet());
        return excelReader.getCustomSheetDataForTDD();
    }
}
