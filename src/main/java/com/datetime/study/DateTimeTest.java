package com.datetime.study;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by Lingfeng on 2016/7/19.
 */
public class DateTimeTest {

    public static void main(String[] args) {
        Date date = new Date();
        LocalDateTime time = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        System.out.println(time);

        LocalDateTime plusTime = time.plusMinutes(10);

        System.out.println(plusTime);
        Date out = Date.from(plusTime.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(out);
    }
}
