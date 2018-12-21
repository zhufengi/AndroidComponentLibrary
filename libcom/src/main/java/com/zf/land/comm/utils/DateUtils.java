package com.zf.land.comm.utils;

import android.annotation.SuppressLint;
import android.util.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import static com.zf.land.comm.utils.ConvertUtils.stringToInt;

/**
 *
 *==================================================================
 *
 *
 *                          HH:mm    15:44
 *                         h:mm a    3:44 下午
 *                        HH:mm z    15:44 CST
 *                        HH:mm Z    15:44 +0800
 *                     HH:mm zzzz    15:44 中国标准时间
 *                       HH:mm:ss    15:44:40
 *                     yyyy-MM-dd    2016-08-12
 *               yyyy-MM-dd HH:mm    2016-08-12 15:44
 *            yyyy-MM-dd HH:mm:ss    2016-08-12 15:44:40
 *       yyyy-MM-dd HH:mm:ss zzzz    2016-08-12 15:44:40 中国标准时间
 *  EEEE yyyy-MM-dd HH:mm:ss zzzz    星期五 2016-08-12 15:44:40 中国标准时间
 *       yyyy-MM-dd HH:mm:ss.SSSZ    2016-08-12 15:44:40.461+0800
 *     yyyy-MM-dd'T'HH:mm:ss.SSSZ    2016-08-12T15:44:40.461+0800
 *   yyyy.MM.dd G 'at' HH:mm:ss z    2016.08.12 公元 at 15:44:40 CST
 *                         K:mm a    3:44 下午
 *               EEE, MMM d, ''yy    星期五, 八月 12, '16
 *          hh 'o''clock' a, zzzz    03 o'clock 下午, 中国标准时间
 *   yyyyy.MMMMM.dd GGG hh:mm aaa    02016.八月.12 公元 03:44 下午
 *     EEE, d MMM yyyy HH:mm:ss Z    星期五, 12 八月 2016 15:44:40 +0800
 *                  yyMMddHHmmssZ    160812154440+0800
 *     yyyy-MM-dd'T'HH:mm:ss.SSSZ    2016-08-12T15:44:40.461+0800
 * EEEE 'DATE('yyyy-MM-dd')' 'TIME('HH:mm:ss')' zzzz    星期五 DATE(2016-08-12) TIME(15:44:40) 中国标准时间
 *
 *
 *
 *
 * =========================================================================
 *
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/12/21
 * @description: DateUtils
 * 时间相关辅助类
 *
 */
public class DateUtils {

    /**Date格式*/
    public static final String DATE_FORMAT_LINK = "yyyyMMddHHmmssSSS";

    /**Date格式 常用*/
    public static final String DATE_FORMAT_DETACH = "yyyy-MM-dd HH:mm:ss";

    /**Date格式 带毫秒*/
    public static final String DATE_FORMAT_DETACH_SSS = "yyyy-MM-dd HH:mm:ss SSS";

    /**时间格式 分钟：秒钟 一般用于视频时间显示*/
    public static final String DATE_FORMAT_MM_SS = "mm:ss";

    /**
     * 毫秒与毫秒的倍数
     */
    public static final int MSEC = 1;

    /******************** 时间相关常量 ********************/
    /**
     * 秒与毫秒的倍数
     */
    public static final int SEC = 1000;
    /**
     * 分与毫秒的倍数
     */
    public static final int MIN = 60000;
    /**
     * 时与毫秒的倍数
     */
    public static final int HOUR = 3600000;
    /**
     * 天与毫秒的倍数
     */
    public static final int DAY = 86400000;

    public enum TimeUnit {
        MSEC,
        SEC,
        MIN,
        HOUR,
        DAY
    }

    public static final SimpleDateFormat DEFAULT_SDF = new SimpleDateFormat(DATE_FORMAT_DETACH, Locale.getDefault());

