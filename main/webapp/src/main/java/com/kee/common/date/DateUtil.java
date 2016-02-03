package com.kee.common.date;

/**
 * Title: DateUtil<br>
 * Description:
 * 
 * @Copyright: Copyright (c) 200-2005. All rights reserved.
 * @Company: KingYi杭州金安易计算机技术有限公司
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 常用时间操作
 * 
 */
public class DateUtil {

	public static String formart1 = "yyyy/MM/dd-HH/mm/ss";
	public static String formart2 = "yyyy年MM月dd日";
	public static String formart3 = "yyyy-MM-dd";
	public static String formart4 = "yyyy年MM月dd日 HH时mm分ss秒";
	public static String formart5 = "yy-MM-dd";
	public static String formart6 = "yy年MM月dd日";
	public static String formart7 = "yy/MM/dd";
	public static String formart8 = "MMddHHmmss";
	public static String formart9 = "yyyy-MM-dd HH:mm:ss";
	public static String formart10 = "yyMMddHHmmss";
	public static String formart11 = "yyMMdd";
	public static String formart12 = "yyyy/MM/dd HH:mm:ss";
	public static String formart13 = "yyyy-MM-dd HH:mm";
	/**
	 *将long值，转换成秒的除数
	 */
	public static long SECOND = 1000;
	/**
	 *将long值，转换成分的除数
	 */
	public static long MINUTE = 60000;
	/**
	 *将long值，转换成小时的除数
	 */
	public static long HOURE = 360000;
	/**
	 *将long值，转换成天的除数
	 */
	public static long DAY = 8460000;

	public static long getTimeInMillis(){
		 return Calendar.getInstance().getTimeInMillis();
	}
	/**
	 * 获取Date对象
	 * 
	 * @return
	 */
	public static Date getDate() {
		return Calendar.getInstance().getTime();
	}
	
	public static Date getDate(long _lTimes){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(_lTimes);
		return cal.getTime();
	}
	/**
	 * 根据long值，返回两个时间相差多少分钟
	 * @param _lFirstTimes
	 * @param _lSecondTimes
	 * @return
	 */
	public static long compareAndReturnMinute(long _lFirstTimes, long _lSecondTimes){
		return compare(_lFirstTimes, _lSecondTimes, MINUTE);
	}
	
	/**
	 * 根据long值，返回两个时间相差多少(秒/分钟/小时/天)
	 * @param _lFirstTimes
	 * @param _lSecondTimes
	 * @param _lDateType：该值取DateUtil类中的：SECOND， MINUTE...
	 * @return
	 */
	public static long compare(long _lFirstTimes, long _lSecondTimes, long _lDateType){
		return (_lFirstTimes-_lSecondTimes)/_lDateType;
	}

	/**
	 * 时间格式化,输出String类型时间。
	 * 
	 * @return
	 */
	public static String getCurrDateStr(final String _formart, Date _date) {
		if (_date == null)
			_date = getDate();
		SimpleDateFormat sdf = new SimpleDateFormat(_formart);
		return sdf.format(_date);
	}
	
