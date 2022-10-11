package org.xgvela.oam.utils;


import org.joda.time.DateTime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author wangyongbo01
 */
public class LocalDateTimeUtils {

    public final static String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public final static String MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
    public final static String DATE_PATTERN = "yyyy-MM-dd";

    public final static DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern(DATETIME_PATTERN);
    public final static DateTimeFormatter MINUTE_FORMAT = DateTimeFormatter.ofPattern(MINUTE_PATTERN);
    public final static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern(DATE_PATTERN);


    public static LocalDateTime getLocalDateTime(long milliseconds) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), ZoneId.systemDefault());
    }

    public static LocalDateTime getLocalDateTime(DateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return getLocalDateTime(dateTime.getMillis());
    }

    public static long getMilliSeconds(LocalDateTime time) {
        return time.toInstant(OffsetDateTime.now().getOffset()).toEpochMilli();
    }

}
