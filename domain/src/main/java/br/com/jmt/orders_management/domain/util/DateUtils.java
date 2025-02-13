package br.com.jmt.orders_management.domain.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DateUtils {

    private static final String DATETIME_JSON_PATTERN = "yyyy-MM-dd hh:mm:ss";

    private DateUtils() {}

    public static String format(LocalDateTime dateTime) {
        if (Objects.isNull(dateTime)) return null;
        return DateTimeFormatter.ofPattern(DATETIME_JSON_PATTERN).format(dateTime);
    }
}

