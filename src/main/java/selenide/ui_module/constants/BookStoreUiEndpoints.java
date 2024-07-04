package selenide.ui_module.constants;

public enum BookStoreUiEndpoints {

    BASE_URL("https://demoqa.com"),
    LOGIN("https://demoqa.com/login"),
    REGISTER("https://demoqa.com/register"),
    PROFILE("https://demoqa.com/profile"),
    BOOK_STORE_PAGE("https://demoqa.com/books");

    private final String url;

    BookStoreUiEndpoints(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}

