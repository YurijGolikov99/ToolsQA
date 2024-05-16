package selenide.ui_module.pages.book_store_application;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;
import selenide.ui_module.pages.BasePage;

import static com.codeborne.selenide.Selenide.$x;


/**
 *6.Элементы страницы LogIn
 */
public class LogInPage extends BasePage {

    private final SelenideElement userNameField = $x("//input[@id='userName']");
    private final SelenideElement passwordField = $x("//input[@id='password']");
    private final SelenideElement loginButton = $x("//");
    private final SelenideElement newUserButton = $x("//");

    public LogInPage(WebDriver driver) {
        super(driver);
    }
}
