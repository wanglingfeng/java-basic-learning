package com;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by lfwang on 2016/8/24.
 */
public class MainTest {

    public static void main(String[] args) {
        List list = new ArrayList<>();

        String s = "s";

        list.add(s);

        Integer i = 0;
        list.add(i);

        Test test = new Test("asd", 12);
        list.add(test);

        System.out.println(list);

        Long value = 12L;
        String str = "LFWANG" + String.format("%06d", value);
        System.out.println(str);

        BigDecimal price = new BigDecimal("300.0383");
        BigDecimal tokens = new BigDecimal(500);

        System.out.println(6 * 10 * 30);

        System.out.println("sadf" + "\n" + "fff");

        /*Date date = new Date(116, 10, 14, 15, 32, 45);*/
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, Calendar.NOVEMBER, 14, 15, 32, 45);
        Date date = calendar.getTime();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        System.out.println(dateFormat.format(date));

        String dateResult = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(dateResult);
    }

    @AllArgsConstructor
    @Data
    public static class Test {
        private String name;
        private Integer age;
    }
}
