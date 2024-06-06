package selenide.api_module.constants;

public class ApiEndpoints {

    private static final String AUTHORISATION_URL = "https://demoqa.com/Account/v1/Authorized";
    //https://demoqa.com/swagger/#/Account/AccountV1GenerateTokenPost
    private static final String GENERATE_TOKEN_URL = "https://demoqa.com/Account/v1/GenerateToken";
    private static final String REGISTER_URL = "https://demoqa.com/Account/v1/User";
    private static final String DELETE_USER_URL = "https://demoqa.com/Account/v1/User/";
    private static final String BOOK_STORE_URL = "https://demoqa.com/BookStore/v1/Books";

    public static String getAuthorisationUrl() {
        return AUTHORISATION_URL;
    }

    public static String getGenerateTokenUrl(){
        return GENERATE_TOKEN_URL;
    }

    public static String getRegisterUrl() {
        return REGISTER_URL;
    }

    public static String getDeleteUserUrl() {
        return DELETE_USER_URL;
    }

    public static String getBookStoreUrl() {
        return BOOK_STORE_URL;
    }
}
