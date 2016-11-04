package com.java8.time.study;

import java.time.LocalDate;
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
    }
}
