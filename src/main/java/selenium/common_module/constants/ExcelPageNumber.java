package selenium.common_module.constants;

//Енумы с названиями листов Excel, надо менять если название листа изменилось
public enum ExcelPageNumber {
    FIRST_SHEET("Лист1"),
    SECOND_SHEET("Лист2"),
    THIRD_SHEET("Лист3"),
    FORTH_SHEET("Лист4"),
    FIFTH_SHEET("Лист5");

    private final String sheet;

    ExcelPageNumber(String sheet){
        this.sheet = sheet;
    }

    public String getSheet() {
        return sheet;
    }

}
