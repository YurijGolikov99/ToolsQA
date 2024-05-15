package selenide.common_module.constants;

public enum PropertyPath {
    BASE_URL("base.url");

    private final String path;

    PropertyPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
