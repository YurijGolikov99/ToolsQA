package selenide.common_module.utils;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

//утилитарный класс, содержащий обобщённые функции для обработки данных
public class SqlUtils {

    public static String buildInSqlQuestionMarks(final int size) {
        return String.join(",", Collections.nCopies(size, "?"));
    }

    public static String buildInSqlListValues(List<Object> list) {
        String result = "";
        for (Object o : list.toArray()) {
            result += "'" + o.toString() + "',";
        }
        return result.substring(0, result.length() - 1);
    }

    public static String timeToString(String secs) {
        BigDecimal seconds = new BigDecimal(secs);
        Duration duration = Duration.ofNanos(seconds.multiply(BigDecimal.valueOf(1_000_000_000)).longValue());
        return String.format("%02d:%02d:%02d.%03d",
                duration.toHours() % 24,
                duration.toMinutes() % 60,
                duration.toSeconds() % 60,
                duration.toMillis() % 1000);
    }

}
