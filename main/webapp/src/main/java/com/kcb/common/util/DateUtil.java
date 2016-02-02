package com.kcb.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 张海峰 zhanghaifeng@kucangbao.com
 * @version 创建时间：2012-11-22 上午11:42:52
 * 日期工具类
 */
public class DateUtil
{

    private static DateUtil DATEUTIL = new DateUtil();

    private DateUtil()
    {
    }
	/**
	 * 获取一个实例
	 * @return
	 */
    public static DateUtil getInstance()
    {
        return DATEUTIL;
    }

    
    
    /**
     * 将给定日期转换成给定格式的字符串
     * 
     * @param date
     * @param format
     * @return
     */
    public static String getDateFormatString(Date date, String format)
    {

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return  dateFormat.format(date);
    }

    /**
     * 将字符串日期转换成给定格式的日期
     * 
     * @param date
     * @param format
     * @return
     */
    public static Date getDateByFormatString(String date, String format)
    {

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date formatDate = null;
        try
        {
            formatDate = dateFormat.parse(date);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        return formatDate;
    }

    /**
     * 将日期转换成Ｃalendar
     * 
     * @param date
     * @return
     */
    public static Calendar getCalendarByDate(Date date)
    {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar;
    }

    /**
     * 获取指定日期前（后）多少天的日期
     * 
     * @param date
     *            ：指定日期
     * @param dayCount
     *            ：负数：前多少天，正数后多少天
     * @return
     */
    public static Date getBeforOrAfterDayCount(Date date, int dayCount)
    {

        Calendar calendar = getCalendarByDate(date);

        calendar.add(Calendar.DAY_OF_WEEK, dayCount);

        return calendar.getTime();
    }

    /**
     * 获取给定时间所在周的第一天(Sunday)的日期和最后一天(Saturday)的日期
     * 
     * @param calendar
     * @return Date数组，[0]为第一天的日期，[1]最后一天的日期
     */
    public Date[] getWeekStartAndEndDate(Date date)
    {

        Calendar calendar = getCalendarByDate(date);

        Date[] dates = new Date[2];
        Date firstDateOfWeek, lastDateOfWeek;
        // 得到当天是这周的第几天
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        // 减去dayOfWeek,得到第一天的日期，因为Calendar用０－６代表一周七天，所以要减一
        calendar.add(Calendar.DAY_OF_WEEK, -(dayOfWeek - 1));
        firstDateOfWeek = calendar.getTime();
        // 每周7天，加６，得到最后一天的日子
        calendar.add(Calendar.DAY_OF_WEEK, 6);
        lastDateOfWeek = calendar.getTime();

        dates[0] = firstDateOfWeek;
        dates[1] = lastDateOfWeek;
        return dates;
    }

    /**
     * 获取给定时间所在月的第一天Ｆ的日期和最后一天的日期
     * 
     * @param calendar
     * @return Date数组，[0]为第一天的日期，[1]最后一天的日期
     */
    public static Date[] getMonthStartAndEndDate(Date date)
    {

        Calendar calendar = getCalendarByDate(date);

        Date[] dates = new Date[2];
        Date firstDateOfMonth, lastDateOfMonth;
        // 得到当天是这月的第几天
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        // 减去dayOfMonth,得到第一天的日期，因为Calendar用０代表每月的第一天，所以要减一
        calendar.add(Calendar.DAY_OF_MONTH, -(dayOfMonth - 1));
        firstDateOfMonth = calendar.getTime();
        // calendar.getActualMaximum(Calendar.DAY_OF_MONTH)得到这个月有几天
        calendar.add(Calendar.DAY_OF_MONTH, calendar
                .getActualMaximum(Calendar.DAY_OF_MONTH) - 1);
        lastDateOfMonth = calendar.getTime();

        dates[0] = firstDateOfMonth;
        dates[1] = lastDateOfMonth;
        return dates;
    }

    /**
     * 获取给定日期所在年
     * 
     * @param date
     * @return
     */
    public static int getYearByDate(Date date)
    {

        Calendar calendar = getCalendarByDate(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取给定日期所在月
     * 
     * @param date
     * @return
     */
    public static int getMonthByDate(Date date)
    {

        Calendar calendar = getCalendarByDate(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取给定日期所在日
     * 
     * @param date
     * @return
     */
    public static int getDayByDate(Date date)
    {

        Calendar calendar = getCalendarByDate(date);
        
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
   
}
