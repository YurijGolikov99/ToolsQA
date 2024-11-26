package selenide.api_module.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenide.api_module.constants.DatePatterns;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

//утилиты для работы с датами
public class DateUtils {

    private static final Logger log = LoggerFactory.getLogger(DatePatterns.class);

    //служит для предотвращения создания экземпляров класса
    private DateUtils() {
    }

    //Возвращает текущую дату и время, отформатированные по заданному шаблону.
    public static String getLocalDateNowOfPattern(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    //Возвращает дату, которая находится на указанное количество дней раньше текущей, в заданном формате.
    public static String getDateBeforeCurrentDate(Long minusDay, String pattern) {
        return createMidnight().minusDays(minusDay).format(DateTimeFormatter.ofPattern(pattern));
    }

    //Возвращает время полуночи текущего дня, с добавлением указанного количества минут.
    public static String getMidnight(int min, String pattern) {
        return createMidnight().plusMinutes((long)min).format(DateTimeFormatter.ofPattern(pattern));
    }

    //Создает объект LocalDateTime, представляющий текущий день в полуночь по часовому поясу "Europe/Moscow".
    public static LocalDateTime createMidnight() {
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now(ZoneId.of("Europe/Moscow"));
        return LocalDateTime.of(today, midnight);
    }

    //Создает объект полуночи с вычитанием минут и добавлением дней, форматирует результат по заданному шаблону.
    public static String createMidnightWithoutMin(Long min, Long day, String pattern) {
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now(ZoneId.of("Europe/Moscow"));
        return LocalDateTime.of(today, midnight.minusMinutes(min)).plusDays(day).format(DateTimeFormatter.ofPattern(pattern));
    }

    //Возвращает время полуночи текущего дня, добавляя указанное количество минут, и логирует результат.
    public static String getMidnight(int min) {
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now(ZoneId.of("Europe/Moscow"));
        LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
        String tomorrowMidnight = todayMidnight.plusMinutes((long)min).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        log.info(tomorrowMidnight);
        return tomorrowMidnight;
    }

    //Преобразует объект LocalDateTime с добавлением указанного количества минут в строку с форматом "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'".
    public static String getLocalDateTime(LocalDateTime nowLocal, Long min) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Calendar now = Calendar.getInstance();
        now.set(11, nowLocal.getHour());
        now.set(12, nowLocal.plusMinutes(min).getMinute());
        now.set(13, 0);
        now.set(14, 0);
        log.info(sdf.format(now.getTime()));
        return sdf.format(now.getTime());
    }

    //проверка попадания даты в интервал
    public static boolean isDateInBetweenIncludingEndPoints(LocalDate min, LocalDate max, LocalDate date) {
        return date.isAfter(min) || date.isBefore(max);
    }

    //проверка попадания даты и времени в интервал
    public static boolean isDateTimeInBetween(LocalDateTime start, LocalDateTime end, LocalDateTime target) {
        return !target.isBefore(start) && !target.isAfter(end);
    }

    //проверка валидности формата даты
    public static boolean isValidDate(String date, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDate.parse(date, formatter);
            return true;
        } catch (Exception e) {
            log.warn("Invalid date: {} with pattern: {}", date, pattern);
            return false;
        }
    }

    //получения текущей даты в другом часовом поясе
    public static String getCurrentDateInZone(String zoneId, String pattern) {
        return LocalDateTime.now(ZoneId.of(zoneId)).format(DateTimeFormatter.ofPattern(pattern));
    }

    //вычисление разницы между двумя датами
    public static long getDifferenceBetweenDates(LocalDateTime start, LocalDateTime end, ChronoUnit unit) {
        return unit.between(start, end);
    }

    //преобразования строки в LocalDateTime
    public static LocalDateTime parseStringToDateTime(String date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(date, formatter);
    }

    //форматирование даты в удобочитаемый текст
    public static String formatToReadableDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm");
        return date.format(formatter);
    }

    //преобразование даты из одного формата в другой
    public static String convertDateFormat(String date, String fromPattern, String toPattern) {
        DateTimeFormatter fromFormatter = DateTimeFormatter.ofPattern(fromPattern);
        DateTimeFormatter toFormatter = DateTimeFormatter.ofPattern(toPattern);
        LocalDateTime dateTime = LocalDateTime.parse(date, fromFormatter);
        return dateTime.format(toFormatter);
    }

    // метод для округления времени до ближайшего часа, минуты или другого временного интервала.
    public static LocalDateTime roundToNearestMinute(LocalDateTime dateTime, int minutes) {
        int remainder = dateTime.getMinute() % minutes;
        return remainder == 0
                ? dateTime
                : dateTime.plusMinutes(minutes - remainder);
    }
}