    private DateUtils(){
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 将时间戳转为时间字符串
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param milliseconds 毫秒时间戳
     * @return 时间字符串
     */
    public static String milliseconds2String(long milliseconds) {
        return milliseconds2String(milliseconds, DEFAULT_SDF);
    }

    /**
     * 将时间戳转为时间字符串
     * <p>格式为用户自定义</p>
     *
     * @param milliseconds 毫秒时间戳
     * @param format       时间格式
     * @return 时间字符串
     */
    public static String milliseconds2String(long milliseconds, SimpleDateFormat format) {
        return format.format(new Date(milliseconds));
    }

    /**
     * 将时间字符串转为时间戳
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return 毫秒时间戳
     */
    public static long string2Milliseconds(String time) {
        return string2Milliseconds(time, DEFAULT_SDF);
    }

    /**
     * 将时间字符串转为时间戳
     * <p>格式为用户自定义</p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return 毫秒时间戳
     */
    public static long string2Milliseconds(String time, SimpleDateFormat format) {
        try {
            return format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 将时间字符串转为Date类型
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return Date类型
     */
    public static Date string2Date(String time) {
        return string2Date(time, DEFAULT_SDF);
    }

    /**
     * 将时间字符串转为Date类型
     * <p>格式为用户自定义</p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return Date类型
     */
    public static Date string2Date(String time, SimpleDateFormat format) {
        return new Date(string2Milliseconds(time, format));
    }

    /**
     * 将Date类型转为时间字符串
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time Date类型时间
     * @return 时间字符串
     */
    public static String date2String(Date time) {
        return date2String(time, DEFAULT_SDF);
    }

    /**
     * 将Date类型转为时间字符串
     * <p>格式为用户自定义</p>
     *
     * @param time   Date类型时间
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String date2String(Date time, SimpleDateFormat format) {
        return format.format(time);
    }

    /**
     * 将Date类型转为时间戳
     *
     * @param time Date类型时间
     * @return 毫秒时间戳
     */
    public static long date2Milliseconds(Date time) {
        return time.getTime();
    }

    /**
     * 将时间戳转为Date类型
     *
     * @param milliseconds 毫秒时间戳
     * @return Date类型时间
     */
    public static Date milliseconds2Date(long milliseconds) {
        return new Date(milliseconds);
    }

    /**
     * 毫秒时间戳单位转换（单位：unit）
     *
     * @param milliseconds 毫秒时间戳
     * @param unit         <ul>
     *                     <li>{@link TimeUnit#MSEC}: 毫秒</li>
     *                     <li>{@link TimeUnit#SEC }: 秒</li>
     *                     <li>{@link TimeUnit#MIN }: 分</li>
     *                     <li>{@link TimeUnit#HOUR}: 小时</li>
     *                     <li>{@link TimeUnit#DAY }: 天</li>
     *                     </ul>
     * @return unit时间戳
     */
    private static long milliseconds2Unit(long milliseconds, TimeUnit unit) {
        switch (unit) {
            case MSEC:
                return milliseconds / MSEC;
            case SEC:
                return milliseconds / SEC;
            case MIN:
                return milliseconds / MIN;
            case HOUR:
                return milliseconds / HOUR;
            case DAY:
                return milliseconds / DAY;
            default:
                break;
        }
        return -1;
    }

    /**
     * 获取两个时间差（单位：unit）
     * <p>time1和time2格式都为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time0 时间字符串1
     * @param time1 时间字符串2
     * @param unit  <ul>
     *              <li>{@link TimeUnit#MSEC}: 毫秒</li>
     *              <li>{@link TimeUnit#SEC }: 秒</li>
     *              <li>{@link TimeUnit#MIN }: 分</li>
     *              <li>{@link TimeUnit#HOUR}: 小时</li>
     *              <li>{@link TimeUnit#DAY }: 天</li>
     *              </ul>
     * @return unit时间戳
     */
    public static long getIntervalTime(String time0, String time1, TimeUnit unit) {
        return getIntervalTime(time0, time1, unit, DEFAULT_SDF);
    }

    /**
     * 获取两个时间差（单位：unit）
     * <p>time1和time2格式都为format</p>
     *
     * @param time0  时间字符串1
     * @param time1  时间字符串2
     * @param unit   <ul>
     *               <li>{@link TimeUnit#MSEC}: 毫秒</li>
     *               <li>{@link TimeUnit#SEC }: 秒</li>
     *               <li>{@link TimeUnit#MIN }: 分</li>
     *               <li>{@link TimeUnit#HOUR}: 小时</li>
     *               <li>{@link TimeUnit#DAY }: 天</li>
     *               </ul>
     * @param format 时间格式
     * @return unit时间戳
     */
    public static long getIntervalTime(String time0, String time1, TimeUnit unit, SimpleDateFormat format) {
        return Math.abs(milliseconds2Unit(string2Milliseconds(time0, format)
                - string2Milliseconds(time1, format), unit));
    }

    /**
     * 获取两个时间差（单位：unit）
     * <p>time1和time2都为Date类型</p>
     *
     * @param time1 Date类型时间1
     * @param time2 Date类型时间2
     * @param unit  <ul>
     *              <li>{@link TimeUnit#MSEC}: 毫秒</li>
     *              <li>{@link TimeUnit#SEC }: 秒</li>
     *              <li>{@link TimeUnit#MIN }: 分</li>
     *              <li>{@link TimeUnit#HOUR}: 小时</li>
     *              <li>{@link TimeUnit#DAY }: 天</li>
     *              </ul>
     * @return unit时间戳
     */
    public static long getIntervalTime(Date time1, Date time2, TimeUnit unit) {
        return Math.abs(milliseconds2Unit(date2Milliseconds(time2)
                - date2Milliseconds(time1), unit));
    }

    /**
     * 获取当前时间
     *
     * @return 毫秒时间戳
     */
    public static long getCurTimeMills() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @return 时间字符串
     */
    public static String getCurTimeString() {
        return date2String(new Date());
    }

    /**
     * 获取当前时间
     * <p>格式为用户自定义</p>
     *
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String getCurTimeString(SimpleDateFormat format) {
        return date2String(new Date(), format);
    }

    /**
     * 获取当前时间
     * <p>Date类型</p>
     *
     * @return Date类型时间
     */
    public static Date getCurTimeDate() {
        return new Date();
    }

    /**
     * 获取与当前时间的差（单位：unit）
     * <p>time格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @param unit <ul>
     *             <li>{@link TimeUnit#MSEC}:毫秒</li>
     *             <li>{@link TimeUnit#SEC }:秒</li>
     *             <li>{@link TimeUnit#MIN }:分</li>
     *             <li>{@link TimeUnit#HOUR}:小时</li>
     *             <li>{@link TimeUnit#DAY }:天</li>
     *             </ul>
     * @return unit时间戳
     */
    public static long getIntervalByNow(String time, TimeUnit unit) {
        return getIntervalByNow(time, unit, DEFAULT_SDF);
    }

    /**
     * 获取与当前时间的差（单位：unit）
     * <p>time格式为format</p>
     *
     * @param time   时间字符串
     * @param unit   <ul>
     *               <li>{@link TimeUnit#MSEC}: 毫秒</li>
     *               <li>{@link TimeUnit#SEC }: 秒</li>
     *               <li>{@link TimeUnit#MIN }: 分</li>
     *               <li>{@link TimeUnit#HOUR}: 小时</li>
     *               <li>{@link TimeUnit#DAY }: 天</li>
     *               </ul>
     * @param format 时间格式
     * @return unit时间戳
     */
    public static long getIntervalByNow(String time, TimeUnit unit, SimpleDateFormat format) {
        return getIntervalTime(getCurTimeString(), time, unit, format);
    }

    /**
     * 获取与当前时间的差（单位：unit）
     * <p>time为Date类型</p>
     *
     * @param time Date类型时间
     * @param unit <ul>
     *             <li>{@link TimeUnit#MSEC}: 毫秒</li>
     *             <li>{@link TimeUnit#SEC }: 秒</li>
     *             <li>{@link TimeUnit#MIN }: 分</li>
     *             <li>{@link TimeUnit#HOUR}: 小时</li>
     *             <li>{@link TimeUnit#DAY }: 天</li>
     *             </ul>
     * @return unit时间戳
     */
    public static long getIntervalByNow(Date time, TimeUnit unit) {
        return getIntervalTime(getCurTimeDate(), time, unit);
    }

    /**
     * 判断闰年
     *
     * @param year 年份
     * @return {@code true}: 闰年<br>
     *          {@code false}: 平年
     */
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    /**
     * 将date转换成format格式的日期
     *
     * @param format 格式
     * @param date   日期
     * @return
     */
    public static String simpleDateFormat(String format, Date date) {
        if (format == null || "".equals(format)) {
            format = DATE_FORMAT_DETACH_SSS;
        }
        String content = new SimpleDateFormat(format).format(date);
        return content;
    }

    //--------------------------------------------字符串转换成时间戳-----------------------------------

    /**
     * 将指定格式的日期转换成时间戳
     *
     * @param mDate
     * @return
     */
    public static String Date2Timestamp(Date mDate) {
        return String.valueOf(mDate.getTime()).substring(0, 10);
    }

    /**
     * 将日期字符串 按照 指定的格式 转换成 DATE
     * 转换失败时 return null;
     *
     * @param format
     * @param datess
     * @return
     */
    public static Date string2Date(String format, String datess) {
        SimpleDateFormat sdr = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdr.parse(datess);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将 yyyy年MM月dd日 转换成 时间戳
     *
     * @param format
     * @param datess
     * @return
     */
    public static String string2Timestamp(String format, String datess) {
        Date date = string2Date(format, datess);
        return Date2Timestamp(date);
    }
    //===========================================字符串转换成时间戳====================================

    /**
     * 获取当前日期时间 / 得到今天的日期
     * str yyyyMMddhhMMss 之类的
     *
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getCurrentDateTime(String format) {
        return simpleDateFormat(format, new Date());
    }

    /**
     * 时间戳  转换成 指定格式的日期
     * 如果format为空，则默认格式为
     *
     * @param times  时间戳
     * @param format 日期格式 yyyy-MM-dd HH:mm:ss
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getDate(String times, String format) {
        return simpleDateFormat(format, new Date(stringToInt(times) * 1000L));
    }

    /**
     * 得到昨天的日期
     *
     * @param format 日期格式 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getYestoryDate(String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return simpleDateFormat(format, calendar.getTime());
    }

    /**
     * 视频时间 转换成 "mm:ss"
     *
     * @param milliseconds
     * @return
     */
    public static String formatTime(long milliseconds) {
        String format = DATE_FORMAT_MM_SS;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        String video_time = sdf.format(milliseconds);
        return video_time;
    }

    /**
     * "mm:ss" 转换成 视频时间
     *
     * @param time
     * @return
     */
    public static long formatSeconds(String time) {
        String format = DATE_FORMAT_MM_SS;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        Date date;
        long times = 0;
        try {
            date = sdf.parse(time);
            long l = date.getTime();
            times = l;
            Log.d("时间戳", times + "");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return times;
    }

    /**
     * 根据年 月 获取对应的月份 天数
     */
    public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 判断当前日期是星期几
     *
     * @param strDate 修要判断的时间
     * @return dayForWeek 判断结果
     * @Exception 发生异常<br>
     */
    public static int stringForWeek(String strDate) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse(strDate));
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return 7;
        } else {
            return c.get(Calendar.DAY_OF_WEEK) - 1;
        }
    }

    /**
     * 判断当前日期是星期几
     *
     * @param strDate 修要判断的时间
     * @return dayForWeek 判断结果
     * @Exception 发生异常<br>
     */
    public static int stringForWeek(String strDate, SimpleDateFormat simpleDateFormat) throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTime(simpleDateFormat.parse(strDate));
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return 7;
        } else {
            return c.get(Calendar.DAY_OF_WEEK) - 1;
        }
    }

    /**
     * 根据日期判断星座
     *
     * @param month
     * @param day
     * @return
     */
    public static String getAstro(int month, int day) {
        String[] starArr = {"魔羯座", "水瓶座", "双鱼座", "牡羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座"};
        // 两个星座分割日
        int[] DayArr = {22, 20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22};

        if (month <= 0 || day <= 0) {
            return "猴年马月座";
        } else if (month > 12 || day > 31) {
            return "猴年马月座";
        }

        int index = month;
        // 所查询日期在分割日之前，索引-1，否则不变
        if (day < DayArr[month - 1]) {
            index = index - 1;
        }
        // 返回索引指向的星座string
        return starArr[index];
    }









}
