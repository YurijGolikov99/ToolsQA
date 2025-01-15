package selenide.common_module.data;

public enum UserCredentials {
    // Определяем значения
    YURIJ("YurijJUnit", "Yurij_@1999"),
    WRONG("wrong", "wrong");

    // Поля для хранения данных
    private final String username;
    private final String password;

    // Конструктор
    UserCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Методы для доступа к данным
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}