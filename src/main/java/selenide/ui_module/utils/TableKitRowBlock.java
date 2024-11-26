package selenide.ui_module.utils;

import com.codeborne.selenide.SelenideElement;

public class TableKitRowBlock {

    private final SelenideElement rowElement;

    // Конструктор, который принимает элемент строки таблицы
    public TableKitRowBlock(SelenideElement rowElement) {
        this.rowElement = rowElement;
    }

    // Метод для получения текста в ячейке, например, имени сотрудника
    public String getEmployeeName() {
        return rowElement.$(".employee-name").getText();  // .employee-name - класс ячейки с именем сотрудника
    }

    // Метод для получения группы поддержки
    public String getSupportGroup() {
        return rowElement.$(".support-group").getText();  // .support-group - класс ячейки с группой поддержки
    }

    // Метод для установки значения в поле сотрудника
    public void setEmployee(String employeeName) {
        SelenideElement employeeField = rowElement.$(".employee-name");
        employeeField.clear();
        employeeField.setValue(employeeName);
    }

    // Метод для установки значения в поле группы поддержки
    public void setSupportGroup(String supportGroup) {
        SelenideElement supportGroupField = rowElement.$(".support-group");
        supportGroupField.clear();
        supportGroupField.setValue(supportGroup);
    }

    // Метод для получения состояния кнопки "Сохранить"
    public boolean isSaveButtonEnabled() {
        return rowElement.$(".save-button").isEnabled();  // .save-button - класс кнопки сохранения
    }

    // Метод для нажатия на кнопку "Сохранить"
    public void clickSaveButton() {
        rowElement.$(".save-button").click();
    }

    // Метод для нажатия на кнопку "Удалить"
    public void clickDeleteButton() {
        rowElement.$(".delete-button").click();  // .delete-button - класс кнопки удаления
    }

    // Метод для проверки, что строка видима
    public void scrollToTheRow() {
        rowElement.scrollIntoView(true);
    }

    // Дополнительные методы для других ячеек или действий можно добавить ниже
}
