package selenide_tests.api.book_store_application;

import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import selenide.api_module.constants.ApiEndpoints;
import selenide.api_module.steps.book_store_application.BookStoreAuthorisationSteps;
import selenide.api_module.steps.book_store_application.BookStoreRegistrationSteps;
import selenide.api_module.utils.rest.ApiResponseChecker;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookStoreRegistrationTests {

    private BookStoreRegistrationSteps bookStoreRegistrationSteps = new BookStoreRegistrationSteps();
    private BookStoreAuthorisationSteps bookStoreAuthorisationSteps = new BookStoreAuthorisationSteps();
    private ApiResponseChecker apiResponseChecker = new ApiResponseChecker();

    //перед повторным запуском, стоит удалить пользователя
    @DisplayName("Успешная регистрация с валидными данными")
    @Description("Отправили ендпоинт с телом из двух строк")
    @AllureId("1")
    @Issue("BooStSelReg-52824")
    @Tags({@Tag("API"), @Tag("BOOK_STORE"), @Tag("SMOKE")})
    @Epic("Книжный магазин")
    @Owner("Юра Голиков")
    @Test
    @Order(1)
    public void testSuccessRegistrationWithValidDate(){
        bookStoreRegistrationSteps.enterValidDataDuringRegistration();
    }

    @DisplayName("Регистрация с невалидными данными")
    @Description("Отправили ендпоинт с телом из двух одинаковых строк")
    @AllureId("3")
    @Issue("BooStSelReg-52824")
    @Tags({@Tag("API"), @Tag("BOOK_STORE"), @Tag("SMOKE")})
    @Epic("Книжный магазин")
    @Owner("Юра Голиков")
    @Test
    @Order(2)
    public void testUnSuccessRegistration(){
        bookStoreRegistrationSteps.enterInvalidDataDuringRegistration();
    }

    @DisplayName("Генерация токена")
    @Description("Токен генерируется после регистрации")
    @AllureId("4")
    @Issue("BooStSelReg-52824")
    @Tags({@Tag("API"), @Tag("BOOK_STORE"), @Tag("SMOKE")})
    @Epic("Книжный магазин")
    @Owner("Юра Голиков")
    @Test
    @Order(3)
    public void testGenerateToken(){
        bookStoreAuthorisationSteps.generateUserToken();
    }

    @DisplayName("Залогиниться с валидными данными")
    @Description("Регистрация пользователя проходит успешно с валидными данными")
    @AllureId("5")
    @Issue("BooStSelReg-52824")
    @Tags({@Tag("API"), @Tag("BOOK_STORE"), @Tag("SMOKE")})
    @Epic("Книжный магазин")
    @Owner("Юра Голиков")
    @Test
    @Order(4)
    public void testLoginWithValidDate(){
        bookStoreAuthorisationSteps.validAuthorisation();
    }

    @DisplayName("Удалить пользователя")
    @Description("Удаляем пользователя и все его данные")
    @AllureId("6")
    @Issue("BooStSelReg-52824")
    @Tags({@Tag("API"), @Tag("BOOK_STORE"), @Tag("SMOKE")})
    @Epic("Книжный магазин")
    @Owner("Юра Голиков")
    @Test
    @Order(5)
    public void testDeleteUsers(){
        bookStoreRegistrationSteps.deleteUser();
    }

    //дубль такого же теста как и выше
    @DisplayName("Успешная регистрация с валидными данными")
    @Description("Отправили ендпоинт с телом из двух строк но уже с использованием спецификаций")
    @AllureId("2")
    @Issue("BooStSelReg-52824")
    @Tags({@Tag("API"), @Tag("BOOK_STORE"), @Tag("SMOKE")})
    @Epic("Книжный магазин")
    @Owner("Юра Голиков")
    @Test
    @Order(6)
    public void testSuccessRegistrationWithValidDate2(){
        bookStoreRegistrationSteps.enterValidDataDuringRegistrationWithSpec();
    }

    @DisplayName("Успешная регистрация с валидными данными")
    @Description("Отправили ендпоинт с телом из двух строк но уже с использованием спецификаций")
    @AllureId("2")
    @Issue("BooStSelReg-52824")
    @Tags({@Tag("API"), @Tag("BOOK_STORE"), @Tag("SMOKE")})
    @Epic("Книжный магазин")
    @Owner("Юра Голиков")
    @Test
    @Disabled //используется для отключения теста, т.е. чтобы он не выполнялся при запуске тестов.
    public void testSuccessRegistrationWithValidDate3(){
        //сам тест не отрабатывает должным образом, так как передаем пост юрл а надо для гета
        Response response = apiResponseChecker.httpGetRequestWithQueryParameters(
                ApiEndpoints.getAuthorisationUrl(), null, List.of());
        apiResponseChecker.checkForStatusCodeEquivalence(response, 200);
        apiResponseChecker.checkThatResponseBodyIsNotEmpty(response);
    }
}
