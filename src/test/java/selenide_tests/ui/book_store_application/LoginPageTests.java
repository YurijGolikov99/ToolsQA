package selenide_tests.ui.book_store_application;

import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import selenide.common_module.data.Credentials;
import selenide.ui_module.constants.UiEndpoints;
import selenide.ui_module.pages.MainPage;
import selenide.ui_module.steps.MainPageSteps;
import selenide.ui_module.steps.book_store_application.BookStoreCommonStepsUI;
import selenide.ui_module.steps.book_store_application.LoginPageSteps;
import selenide_tests.BaseTest;


public class LoginPageTests extends BaseTest {

    private final MainPage mainPage = new MainPage();
    private final MainPageSteps mainPageSteps = new MainPageSteps();
    private final BookStoreCommonStepsUI bookStoreCommonStepsUI = new BookStoreCommonStepsUI();
    private final LoginPageSteps loginPageSteps = new LoginPageSteps();

    @DisplayName("регистрация с валидными данными")
    @Description("Открываем страницу, переходим в книжный, открываем логин пейдж и логинимся")
    @AllureId("7")
    @Issue("IDF-T1")
    @Tags({@Tag("UI"), @Tag("IM_SERVICE"), @Tag("SMOKE")})
    @Epic("Книжный магазин")
//    @Service(AllureServiceConstants.IM_MONITORING_SERVICE)
//    @Layer(AllureLayer.SYSTEM_TESTS)
    @Owner("Юра Голиков")
    @Test
    public void testAuthorisationWithValidDate(){
        mainPage.openPage(UiEndpoints.BASE_URL.getUrl());
        mainPageSteps.clickBookStoreApplication();
        bookStoreCommonStepsUI.openLoginPage();
        loginPageSteps.authorisation(Credentials.USER_LOGIN.getProperty(), Credentials.USER_PASSWORD.getProperty());
    }
}
