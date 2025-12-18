package com.java8.time.study;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

/**
 * Created by Lingfeng on 2016/7/29.
 */
public class DateTimeFormatterTest {

    public static void main(String... args) {
        DateTimeFormatter italianFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyyy", Locale.ITALIAN);
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        String formattedDate = date1.format(italianFormatter);
        LocalDate date2 = LocalDate.parse(formattedDate, italianFormatter);

        DateTimeFormatter italianFormatter1 = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.ITALIAN);

        LocalDateTime now = LocalDateTime.now();
        System.out.println("first day of week: " + firstDayOfWeek(now));
        System.out.println("last day of week: " + lastDayOfWeek(now));
    }

    /**
     * 获取当前周的第一天
     * @param dateTime
     * @return
     */
    public static LocalDateTime firstDayOfWeek(LocalDateTime dateTime) {

        return dateTime.with(temporal -> temporal.with(ChronoField.DAY_OF_WEEK, 1))
                .withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    /**
     * 获取当前周的最后一天
     * @param dateTime
     * @return
     */
    public static LocalDateTime lastDayOfWeek(LocalDateTime dateTime) {

        return dateTime.with(temporal -> temporal.with(ChronoField.DAY_OF_WEEK,
                        ChronoField.DAY_OF_WEEK.range().getMaximum()))
                .withHour((int) ChronoField.HOUR_OF_DAY.range().getMaximum())
                .withMinute((int) ChronoField.MINUTE_OF_HOUR.range().getMaximum())
                .withSecond((int) ChronoField.SECOND_OF_MINUTE.range().getMaximum())
                .withNano((int) ChronoField.NANO_OF_SECOND.range().getMaximum());
    }
}
