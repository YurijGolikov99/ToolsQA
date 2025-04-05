package selenium.common_module.utils;

import selenium.common_module.property.PropertyHelper;

//6 Создали класс, который содержит значения таймаутов, зависящие от настроек в файле свойств
public class TimeOuts {

    public static final int PAGE_LOAD_TIMEOUT = Integer.parseInt(PropertyHelper.getProperty("timeouts.page"));
    public static final int ELEMENT_TIMEOUT = Integer.parseInt(PropertyHelper.getProperty("timeouts.element"));
    public static final int MINIMAL_ELEMENT_TIMEOUT = Integer.parseInt(PropertyHelper.getProperty("timeouts.minimal"));
    public static final int POLLING = Integer.parseInt(PropertyHelper.getProperty("timeouts.polling"));
}