	public static String getDateStr(final String _formart, Date _date) {
		if (_date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(_formart);
		return sdf.format(_date);
	}
	

	public static Date getDateFromStr(final String _formart, String _sDateStr)
			throws Exception {
		if (_sDateStr == null)
			throw new Exception("待转换的时间不能为空");

		SimpleDateFormat sdf = new SimpleDateFormat(_formart);
		return sdf.parse(_sDateStr);
	}

	
	/**
	 * 当前年份
	 * 
	 * @return
	 */
	public static int getCurrYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	
	public static int getYear(Date _date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(_date);
		return cal.get(Calendar.YEAR);
	}
	
	/**
	 * 当前月份
	 * 
	 * @return
	 */
	public static int getCurrMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	/**
	 * 当前日期号
	 * 
	 * @return
	 */
	public static int getCurrDay() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取指定年，月的最末号数。
	 * 
	 * @param _nYear
	 * @param _nMonth
	 * @return
	 */
	public static int getActualMaximumDayOfMonth(int _nYear, int _nMonth) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, _nYear);
		cal.set(Calendar.MONTH, _nMonth - 1);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取月初日期
	 * 
	 * @param _nYear
	 * @param _nMonth
	 * @return
	 */
	public static Date getActualStartDate(int _nYear, int _nMonth) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, _nYear);
		cal.set(Calendar.MONTH, _nMonth - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}
	
	/**
	 *  获取月初日期
	 * @param _date
	 * @return
	 */
	public static Date getActualStartDate(Date _date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(_date);
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}
	
	/**
	 *  获取月末日期
	 * @param _nYear
	 * @param _nMonth
	 * @return
	 */
	public static Date getActualEndDate(int _nYear, int _nMonth) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, _nYear);
		cal.set(Calendar.MONTH, _nMonth - 1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}
	/**
	 * 根据给定日期，获取月末日期，且不改变给定日期的值
	 * @param d
	 * @return
	 */
	public static Date getActualEndDate(Date d){
		Calendar cal = Calendar.getInstance();
		Date t_d=(Date)d.clone();
		cal.setTime(t_d);
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}
	public static Date getDate(int _nYear, int _nMonth, int _nDay){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, _nYear);
		cal.set(Calendar.MONTH, _nMonth - 1);
		cal.set(Calendar.DAY_OF_MONTH, _nDay);
		return cal.getTime();
	}

	public static int getMonth(Date _date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(_date);
		return cal.get(Calendar.MONTH) + 1;
	}
	
	/**
	 * 日期相减的天数差。
	 * @param _startDate
	 * @param _endDate
	 * @return
	 */
	public static int getSubDay(Date _startDate, Date _endDate){
		long subSecond=(_startDate.getTime()-_endDate.getTime())/1000;//除以1000是为了转换成秒
		long day=subSecond/(24*3600);
		return new Long(day).intValue();
	}
	
	/**
	 * 日期相减的月份差。
	 * @param _startDate
	 * @param _endDate
	 * @return
	 */
	public static int getSubMonth(Date _startDate, Date _endDate){
		return getSubDay(_startDate, _endDate)/30;
	}
	
	/**
	 * 当前日期减去几天。
	 * @param _startDate 当前日期
	 * @param days 减去的天数
	 * @return
	 */
	public static Date getDateSubDays(Date _startDate, int days){
		return getDateAddDays(_startDate, -days);
	}
	
	/**
	 * 当前日期加上几天。
	 * @param _startDate 当前日期
	 * @param days 加上的天数
	 * @return
	 */
	public static Date getDateAddDays(Date _startDate, int days){
		 long myTime=(_startDate.getTime()/1000)+60*60*24*days; 
		 Date newDate = (Date)_startDate.clone();
		 newDate.setTime(myTime*1000); 
		 return newDate;
	}
	
	/**
	 * 两个日期比较大小
	 * @param _fristDate
	 * @param _secondDate
	 * @return
	 */
	public static int compareTo(Date _fristDate, Date _secondDate){
		return _fristDate.compareTo(_secondDate);
	}
	
	public static Date addOneDayCutOneYear(Date date){
		Date new_date = getDateAddDays(date,1);
		int year = new_date.getYear();
		int month = new_date.getMonth();
		int day = new_date.getDay();
		return new Date(year-1,month,day);
	}
	
	public static Date addOneYearCutOneDay(Date date){
		Date new_date = getDateSubDays(date,1);
		int year = new_date.getYear();
		int month = new_date.getMonth();
		int day = new_date.getDate();
		return new Date(year+1,month,day);
	}
	
	/**
	 * 
	 * @param year
	 * @return
	 * @throws Exception 
	 */
	public static Date intToYearDate(int year) throws Exception{
		String tem = year+"-01-01";
	         return getDateFromStr(DateUtil.formart3,tem);
	}
	
	
	/**
	 * 
	 * @param date
	 * @return
	 * @throws Exception 
	 */
	public static Date getQuarterStratDate(Date date) throws Exception{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, date.getYear()+1900);
		cal.set(Calendar.MONTH, ((date.getMonth())/3)*3);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}
	
	
	/**
	 * 
	 * @param date
	 * @return
	 * @throws Exception 
	 */
	public static Date getQuarterEndDate(Date date) throws Exception{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, date.getYear()+1900);
		cal.set(Calendar.MONTH, ((date.getMonth())/3)*3+2);
		//System.out.println(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		//System.out.println(cal.getMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}
	
	/**
	 * 
	 *  获取当天起始时间
	 */
	public static Date getStartTime(){
		Calendar todayStart = Calendar.getInstance();  
        todayStart.set(Calendar.HOUR_OF_DAY, 0);  
        todayStart.set(Calendar.MINUTE, 0);  
        todayStart.set(Calendar.SECOND, 0);  
        todayStart.set(Calendar.MILLISECOND, 0);  
        return todayStart.getTime();  
	}
	
	/**
	 * 
	 *  获取当天结束时间
	 */
	public static Date getEndTime(){
		Calendar todayEnd = Calendar.getInstance();  
        todayEnd.set(Calendar.HOUR_OF_DAY, 24);  
        todayEnd.set(Calendar.MINUTE, 0);  
        todayEnd.set(Calendar.SECOND, 0);  
        todayEnd.set(Calendar.MILLISECOND, 0);  
        return todayEnd.getTime(); 
	}
}
