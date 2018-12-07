package com.example.codedemo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    private TimeUtil() {
    }

    /**
     * 获取今日日期
     *
     * @return
     */
    public static String getToday() {
        return "20150101";
    }

    public static String getNow(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }
};