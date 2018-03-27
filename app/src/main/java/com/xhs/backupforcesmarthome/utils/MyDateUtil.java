package com.xhs.backupforcesmarthome.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author zdl
 * @date 2018/3/3 13:19
 * email zdl328465042@163.com
 * explain 封装日期相关工具类
 */

public class MyDateUtil {

    /**
     * 获取系统时间   格式为:yyyy-MM-dd-HH-mm-ss
     *
     * @return date
     */
    public String getCurrentDate() {
        Calendar date = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault());
        return sdf.format(date.getTime());
    }

    /**
     * 获取系统时间戳
     *
     * @return time
     */
    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    /**
     * 时间戳转换成字符串
     *
     * @param time time
     * @return date
     */
    public static String getDateByTime(long time) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(date);
    }

    /**
     * 字符串转换为时间戳，需要保持字符串格式一样
     *
     * @param dateStr date
     * @return time
     */
    public static long getTimeByDate(String dateStr) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        try {
            date = sf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }
}
