package selenium.ui_module.pages.book_store_application;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.ui_module.pages.BasePage;

public class BookStorePageBy extends BasePage  {

    private WebDriverWait wait;

    private final By loginButton = By.xpath("//button[@id=\"login\"]");
    private final By gitPocketGuideBookTitle = By.xpath("//span[@id='see-book-Git Pocket Guide']");
    private final By learningJavaScriptDesignTitle = By.xpath("//span[@id='see-book-Learning JavaScript Design Patterns']");
    private final By designingEvolvableWebAPIsTitle = By.xpath("//span[@id='see-book-Designing Evolvable Web APIs with ASP.NET']");
    private final By speakingJavaScriptTitle = By.xpath("//span[@id='see-book-Speaking JavaScript']");
    //дичь, которая не ищет эллемент с одинарными кавычками)))
    private final By youDontKnowJSTitle = By.xpath("//span[@id=\"see-book-You Don't Know JS\"]");
    private final By programmingJavaScriptApplicationsTitle = By.xpath("//span[@id='see-book-Programming JavaScript Applications']");
    private final By eloquentJavaScriptSecondEditionTitle = By.xpath("//span[@id='see-book-Eloquent JavaScript, Second Edition']");
    private final By understandingECMAScriptTitle = By.xpath("//span[@id='see-book-Understanding ECMAScript 6']");

    public BookStorePageBy(WebDriver driver) {
        super(driver);
    }

    public WebElement getLoginButton() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

    public String getGitPocketGuideTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(gitPocketGuideBookTitle)).getText();
    }

    public String getLearningJavaScriptDesignTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(learningJavaScriptDesignTitle)).getText();
    }

    public String getDesigningEvolvableWebAPIsTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(designingEvolvableWebAPIsTitle)).getText();
    }

    public String getSpeakingJavaScriptTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(speakingJavaScriptTitle)).getText();
    }

    public String getYouDontKnowJSTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(youDontKnowJSTitle)).getText();
    }

    public String getProgrammingJavaScriptApplicationsTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(programmingJavaScriptApplicationsTitle)).getText();
    }

    public String getEloquentJavaScriptSecondEditionTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(eloquentJavaScriptSecondEditionTitle)).getText();
    }

    public String getUnderstandingECMAScriptTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(understandingECMAScriptTitle)).getText();
    }
}
