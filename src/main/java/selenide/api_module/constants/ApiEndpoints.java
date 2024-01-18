package selenide.api_module.constants;

public enum ApiEndpoints {
    AUTHORISATION("https://demoqa.com/BookStore/v1/Autorized"),
    REGISTER("https://demoqa.com/Account/v1/User"),
    DELETE_USER("https://demoqa.com/Account/v1/User/{UUID}"),
    BOOKSTORE("https://demoqa.com/BookStore/v1/Books");
    private final String url;

    ApiEndpoints(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
