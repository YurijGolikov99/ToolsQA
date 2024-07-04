package selenide.ui_module.pages.elements;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class TextBoxPage {

    private final SelenideElement fullNameField = $(By.id("userName"));
    private final SelenideElement emailField = $(By.id("userEmail"));
    private final SelenideElement currentAddressField = $(By.id("currentAddress"));
    private final SelenideElement permanentAddressField = $(By.id("permanentAddress"));
    private final SelenideElement submitButton = $(By.id("submit"));

    public void openPage(String url) {
        Selenide.open(url);
    }

    public SelenideElement getFullNameField(){
        return fullNameField;
    }

    public SelenideElement getEmailField(){
        return emailField;
    }

    public SelenideElement getCurrentAddressField(){
        return currentAddressField;
    }

    public SelenideElement getPermanentAddressField(){
        return permanentAddressField;
    }

    public SelenideElement getSubmitButton(){
        return submitButton;
    }
}
