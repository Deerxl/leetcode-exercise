package org.example.leetcode;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jialu
 */
public class Test {

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd";

    static Random random = new Random();

    public static void main(String[] args) {
        System.out.println(getTodayEndTime());
        System.out.println(getTimesNight());
        System.out.println(getDay());
    }

    public static long getTodayEndTime() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MAX).toEpochSecond(ZoneOffset.of("+8"));
    }

    public static long getTimesNight(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis() - 1;
    }

    public static int getDay() {
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
        System.out.println(dateFormat.format(cal.getTime()));
        int d = cal.get(Calendar.DATE);
        return d;
    }


    @Data
    public static class Task implements Serializable {

        private static final long serialVersionUID = 922653937108308953L;

        private Long subTaskId;

        /**
         * 1：重试
         * 2：提交打印
         * 3：提交打印结果
         */
        private Integer operatorType;

        private String statusCode;
    }

    public static Date parseDate(String dates, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(dates);
        } catch (ParseException e) {
            return null;
        }
    }
    public static String formatDay(Date date) {
        if (date == null) {
            return null;
        }

        DateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT);
        return df.format(date);
    }

    public static String getRandomNumber() {
        int ends = random.nextInt(99);
        return String.format("%02d", ends);
    }

    public static boolean isNumber(String str, int decimalPlaces){
        return str.matches("^[0-9]+\\.?[0-9]{0," + decimalPlaces + "}$");
    }

}
