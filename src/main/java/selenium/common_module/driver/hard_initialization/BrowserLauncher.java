package selenium.common_module.driver.hard_initialization;

import org.openqa.selenium.WebDriver;

//0 Создали новую реализацию BrowserLauncher по Принципу OCP - Код расширяем, но не модифицируем
public interface BrowserLauncher {
    WebDriver createDriver();
}
