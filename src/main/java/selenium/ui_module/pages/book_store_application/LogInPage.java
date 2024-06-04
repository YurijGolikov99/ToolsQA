package selenium.ui_module.pages.book_store_application;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.ui_module.pages.BasePage;

import java.time.Duration;

/**
 *6.Элементы страницы LogIn
 */
public class LogInPage extends BasePage {

    private final WebDriverWait wait;

    private final By userNameField = By.xpath("//input[@id='userName']");
    private final By passwordField = By.xpath("//input[@id='password']");
    private final By loginButton = By.xpath("//button[@id=\"login\"]");
    private final By newUserButton = By.xpath("//button[@id='newUser']");

    public LogInPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openPage(String url) {
        driver.get(url);
    }

    public WebElement getUserNameField(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(userNameField));
    }

    public WebElement getPasswordField(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
    }

    public WebElement getLoginButton(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

    public WebElement getNewUserButton(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(newUserButton));
    }
}
