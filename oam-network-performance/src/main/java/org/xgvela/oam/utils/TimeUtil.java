package org.xgvela.oam.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeUtil {

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String getFormatDateTime(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date).trim();
	}

	public static Date getDateFromString(String dateStr, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date resDate = null;
		try {
			resDate = sdf.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resDate;
	}

	public static String dateTimeToStr(Long time) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = "";
		try {
			dateString = formatter.format(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateString;
	}

	public static String getFormatDateAdd(Date date, int amount, String format) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(5, amount);
		return getFormatDateTime(cal.getTime(), format);
	}

	public static String getCurrentTime() {
		return getFormatDateTime(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	public static String getYestoday(String sourceDate, String format) {
		return getFormatDateAdd(getDateFromString(sourceDate, format), -1, format);
	}

	/**
	 * get system time
	 *
	 * @return Date
	 */
	public static Date newDate() {

		Date time = null;
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 可以方便地修改日期格式

		try {
			time = dateFormat.parse(dateFormat.format(now));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return time;
	}

	/**
	 * date format to yyyy-MM-dd HH:mm:ss
	 *
	 * @param dateDate
	 * @return
	 */
	public static String dateToStrLong(Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	public static Date getDayAfterDate(int day){
		long time = System.currentTimeMillis() + 1000L * 24 * 3600 * day;
		return new Date(time);
	}

	public static Date getHourOfTimes(Date now, int hour) {
		Date time = null;

		try {
			Date expireTime = new Date(now.getTime() + hour * 1000 * 60 * 60);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			time = df.parse(df.format(expireTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}

	public static String dateToStamp(String s) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = simpleDateFormat.parse(s);
		long ts = date.getTime();
		return String.valueOf(ts);
	}

	public static long getTimeStr(Long milSec){
		long dayTime = 1000l * 24 * 3600;
		long day = milSec/dayTime;

		return day ;
	}
}
