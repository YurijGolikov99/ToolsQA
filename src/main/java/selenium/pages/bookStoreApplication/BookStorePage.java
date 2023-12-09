package selenium.pages.bookStoreApplication;

import selenium.driver.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.LoginPage;

public class BookStorePage extends BaseSeleniumPage {
    @FindBy(xpath = "//button[@id=\"login\"]")
    private  WebElement loginButton;
    public BookStorePage() {
        driver.get("https://demoqa.com/books");
        PageFactory.initElements(driver, this);
    }
    public LoginPage openLoginPage(){
        loginButton.click();
        return new LoginPage();
    }
}