package selenide.api_module.model;

import lombok.Data;
import lombok.experimental.Accessors;

//Содержит имя пользователя базы данных, используемое для аутентификации.
@Data
@Accessors(chain = true)
public class DbConfigDto {

    private String user;
    private String password;
    private String url;
    private String name;
}