package br.com.jmt.orders_management.domain.util;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DateUtils {

    private static final String DATETIME_JSON_PATTERN = "yyyy-MM-dd'T'hh:mm:ss";

    private DateUtils() {}

    public static String format(LocalDateTime dateTime) {
        if (Objects.isNull(dateTime)) return null;
        return DateTimeFormatter.ofPattern(DATETIME_JSON_PATTERN).format(dateTime);
    }

    public static LocalDateTime convert(String stringDate)  {
        if (StringUtils.isAllBlank(stringDate)) return null;
        return LocalDateTime.parse(stringDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}

