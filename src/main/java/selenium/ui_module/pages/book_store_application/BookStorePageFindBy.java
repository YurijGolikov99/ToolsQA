package selenium.ui_module.pages.book_store_application;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenide.common_module.driver.easy_initialization.BaseSeleniumPage;
import selenide.ui_module.pages.book_store_application.LoginPage;


// TODO переделать под selenide
public class BookStorePageFindBy extends BaseSeleniumPage {

    @FindBy(xpath = "//button[@id=\"login\"]")
    private WebElement loginButton;
    @FindBy(xpath = "//span/a")
    private WebElement bookTitles;


    public BookStorePageFindBy() {
        PageFactory.initElements(driver, this);
    }

    public WebElement getLoginButton(){
        return loginButton;
    }

    public WebElement getBookTitles(){
        return bookTitles;
    }

    public LoginPage openLoginPage(){
        loginButton.click();
        return new LoginPage();
    }
}
