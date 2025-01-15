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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import selenide.common_module.data.Credentials;
import selenide.common_module.data.UserCredentials;
import selenide.ui_module.constants.BookStoreUiEndpoints;
import selenide.ui_module.pages.MainPage;
import selenide.ui_module.steps.MainPageSteps;
import selenide.ui_module.steps.book_store_application.BookStoreCommonStepsUI;
import selenide.ui_module.steps.book_store_application.LoginPageSteps;
import selenide_tests.BaseTest;

import java.util.stream.Stream;

public class LoginPageTests extends BaseTest {

    private final MainPage mainPage = new MainPage();
    private final MainPageSteps mainPageSteps = new MainPageSteps();
    private final BookStoreCommonStepsUI bookStoreCommonStepsUI = new BookStoreCommonStepsUI();
    private final LoginPageSteps loginPageSteps = new LoginPageSteps();

    @DisplayName("регистрация с валидными данными")
    @Description("Открываем страницу, переходим в книжный, открываем логин пейдж и логинимся")
    @AllureId("7")
    @Issue("IDF-T1")
    @Tags({@Tag("UI"), @Tag("SMOKE")})
    @Epic("Книжный магазин")
//    @Service(AllureServiceConstants.IM_MONITORING_SERVICE)
//    @Layer(AllureLayer.SYSTEM_TESTS)
    @Owner("Юра Голиков")
    @Test
    public void testAuthorisationWithValidDate(){
        mainPage.openPage(BookStoreUiEndpoints.BASE.getUrl());
        mainPageSteps.clickBookStoreApplication();
        bookStoreCommonStepsUI.openLoginPage();
        loginPageSteps.authorisation(
                UserCredentials.YURIJ.getUsername(),
                UserCredentials.YURIJ.getPassword());
    }

    //такой же что и выше но параметризованный тест
    @DisplayName("регистрация с валидными данными")
    @Description("Открываем страницу, переходим в книжный, открываем логин пейдж и логинимся")
    @AllureId("8")
    @Issue("IDF-T1")
    @Epic("Книжный магазин")
    @Owner("Юра Голиков")
    @MethodSource("credentials")
    @ParameterizedTest
    public void testAuthorisationWithValidDate(String login, String password){
        mainPage.openPage(BookStoreUiEndpoints.BASE.getUrl());
        mainPageSteps.clickBookStoreApplication();
        bookStoreCommonStepsUI.openLoginPage();
        loginPageSteps.authorisation(login, password);
    }

    private static Stream<Arguments> credentials(){
        return Stream.of(
                Arguments.of(Credentials.TEST_USERNAME, Credentials.TEST_PASSWORD),
                Arguments.of(UserCredentials.YURIJ.getUsername(), UserCredentials.YURIJ.getPassword())
        );
    }
}
