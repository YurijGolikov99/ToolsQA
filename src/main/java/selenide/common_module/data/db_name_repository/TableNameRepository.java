package selenide.common_module.data.db_name_repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

//методы для работы с таблицами в бд
@Repository
public class TableNameRepository {

    @Autowired
    @Qualifier("mcDbNameClient")
    private JdbcClient jdbcClient;

    //проверка, что таблица пустая
    public boolean isTableEmpty() {
        List<Map<String, Object>> rows = jdbcClient
                .sql("""
                        SELECT *
                        FROM ms_ppcd_datahub.stagedb_kafka_ul_client_info
                        LIMIT 1
                        """)
                .query()
                .listOfRows(); // Извлекаем список строк из таблицы
        return rows.isEmpty(); // Если список пуст, значит таблица пуста
    }

    //удаление записи в бд при поиске по UUID
    public List<Map<String, Object>> findAllByUUID(UUID uuid) {
        return jdbcClient
                .sql("""
                        SELECT *
                        FROM db_name.table_name
                        WHERE column_name = :uuid
                        """)
                .param("uuid", uuid)
                .query()
                .listOfRows();
    }

    //поиск целой записи в скл по двум параметрам
    public List<Map<String, Object>> findAllByVariable(String variable, String variable2) {
        return jdbcClient
                .sql("""
                        SELECT *
                        FROM db_name.table_name
                        WHERE column_name = :variable
                        AND column_name2 = :variable2
                        """)
                .param("columnName", variable)
                .param("variable", variable2)
                .query()
                .listOfRows();
    }

    //поиск конкретного ключа значения в записи
    public Map<String, Object> findVariable(String variable) {
        return jdbcClient
                .sql("""
                        SELECT column_name 
                        FROM db_name.table_name
                        WHERE column_name = :variable
                        """)
                .param("variable", variable)
                .query()
                .singleRow();
    }

    //Удалить по значению стринги
    public void deleteByVariable(String variable) {
        jdbcClient
                .sql("""
                        DELETE 
                        FROM db_name.table_name
                        WHERE column_name = :variable
                        """)
                .param("variable", variable)
                .update();
    }

    //Удалить по значению uuid
    public void deleteByUuid(UUID id) {
        jdbcClient
                .sql("""
                        DELETE 
                        FROM db_name.table_name
                        WHERE column_name = :id
                        """)
                .param("id", id)
                .update();
    }

    //использует объект JdbcClient для выполнения SQL-запроса
    public void execute(String query) {
        jdbcClient
                .sql(query)
                .update();
    }
}
