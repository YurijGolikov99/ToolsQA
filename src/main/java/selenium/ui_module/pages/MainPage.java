package selenium.ui_module.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.common_module.driver.BaseSeleniumPage;

public class MainPage extends BaseSeleniumPage {

    //указали путь до элементов
    private final By elementsButton = By.xpath("(//div[@class='card mt-4 top-card'])[1]");
    private final By formsButton = By.xpath("(//div[@class='card mt-4 top-card'])[2]");
    private final By alertFrameWindowsButton = By.xpath("(//div[@class='card mt-4 top-card'])[3]");
    private final By widgetsButton = By.xpath("(//div[@class='card mt-4 top-card'])[4]");
    private final By interactionsButton = By.xpath("(//div[@class='card mt-4 top-card'])[5]");
    private final By bookStoreApplicationButton = By.xpath("(//div[@class='card mt-4 top-card'])[6]");

    //поиск элементом по пути выше но он сразу будет их искать
    private WebElement elementsButtonElement = driver.findElement(elementsButton);
    private WebElement formsButtonElement = driver.findElement(formsButton);
    private WebElement alertFrameWindowsButtonElement = driver.findElement(alertFrameWindowsButton);
    private WebElement widgetsButtonElement = driver.findElement(widgetsButton);
    private WebElement interactionsButtonElement = driver.findElement(interactionsButton);
    private WebElement bookStoreApplicationButtonElement = driver.findElement(bookStoreApplicationButton);
}
