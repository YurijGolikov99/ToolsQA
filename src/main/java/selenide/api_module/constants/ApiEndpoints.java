package selenide.api_module.constants;

public enum ApiEndpoints {
    BASE_URL("https://demoqa.com/Account/v1"),
    AUTHORISATION_URL("https://demoqa.com/BookStore/v1/Autorized"),
    GENERATE_TOKEN_URL("https://demoqa.com/Account/v1/GenerateToken"),
    REGISTER_URL("https://demoqa.com/Account/v1/User"),
    DELETE_USER_URL("https://demoqa.com/Account/v1/User/{UUID}"),
    BOOKSTORE_PAGE("https://demoqa.com/BookStore/v1/Books");
    private final String url;

    ApiEndpoints(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
