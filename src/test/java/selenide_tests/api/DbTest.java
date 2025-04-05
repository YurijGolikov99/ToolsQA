package selenide_tests.api;

import io.qameta.allure.Allure;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.dao.DataIntegrityViolationException;
import selenide.common_module.data.db_name_repository.TableNameRepository;
import selenide.common_module.utils.TestExecutionUtils;

import java.util.UUID;
import java.util.stream.Stream;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DbTest {

    TestExecutionUtils testExecutionUtils;
    private static TableNameRepository tableNameRepository;

    /**
     * Проверка выполнения SQL-запроса.
     */
    @ParameterizedTest(name = "Проверка параметра {0}")
    @MethodSource("noErrorMessageDataProvider")
    @Owner("uagolikov")
    public void tableFieldsNoErrorMessageTest(String testName, String tmsLink, Runnable cleanTask, String sql) {
        Allure.tms(tmsLink, "https://sfera.inno.local/testing/project/CCOP/test-issue/" + tmsLink);
        testExecutionUtils.testWithCleaner(() ->
                step("Выполнить SQL-запрос и убедиться в отсутствии исключения",
                        () -> tableNameRepository.execute(sql)), cleanTask);
    }

    public static Stream<Arguments> noErrorMessageDataProvider() {
        UUID randomUuid = UUID.randomUUID();
        return Stream.of(
                Arguments.of("param_name на обязательность в таблице table_name", //название тест-кейса
                        "test-case-numb", // //номер тест-кейса
                        (Runnable) () -> tableNameRepository.deleteByUuid(randomUuid), //чистим бд
                        String.format("""
                                INSERT INTO ms_bd_name.table_name
                                (column1, column2, column3, column4, column5, column6, column7, column8)
                                VALUES ('variable_uuid', 'variable_data', 'variable_long', 'variable_string', '%s', 'variable_date_time', 'variable_date_time2', variable_null);
                                """, //sql запрос делаем
                                randomUuid)), //передали рандомный uuid вместо %s
                Arguments.of("param_name2 на обязательность в таблице table_name", "test-case-numb",  (Runnable) () -> tableNameRepository.deleteByUuid(randomUuid),
                        String.format("""
                                INSERT INTO ms_bd_name.table_name
                                (column1, column2, column3, column4, column5, column6, column7, column8)
                                VALUES ('%s', 'variable_data', 'variable_long', 'variable_string', 'variable_uuid', 'variable_date_time', 'variable_date_time2', variable_null);
                                """, randomUuid))
        );
    }


    /**
     * Проверка SQL-запроса с ожиданием исключения и определённого сообщения об ошибке.
     */
    @ParameterizedTest(name = "Проверка параметра {0}")
    @MethodSource("errorMessageDataProvider")
    @Owner("uagolikov")
    public void tableFieldsWithErrorMessageTest(String testName, String tmsLink, String errorMessage, Runnable cleanTask, String sql) {
        Allure.tms(tmsLink, "https://sfera.inno.local/testing/project/CCOP/test-issue/" + tmsLink);
        testExecutionUtils.testWithCleaner(() ->
                step("Выполнить SQL-запрос и убедиться в возникновении исключения с ожидаемым сообщением - " + errorMessage, () -> {
                    assertThatThrownBy(() -> tableNameRepository.execute(sql))
                            .isInstanceOf(DataIntegrityViolationException.class)
                            .hasMessageContaining(errorMessage);
                }), cleanTask);
    }

    private Stream<Arguments> errorMessageDataProvider() {
        return Stream.of(
                Arguments.of("param_name на обязательность в таблице table_name", //название тест-кейса
                        "test-case-numb", //номер тест-кейса
                        "null \"value\" in column param_name of relation \"table_name\" violates not-null constraint", //сообщение об ошибке
                        null, //нет зачистки
                        """
                            INSERT INTO ms_ppcd_product.general_agreement_register
                            (id, register_id, general_agreement_id, general_agreement_register_status, created_at, updated_at)
                            VALUES(NULL, '1f85d777-4741-4d77-9b67-e22828c3e19b', '633757e4-a548-4ee7-9b04-e4eec9fc3018', 'OPEN', '2024-08-02 12:38:29.815', '2024-08-02 12:38:36.431');
                            """) //sql запрос

        );
    }
}
