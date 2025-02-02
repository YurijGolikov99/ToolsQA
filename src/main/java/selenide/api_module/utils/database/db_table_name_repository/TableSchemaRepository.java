package selenide.api_module.utils.database.db_table_name_repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import selenide.api_module.utils.database.InformationSchema;

import java.util.List;

//методы для получения метаинформации о схемах, таблицах, и столбцах в базе данных.
@Repository
public class TableSchemaRepository {

    @Autowired
    @Qualifier("dbNameClient")
    private JdbcClient jdbcClient;

    @Autowired
    InformationSchema informationSchema;

    //Возвращает список схем, доступных в базе данных
    public List<String> getSchemasByDb() {
        return informationSchema.getSchemasByDb(jdbcClient);
    }

    //Возвращает список таблиц, принадлежащих конкретной схеме
    public List<String> getTablesBySchema(String schema) {
        return informationSchema.getTablesBySchema(schema, jdbcClient);
    }

    //Возвращает список всех столбцов, принадлежащих указанной таблице
    public List<String> getColumnsByTable(String tableName) {
        return informationSchema.getColumnsByTable(tableName, jdbcClient);
    }

    //Возвращает информацию о том, может ли конкретный столбец указанной таблицы принимать значение NULL
    public String getColumnNullable(String tableName, String columnName) {
        return informationSchema.getColumnNullable(tableName, columnName, jdbcClient);
    }

    //Возвращает тип данных столбца, например, VARCHAR, INTEGER, DATE
    public String getDataType(String tableName, String columnName) {
        return informationSchema.getDataType(tableName, columnName, jdbcClient);
    }

    //Возвращает максимальную длину столбца, если это применимо (например, для строковых типов данных).
    public int getMaximumLength(String tableName, String columnName) {
        return informationSchema.getMaximumLength(tableName, columnName, jdbcClient);
    }

}
