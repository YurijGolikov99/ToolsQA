package selenide.common_module.driver.hard_initialization;

public class DriverProvider {
    public static void setupDriver() {
        SelenideConfig.setup();
    }

    public static void tearDown() {
        SelenideConfig.tearDown();
    }
}
