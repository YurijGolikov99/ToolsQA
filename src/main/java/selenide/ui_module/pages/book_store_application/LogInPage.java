package selenide.ui_module.pages.book_store_application;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;


/**
 *6.Элементы страницы LogIn
 */
public class LogInPage {

    private final SelenideElement userNameField = $x("//input[@id='userName']");
    private final SelenideElement passwordField = $x("//input[@id='password']");
    private final SelenideElement loginButton = $x("//");
    private final SelenideElement newUserButton = $x("//");

    public LogInPage(String url) {
        Selenide.open(url);
    }

}
