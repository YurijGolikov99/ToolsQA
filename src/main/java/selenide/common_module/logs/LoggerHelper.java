package selenide.common_module.logs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerHelper {
    private static final Logger logger = LogManager.getRootLogger();

    public static void info(String message) {
        logger.info(message);
    }

    public static void info(String message, String ...args) {
        logger.info(String.format(message, args));
    }
}
