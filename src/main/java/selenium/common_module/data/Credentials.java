package selenium.common_module.data;

import selenium.common_module.property.PropertyHelper;

public enum Credentials {
    USER_LOGIN(PropertyHelper.getProperty("login.user")),
    USER_PASSWORD(PropertyHelper.getProperty("password.user")),
    ADMIN_LOGIN(PropertyHelper.getProperty("login.admin")),
    ADMIN_PASSWORD(PropertyHelper.getProperty("password.admin")),
    WRONG_LOGIN(PropertyHelper.getProperty("login.wrong")),
    WRONG_PASSWORD(PropertyHelper.getProperty("password.wrong"));

    String property;

    Credentials(String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }
}
