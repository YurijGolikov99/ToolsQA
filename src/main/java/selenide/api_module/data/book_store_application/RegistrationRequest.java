package selenide.api_module.data.book_store_application;

/**
 * {
 *   "userName": "string",
 *   "password": "string"
 * }
 */
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
