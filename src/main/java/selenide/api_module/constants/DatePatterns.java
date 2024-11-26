package selenide.api_module.constants;

//класс с видами возможных дат
public final class DatePatterns {

    public static final String DATE = "dd.MM.yyyy";
    public static final String DATE_WITH_TIME = "dd.MM.yyyy HH:mm";
    public static final String DATE_FORMAT_WITH_TIMEZONE = "yyyy-MM-ddX";
    public static final String DATE_FORMAT = "yyyy.MM.dd";
    public static final String DATETIME_FORMAT_WITHOUT_TIMEZONE = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DATETIME_FORMAT_WITHOUT_TIMEZONE_ = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final String DATETIME_FORMAT_ = "yyyy-MM-dd'T'HH:mm";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssX";
    public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
    public static final String TIMESTAMP_FORMAT_WITHOUT_TIMEZONE = "yyyy-MM-dd HH:mm:ss.S";
    public static final String TIMESTAMP_FORMAT_WITHOUT_TIMEZONE_N = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String TIMESTAMP_FORMAT_WITHOUT_TIMEZONE_ = "yyyy-MM-dd HH:mm";
    public static final String REVERSE_DATE = "yyyyMMdd";
    public static final String DATE_FORMAT_LEGACY = "yyyy-MM-dd";
    public static final String DATE_FORMAT_LEG = "yyyy-M-dd";
    public static final String TIMESTAMP_FORMAT_WITH_SECOND = "yyyy-MM-dd HH:mm:ss";
    public static final String TIMESTAMP_FORMAT_WITHOUT_SECOND = "yyyy-MM-dd HH:mm";
    public static final String DATETIME_FORMAT_WITHOUT_TIMEZONE_Z = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final String TIMESTAMP_FORMAT_WITHOUT_MIN_ = "yyyy-MM-dd HH";
    public static final String TIMESTAMP_FORMAT_WITHOUT_MIN = "yyyy-MM-dd'T'HH:m";
    public static final String DATE_WITH_ZONE = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String DATE_WITH_ZONE_N = "yyyy-MM-dd'T'HH:mm:ss.SS'Z'";
    public static final String DATE_FULL = "dd.MM.yyyy HH:mm:ss";

    private DatePatterns() {
    }
}
