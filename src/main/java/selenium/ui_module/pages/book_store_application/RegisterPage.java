package selenium.ui_module.pages.book_store_application;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

// TODO переделать под селениум,
//  так как здесь всё в корне на селениде держится
/**
 *5.Элементы страницы Register
 */
public class RegisterPage {

    public final SelenideElement firstNameField = $x("//");
    public final SelenideElement lastNameField = $x("//");
    public final SelenideElement userNameField = $x("//");
    public final SelenideElement passwordField = $x("//");

}
