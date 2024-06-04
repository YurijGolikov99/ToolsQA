package selenium.ui_module.pages.book_store_application;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.ui_module.pages.BasePage;

/**
 * 4. Элементы страницы bookStoreApplication
 */
public class BookStorePage extends BasePage {

    @FindBy(xpath = "//button[@id=\"login\"]")
    private WebElement loginButton;
    @FindBy(xpath = "//span[@id='see-book-Git Pocket Guide']")
    private WebElement gitPocketGuideBookTitle;
    @FindBy(xpath = "//span[@id='see-book-Learning JavaScript Design Patterns']")
    private WebElement learningJavaScriptDesignTitle;
    @FindBy(xpath = "//span[@id='see-book-Designing Evolvable Web APIs with ASP.NET']")
    private WebElement designingEvolvableWebAPIsTitle;
    @FindBy(xpath = "//span[@id='see-book-Speaking JavaScript']")
    private WebElement speakingJavaScriptTitle;
    //дичь, которая не ищет элемент с одинарными кавычками)))
    @FindBy(xpath = "//span[@id=\"see-book-You Don't Know JS\"]")
    private WebElement youDontKnowJSTitle;
    @FindBy(xpath = "//span[@id='see-book-Programming JavaScript Applications']")
    private WebElement programmingJavaScriptApplicationsTitle;
    @FindBy(xpath = "//span[@id='see-book-Eloquent JavaScript, Second Edition']")
    private WebElement eloquentJavaScriptSecondEditionTitle;
    @FindBy(xpath = "//span[@id='see-book-Understanding ECMAScript 6']")
    private WebElement understandingECMAScriptTitle;

    public BookStorePage(WebDriver driver) {
        super(driver);
    }

    public void openPage(String url) {
        driver.get(url);
    }

    public WebElement getLoginButton(){
        return loginButton;
    }

    public String getGitPocketGuideTitle(){
        return gitPocketGuideBookTitle.getText();//equals("Git Pocket Guide") ? "Right book!" : "Wrong book!";
    }

    public String getLearningJavaScriptDesignTitle() {
        return learningJavaScriptDesignTitle.getText();
    }

    public String getDesigningEvolvableWebAPIsTitle() {
        return designingEvolvableWebAPIsTitle.getText();
    }

    public String getSpeakingJavaScriptTitle() {
        return speakingJavaScriptTitle.getText();
    }

    public String getYouDontKnowJSTitle() {
        return youDontKnowJSTitle.getText();
    }

    public String getProgrammingJavaScriptApplicationsTitle() {
        return programmingJavaScriptApplicationsTitle.getText();
    }

    public String getEloquentJavaScriptSecondEditionTitle() {
        return eloquentJavaScriptSecondEditionTitle.getText();
    }

    public String getUnderstandingECMAScriptTitle() {
        return understandingECMAScriptTitle.getText();
    }
}
