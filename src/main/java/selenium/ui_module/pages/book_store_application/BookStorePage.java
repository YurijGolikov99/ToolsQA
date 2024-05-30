package selenium.ui_module.pages.book_store_application;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

// TODO переделать под селениум,
//  так как здесь всё в корне на селениде держится
/**
 *4. Элементы страницы bookStoreApplication
 */
public class BookStorePage  {

    private final SelenideElement loginButton = $x("//button[@id=\"login\"]");
    private final SelenideElement gitPocketGuideBookTitle = $x("//span[@id='see-book-Git Pocket Guide']");
    private final SelenideElement learningJavaScriptDesigTitle = $x("//span[@id='see-book-Learning JavaScript Design Patterns']");
    private final SelenideElement designingEvolvableWebAPIsTitle = $x("//span[@id='see-book-Designing Evolvable Web APIs with ASP.NET']");
    private final SelenideElement speakingJavaScriptTitle = $x("//span[@id='see-book-Speaking JavaScript']");
    //дичь, которая не ищет эллемент с одинарными кавычками)))
    private final SelenideElement youDontKnowJSTitle = $x("//span[@id=\"see-book-You Don't Know JS\"]");
    private final SelenideElement programmingJavaScriptApplicationsTitle = $x("//span[@id='see-book-Programming JavaScript Applications']");
    private final SelenideElement eloquentJavaScriptSecondEditionTitle = $x("//span[@id='see-book-Eloquent JavaScript, Second Edition']");
    private final SelenideElement understandingECMAScriptTitle = $x("//span[@id='see-book-Understanding ECMAScript 6']");

    public void openPage(String url) {
        Selenide.open(url);
    }

    public SelenideElement getLoginButton(){
        return loginButton;
    }

    public String getGitPocketGuideTitle(){
        return gitPocketGuideBookTitle.getText();//equals("Git Pocket Guide") ? "Right book!" : "Wrong book!";
    }

    public String getLearningJavaScriptDesignTitle() {
        return learningJavaScriptDesigTitle.getText();
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
