package selenide.ui_module.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
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


    public MainPage(String url){
        Selenide.open(url);
    }

    //тоже самое что и выше
    public void openWebSite(String url){
        Selenide.open(url);
    }

    public void clickOnElementsButton(){
        elementsButton.click();
    }

    public void clickOnFormsButton(){
        formsButton.click();
    }

    public void clickOnAlertFrameWindowsButton(){
        alertFrameWindowsButton.click();
    }

    public void clickOnWidgetsButton(){
        widgetsButton.click();
    }

    public void clickOnInteractionsButton(){
        interactionsButton.click();
    }

    public void clickOnBookStoreApplication(){
        bookStoreApplicationButton.click();
    }
}
