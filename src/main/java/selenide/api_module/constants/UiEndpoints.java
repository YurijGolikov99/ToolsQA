package selenide.api_module.constants;

public enum UiEndpoints {
    BASE("https://demoqa.com"),
    BOOKS("https://demoqa.com/books"),
    LOGIN("https://demoqa.com/login"),
    REGISTER("https://demoqa.com/register"),
    PROFILE("https://demoqa.com/profile"),
    BOOK_STORE_PAGE("https://demoqa.com/books");

    private final String url;

    UiEndpoints(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
