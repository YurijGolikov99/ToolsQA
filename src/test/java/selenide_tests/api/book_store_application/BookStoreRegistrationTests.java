package selenide_tests.api.book_store_application;

import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import selenide.api_module.steps.book_store_application.BookStoreAuthorisationSteps;
import selenide.api_module.steps.book_store_application.BookStoreRegistrationSteps;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookStoreRegistrationTests {

    private final BookStoreRegistrationSteps bookStoreRegistrationSteps = new BookStoreRegistrationSteps();
    private final BookStoreAuthorisationSteps bookStoreAuthorisationSteps = new BookStoreAuthorisationSteps();

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

    @DisplayName("")
    @Description("")
    @AllureId("3")
    @Issue("")
    @Tags({@Tag("API"), @Tag("BOOK_STORE"), @Tag("SMOKE")})
    @Epic("Книжный магазин")
    @Owner("Юра Голиков")
    @Test
    @Order(2)
    public void testUnSuccessRegistration(){
        bookStoreRegistrationSteps.enterInvalidDataDuringRegistration();
    }

    @DisplayName("")
    @Description("")
    @AllureId("4")
    @Issue("")
    @Tags({@Tag("API"), @Tag("BOOK_STORE"), @Tag("SMOKE")})
    @Epic("Книжный магазин")
    @Owner("Юра Голиков")
    @Test
    @Order(3)
    public void testGenerateToken(){
        bookStoreAuthorisationSteps.generateUserToken();
    }

    @DisplayName("")
    @Description("")
    @AllureId("5")
    @Issue("")
    @Tags({@Tag("API"), @Tag("BOOK_STORE"), @Tag("SMOKE")})
    @Epic("Книжный магазин")
    @Owner("Юра Голиков")
    @Test
    @Order(4)
    public void testLoginWithValidDate(){
        bookStoreAuthorisationSteps.validAuthorisation();
    }

    @DisplayName("")
    @Description("")
    @AllureId("6")
    @Issue("")
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
    @Description("Отправили ендпоинт с телом из двух строк")
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
}
