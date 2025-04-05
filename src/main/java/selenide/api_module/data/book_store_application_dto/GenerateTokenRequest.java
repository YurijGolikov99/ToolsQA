package selenide.api_module.data.book_store_application_dto;

public class GenerateTokenRequest {

    private String userName;
    private String password;

    public GenerateTokenRequest(String userName, String password) {
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
