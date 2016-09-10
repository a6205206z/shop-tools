package com.xmm.shoptools.backend.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static DateFormat df_day = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat df_join = new SimpleDateFormat("yyyyMMddHHmmss");
	private static DateFormat df_ym = new SimpleDateFormat("yyyyMM");
	private static DateFormat df_ymd = new SimpleDateFormat("yyyyMMdd");
	private static DateFormat df_md = new SimpleDateFormat("MM-dd");
	private static DateFormat df_hm = new SimpleDateFormat("HH:mm");
	private static DateFormat df_md_hm = new SimpleDateFormat("MM-dd HH:mm");
	private static DateFormat cndf = new SimpleDateFormat("yyyy年MM月dd日");
	private static DateFormat df_mmdd = new SimpleDateFormat("MM月dd日HH:mm");
	private static DateFormat df_yymd = new SimpleDateFormat("yyMMdd");
	private static DateFormat df_yyyy = new SimpleDateFormat("yyyy");
	private static DateFormat df_Md = new SimpleDateFormat("MMdd");

	private static Calendar defCalendar() {
		return Calendar.getInstance();
	}

	public static int getDate() {
		return defCalendar().get(Calendar.DATE);
	}

	public static int getHourOfDay() {
		return defCalendar().get(Calendar.HOUR_OF_DAY);
	}

	public static Date getTime(Integer hour, Integer min) {
		Calendar c = defCalendar();
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, min);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static Date queryDate(Date psd) {
		Calendar c = defCalendar();
		c.setTime(psd);
		c.add(Calendar.DAY_OF_YEAR, -1);
		return c.getTime();
	}

	public static Date addDate(Date where, int days) {
		Calendar c = defCalendar();
		c.setTime(where);
		c.add(Calendar.DAY_OF_YEAR, days);
		return c.getTime();
	}

	public static Date getLastDay(int days) {
		Calendar c = defCalendar();
		c.add(Calendar.DAY_OF_YEAR, -days);
		return c.getTime();
	}
	
	public static Date addDay(int days) {
		Calendar c = defCalendar();
		c.add(Calendar.DAY_OF_YEAR, days);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	public static Date queryDate(Date psd, Integer min) {
		Calendar c = defCalendar();
		c.setTime(psd);
		c.add(Calendar.MINUTE, min);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	public static String getLastHalfHour(Integer min) {
		Calendar c = defCalendar();
		c.add(Calendar.MINUTE, min);
		c.set(Calendar.SECOND, 0);
		return df.format(c.getTime());
	}

	/**
	 * @param strdate
	 * @return
	 */
	public static Date strToDate(String strdate) {
		Date date = null;
		try {
			date = df.parse(strdate);
		} catch (ParseException e) {
		}
		return date;
	}

	/**
	 * 根据strdate生成时间(strdate为时间格式字符串 yyyy-MM-dd HH:mm:ss)
	 * 
	 * @param strdate
	 * @return
	 */
	public static Date strToDate(String strdate, String format) {
		DateFormat df = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = df.parse(strdate);
		} catch (ParseException e) {
		}
		return date;
	}

	/**
	 * 日期转字符串
	 * 
	 * @param time
	 * @return
	 */
	public static String formatstr(Date time) {
		return df.format(time);
	}

	public static String formatToday() {
		return df_day.format(getLastDay(0));
	}

	public static Long formatDayZero() {
		Calendar c = defCalendar();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime().getTime();
	}
	
	public static Long formatDayMax() {
		Calendar c = defCalendar();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime().getTime();
	}

	public static String formatstr() {
		return df_yymd.format(new Date());
	}

	public static String formatAllstr(Date time) {
		return df_join.format(time);
	}

	public static String formatYMstr(Date d) {
		return df_ym.format(d);
	}
	
	public static String formatYMstr() {
		return df_ym.format(new Date());
	}

	public static String formatMDstr(Date date) {
		return df_md.format(date);
	}

	public static String formatMDHMstr(Date date) {
		return df_md_hm.format(date);
	}

	public static String formatHMstr(Date date) {
		return df_hm.format(date);
	}

	public static String formatCNstr(Date date) {
		return cndf.format(date);
	}
	public static String formatMMDDstr(Date date) {
		return df_mmdd.format(date);
	}

	/**
	 * 返回距下一次执行的最近时间（单位:分）
	 * 
	 * @param cycle
	 * @param t
	 * @return
	 */
	public static long computeInitialDelay(int cycle) {
		cycle = cycle > 0 ? cycle : 1;

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);

		long delay = cal.getTimeInMillis() - System.currentTimeMillis();

		// System.out.println(new Date(cal.getTimeInMillis()));
		// System.out.println("到24点:" + delay);
		// System.out.println("分钟间隔:" + 24 * 60 / cycle);

		return delay / (1000 * 60) % (24 * 60 / cycle);
	}

	/**
	 * 获取当前时间的字符串形式(yyyy-MM-dd HH:mm:ss)
	 * 
	 * @return
	 */
	public static String getNow() {
		return df.format(new Date());
	}

	public static boolean isDifMonth(Date datetime) {
		String theDate = df_ym.format(datetime);
		String tdate = df_ym.format(new Date());
		if (theDate.equals(tdate))
			return true;
		return false;
	}

	/***
	 * 是否是今天
	 */
	public static boolean isToday(Date datetime) {
		String theDate = df_ymd.format(datetime);
		String tdate = df_ymd.format(new Date());
		if (theDate.equals(tdate))
			return true;
		return false;
	}
	
	/** 
    * 判断给定日期是否为月末*天
    * 
    * @param date 
    * @return true:是|false:不是 
    */ 
    public static boolean isLastDayOfMonth(Integer days) { 
    	
        boolean flag = false;
        for (int i = 1; i <= days; i++) {
        	Calendar calendar = Calendar.getInstance(); 
        	calendar.setTime(new Date()); 
        	calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + i)); 
//        	System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
            if (calendar.get(Calendar.DAY_OF_MONTH) == 1) { 
            	flag =  true; 
            	break;
            } 
		}
        return flag; 
    } 

	/**
	 * 求日期相隔天数
	 * 
	 * @param smdate
	 * @param bdate
	 * @return
	 * @version v1.0
	 */
	public static int daysBetween(Date start, Date end) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			start = sdf.parse(sdf.format(start));
			end = sdf.parse(sdf.format(end));
		} catch (ParseException e) {
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(start);
		long time1 = cal.getTimeInMillis();
		cal.setTime(end);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	public static String getFirstDay() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		return df_day.format(c.getTime());
	}
	
	public static String getYyyy(Date theDate) {
		return df_yyyy.format(theDate);
	}
	
	public static String getMMdd(Date theDate) {
		return df_Md.format(theDate);
	}

	public static void main(String[] s) {
		System.out.println(isLastDayOfMonth(14));
	}
}