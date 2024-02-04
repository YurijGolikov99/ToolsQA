package selenide.common_module;


import selenide.common_module.property.PropertyHelper;

//5 Создали глобальные константы и настройки
public class Global {

    public static final String OS_NAME = System.getProperty("os.name");
    public static final String WEBDRIVER_TYPE = PropertyHelper.getProperty("driver");

    public static final String CHROME_NAME = "Chrome";
    public static final String FIREFOX_NAME = "Firefox";
    public static final String SAFARI_NAME = "Safari";

    public static final String OS_NAME_WIN = "Win";
    public static final String OS_NAME_MAC = "Mac";


}