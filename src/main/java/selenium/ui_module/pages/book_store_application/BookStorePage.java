package selenium.ui_module.pages.book_store_application;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.common_module.driver.BaseSeleniumPage;
import selenium.ui_module.pages.LoginPage;

public class BookStorePage extends BaseSeleniumPage {

    @FindBy(xpath = "//button[@id=\"login\"]")
    private WebElement loginButton;
    @FindBy(xpath = "//span/a")
    private WebElement bookTitles;


    public BookStorePage() {
        driver.get("https://demoqa.com/books");
        PageFactory.initElements(driver, this);
    }

    public LoginPage openLoginPage(){
        loginButton.click();
        return new LoginPage();
    }

    /*
    private WebDriver driver;

    public BookStorePage(String url){
        this.driver = DriverProvider.getDriver();
        this.driver.get(url);
        PageFactory.initElements(driver, this);
    }

    private final WebElement gitPocketGuideBookTitle = (By.xpath("//span[@id='see-book-Git Pocket Guide']"));

     */
}
