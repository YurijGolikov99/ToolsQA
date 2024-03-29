package selenide.api_module.data;

//обычно так выглядит регистрация https://demoqa.com/swagger/#/Account/AccountV1UserPost
//всегда менять лучше переменные с публичных на приватные
//1 request (отправляем) ->
public class RegistrationRequest {
    private String userName;
    private String password;

    public RegistrationRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
