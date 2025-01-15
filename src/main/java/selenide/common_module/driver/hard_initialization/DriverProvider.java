package selenide.common_module.driver.hard_initialization;

//DriverProvider - всё будет прогоняться сплошными тестами в одном окне
public class DriverProvider {

    public void setupDriver() {
        SelenideConfig.setup();
    }

    public void closeDriver() {
        SelenideConfig.tearDown();
    }
}
