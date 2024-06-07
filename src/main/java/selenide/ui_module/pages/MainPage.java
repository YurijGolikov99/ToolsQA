package selenide.ui_module.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

/**
 * 2.Эллементы главной страница Demoqa.com
 */
public class MainPage {

    private final SelenideElement elementsButton = $x("(//div[@class='card mt-4 top-card'])[1]");
    private final SelenideElement formsButton = $x("(//div[@class='card mt-4 top-card'])[2]");
    private final SelenideElement alertFrameWindowsButton = $x("(//div[@class='card mt-4 top-card'])[3]");
    private final SelenideElement widgetsButton = $x("(//div[@class='card mt-4 top-card'])[4]");
    private final SelenideElement interactionsButton = $x("(//div[@class='card mt-4 top-card'])[5]");
    private final SelenideElement bookStoreApplicationButton = $x("(//div[@class='card mt-4 top-card'])[6]");

    //тоже самое, что и выше
    @Step("Открыть главную страницу")
    public void openPage(String url){
        Selenide.open(url);
    }

    // Геттеры для элементов страницы
    public SelenideElement getElementsButton(){
        return elementsButton;
    }

    public SelenideElement getFormsButton(){
        return formsButton;
    }

    public SelenideElement getAlertFrameWindowsButton(){
        return alertFrameWindowsButton;
    }

    public SelenideElement getWidgetsButton(){
        return widgetsButton;
    }

    public SelenideElement getInteractionsButton(){
        return interactionsButton;
    }

    public SelenideElement getBookStoreApplicationButton(){
        return bookStoreApplicationButton.scrollTo();
    }
}
