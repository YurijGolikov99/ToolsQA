package selenium.ui_module.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.common_module.driver.EasyInicialisation.BaseSeleniumPage;
import selenium.common_module.property.PropertyHelper;

public class MainPageFindBY extends BaseSeleniumPage {

    protected final Logger logger = LogManager.getRootLogger();
    private WebDriver driver;
    //указали путь до элементов
    @FindBy(xpath = "(//div[@class='card mt-4 top-card'])[1]")
    private WebElement elementsButton;
    @FindBy(xpath = "(//div[@class='card mt-4 top-card'])[2]")
    private WebElement formsButton;
    @FindBy(xpath =  "(//div[@class='card mt-4 top-card'])[3]")
    private WebElement alertFrameWindowsButton;
    @FindBy(xpath =  "(//div[@class='card mt-4 top-card'])[4]")
    private WebElement widgetsButton;
    @FindBy(xpath =  "(//div[@class='card mt-4 top-card'])[5]")
    private WebElement interactionsButton;
    @FindBy(xpath =  "(//div[@class='card mt-4 top-card'])[6]")
    private WebElement bookStoreApplicationButton;

    public MainPageFindBY(){
        this.driver = BaseSeleniumPage.driver;
        logger.info("Open page");
        driver.get(PropertyHelper.getProperty("base.url"));
        PageFactory.initElements(driver, this);
    }

    public MainPageFindBY openBookStore(){
        bookStoreApplicationButton.click();
        return this;
    }
}
