package selenide.ui_module.steps.elements;

import io.qameta.allure.Step;
import selenide.ui_module.pages.elements.TextBoxPage;

import static com.codeborne.selenide.Selenide.actions;

public class TextBoxSteps {

    private static TextBoxPage textBoxPage = new TextBoxPage();

    @Step("Заполнить поле Full Name")
    public void fillFullNameField(){
        textBoxPage.getFullNameField().click();
        textBoxPage.getFullNameField().sendKeys("1");
    }

    @Step("Заполнить поле Email")
    public void fillEmailField(){
        textBoxPage.getEmailField().click();
        textBoxPage.getEmailField().sendKeys("test@mail.ru");
    }

    @Step("Заполнить поле Current Address")
    public void fillCurrentAddressField(){
        textBoxPage.getCurrentAddressField().scrollTo();
        textBoxPage.getCurrentAddressField().click();
        textBoxPage.getCurrentAddressField().sendKeys("3");
    }

    @Step("Уменьшили размер поля Current Address")
    public void dragCurrentAddressField(){
        actions().dragAndDropBy(textBoxPage.getCurrentAddressField(), 0, -20).perform();
    }

    @Step("Заполнить поле Permanent Address")
    public void fillPermanentAddressField(){
        textBoxPage.getPermanentAddressField().scrollTo();
        textBoxPage.getPermanentAddressField().click();
        textBoxPage.getPermanentAddressField().sendKeys("4");
    }

    @Step("Уменьшили размер поля Permanent Address")
    public void dragPermanentAddressField(){
        actions().dragAndDropBy(textBoxPage.getPermanentAddressField(), 0, 20).perform();
    }

    @Step("Нажать на кнопку подтвердить")
    public void clickSubmitButton(){
        textBoxPage.getSubmitButton().click();
    }
}
