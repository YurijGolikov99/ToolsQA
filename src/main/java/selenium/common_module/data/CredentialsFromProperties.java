package selenium.common_module.data;

import selenium.common_module.property.PropertyHelper;

public enum CredentialsFromProperties {
    USER_LOGIN(PropertyHelper.getProperty("login.user")),
    USER_PASSWORD(PropertyHelper.getProperty("password.user")),
    WRONG_LOGIN(PropertyHelper.getProperty("login.wrong")),
    WRONG_PASSWORD(PropertyHelper.getProperty("password.wrong"));

    String property;

    CredentialsFromProperties(String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }
}
