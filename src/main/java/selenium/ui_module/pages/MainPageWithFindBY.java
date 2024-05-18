package selenium.ui_module.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.common_module.driver.BaseSeleniumPage;
import selenium.common_module.property.PropertyHelper;

public class MainPageWithFindBY extends BaseSeleniumPage {

    protected final Logger logger = LogManager.getRootLogger();

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


    public MainPageWithFindBY(){
        logger.info("Open page");
        driver.get(PropertyHelper.getProperty("base.url"));
        PageFactory.initElements(driver, this);
    }

    public MainPageWithFindBY openBookStore(){
        bookStoreApplicationButton.click();
        return this;
    }
}
