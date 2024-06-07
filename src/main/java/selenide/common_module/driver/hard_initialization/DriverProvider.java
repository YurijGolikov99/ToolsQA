package selenide.common_module.driver.hard_initialization;

public class DriverProvider {

    public void setupDriver() {
        SelenideConfig.setup();
    }

    public void closeDriver() {
        SelenideConfig.tearDown();
    }
}
