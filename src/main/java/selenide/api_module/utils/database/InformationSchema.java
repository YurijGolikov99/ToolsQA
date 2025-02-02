package selenide.api_module.utils.database;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//класс служит для получения системной информации о базе данных
@Component
public class InformationSchema {

    //Возвращает список всех схем в базе данных
    public List<String> getSchemasByDb(JdbcClient jdbcClient) {
        List<String> result = new ArrayList<>();
        jdbcClient
                .sql("""
                        SELECT schema_name
                        FROM information_schema.schemata
                        """)
                .query()
                .listOfRows()
                .forEach(map -> result.add(map.get("schema_name").toString()));
        return result;
    }

    //Возвращает список таблиц, принадлежащих указанной схеме.
    public List<String> getTablesBySchema(String schema, JdbcClient jdbcClient) {
        List<String> result = new ArrayList<>();
        jdbcClient
                .sql("""
                        SELECT table_name
                        FROM information_schema.tables
                        WHERE table_schema = :schema
                        """)
                .param("schema", schema)
                .query()
                .listOfRows()
                .forEach(map -> result.add(map.get("table_name").toString()));
        return result;
    }

    //Возвращает список всех столбцов указанной таблицы
    public List<String> getColumnsByTable(String tableName, JdbcClient jdbcClient) {
        List<String> result = new ArrayList<>();
        jdbcClient
                .sql("""
                        SELECT column_name
                        FROM information_schema.columns
                        WHERE table_name = :tableName
                        """)
                .param("tableName", tableName)
                .query()
                .listOfRows()
                .forEach(map -> result.add(map.get("column_name").toString()));
        return result;
    }

    //Возвращает значение is_nullable для указанного столбца таблицы (YES или NO),
    // показывающее, может ли поле принимать NULL.
    public String getColumnNullable(String tableName, String columnName, JdbcClient jdbcClient) {
        return jdbcClient
                .sql("""
                        SELECT is_nullable
                        FROM information_schema.columns
                        WHERE table_name = :tableName
                        AND column_name = :columnName
                        """)
                .param("tableName", tableName)
                .param("columnName", columnName)
                .query()
                .singleValue().toString();
    }

    //Возвращает тип данных столбца, например, VARCHAR, INTEGER, DATE
    public String getDataType(String tableName, String columnName, JdbcClient jdbcClient) {
        return jdbcClient
                .sql("""
                        SELECT data_type
                        FROM information_schema.columns
                        WHERE table_name = :tableName
                        AND column_name = :columnName
                        """)
                .param("tableName", tableName)
                .param("columnName", columnName)
                .query()
                .singleValue().toString();
    }

    // Возвращает максимальную длину строки для столбца (например, для VARCHAR). Если длина не задана, возвращается NULL.
    public int getMaximumLength(String tableName, String columnName, JdbcClient jdbcClient) {
        return Integer.parseInt(jdbcClient
                .sql("""
                        SELECT character_maximum_length
                        FROM information_schema.columns
                        WHERE table_name = :tableName
                        AND column_name = :columnName
                        """)
                .param("tableName", tableName)
                .param("columnName", columnName)
                .query()
                .singleValue().toString());
    }
}
