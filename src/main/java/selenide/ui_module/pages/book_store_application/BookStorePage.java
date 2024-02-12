package selenide.ui_module.pages.book_store_application;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

/**
 *4. Элементы страницы bookStoreApplication
 */

public class BookStorePage {
    private final SelenideElement gitPocketGuideBookTitle = $x("//span[@id='see-book-Git Pocket Guide']");
    private final SelenideElement learningJavaScriptDesigTitle = $x("//span[@id='see-book-Learning JavaScript Design Patterns']");
    private final SelenideElement designingEvolvableWebAPIsTitle = $x("//span[@id='see-book-Designing Evolvable Web APIs with ASP.NET']");
    private final SelenideElement speakingJavaScriptTitle = $x("//span[@id='see-book-Speaking JavaScript']");
    //дичь, которая не ищет эллемент с одинарными кавычками)))
    private final SelenideElement youDontKnowJSTitle = $x("//span[@id=\"see-book-You Don't Know JS\"]");
    private final SelenideElement programmingJavaScriptApplicationsTitle = $x("//span[@id='see-book-Programming JavaScript Applications']");
    private final SelenideElement eloquentJavaScriptSecondEditionTitle = $x("//span[@id='see-book-Eloquent JavaScript, Second Edition']");
    private final SelenideElement understandingECMAScriptTitle = $x("//span[@id='see-book-Understanding ECMAScript 6']");

    public BookStorePage(String url) {
        Selenide.open(url);
    }

//    public String getGitPocketGuideEasy(){
//        String bookName = gitPocketGuideBookTitle.getText();
//        if ("Git Pocket Guide".equals(bookName)) {
//            return "Right book!";
//        }
//        return "Wrong book!";
//    }
//    или более сложным способом через тернарный оператор

    public String getGitPocketGuide(){
        return gitPocketGuideBookTitle.getText();//equals("Git Pocket Guide") ? "Right book!" : "Wrong book!";
    }

    public String getLearningJavaScriptDesigTitle() {
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
