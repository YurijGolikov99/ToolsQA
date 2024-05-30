package selenium.api_module.constants;

public enum ApiEndpoints {
    AUTHORISATION_PAGE("https://demoqa.com/BookStore/v1/Autorized"),
    REGISTER_PAGE("https://demoqa.com/Account/v1/User"),
    DELETE_USER_PAGE("https://demoqa.com/Account/v1/User/{UUID}"),
    BOOKSTORE_PAGE("https://demoqa.com/BookStore/v1/Books");
    private final String url;

    ApiEndpoints(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
