package selenide.common_module.data;

import org.testng.annotations.DataProvider;

public class ExcelDataProvider {

    @DataProvider
    public Object[][] usersFromSheet1() throws Exception {
        String path = "src/main/resources/names_data.xlsx";
        ExcelReader excelReader = new ExcelReader(path);
        return excelReader.getSheetDataForTDD();
    }
}
