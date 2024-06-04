package selenide.common_module.driver.hard_initialization;

import org.junit.After;
import org.junit.Before;

public class DriverProvider {

    @Before
    public void setupDriver() {
        SelenideConfig.setup();
    }

    @After
    public void tearDown() {
        SelenideConfig.tearDown();
    }
}
