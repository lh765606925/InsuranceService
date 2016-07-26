package com.insurance.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DateUtil {

	public static String formatDate(Date date, String format) {
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (date != null) {
			result = sdf.format(date);
		}
		return result;
	}

	/**
	 * 通过生日获取当前用户年龄
	 * 
	 * @param birthday
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static Long getAgeByBirthDay(String birthday) throws Exception {
		String t1 = getCurrentDateStr1().substring(0, 9).replace('-', '/');
		Date dt1 = new Date(t1);
		String t2 = birthday.substring(0, 9).replace('-', '/');
		Date dt2 = new Date(t2);
		long i = (dt1.getTime() - dt2.getTime()) / (1000 * 60 * 60 * 24);
		long a = i / 365;
		return a;
	}

	/**
	 * 根据当前时间获取16位时间数
	 * 
	 * @param date
	 * @return
	 */
	public static String GetDateRandom16() {

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String strData = sdf.format(date);
		Random r = new Random();
		strData = strData + r.nextInt(100000000);
		return strData;
	}

	public static Date formatString(String str, String format) throws Exception {
		if (StringUtil.isEmpty(str)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(str);
	}

	/**
	 * 获取当前时间字符串 SimpleDateFormat("yyyyMMddhhmmss");
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getCurrentDateStr() throws Exception {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date);
	}
	/**
	 * 获取当前时间字符串 SimpleDateFormat("yyMMdd");
	 * @return
	 * @throws Exception
	 */
	public static String getCurrentDateStr6() throws Exception {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		return sdf.format(date);
	}
	
	public static String getCurrentDateRandomStr() throws Exception {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date);
	}

	/**
	 * 获取当前时间字符串 SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getCurrentDateStr1() throws Exception {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * 获取当前日期datekey()
	 * @return
	 */
	public static String getDateKey() {
		Date date = new Date();
		return String.valueOf(date.getTime());
	}

	public static String getNextDate(int i) {
		try {
			// 获取当前日期
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String nowDate = sf.format(date);
			// 通过日历获取下一天日期
			Calendar cal = Calendar.getInstance();
			cal.setTime(sf.parse(nowDate));
			cal.add(Calendar.DAY_OF_YEAR, +i);
			String nextDate_1 = sf.format(cal.getTime());
			return nextDate_1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println(GetDateRandom16());
		System.out.println(getAgeByBirthDay("1989-02-05").toString());
		System.out.println(GetDateRandom16());
		System.out.println(getAgeByBirthDay("1989-02-05").toString());
		System.out.println(GetDateRandom16());
		System.out.println(getAgeByBirthDay("1989-02-05").toString());
		System.out.println(GetDateRandom16());
		System.out.println(getAgeByBirthDay("1989-02-05").toString());
		System.out.println(GetDateRandom16());
		System.out.println(getAgeByBirthDay("1989-02-05").toString());
		System.out.println(GetDateRandom16());
		System.out.println(getAgeByBirthDay("1989-02-05").toString());
		System.out.println(GetDateRandom16());
		System.out.println(getAgeByBirthDay("1989-02-05").toString());
		System.out.println(GetDateRandom16());
		System.out.println(getAgeByBirthDay("1989-02-05").toString());
		long day = getAgeByBirthDay("1989-02-05");
		long a = day / 365;
		System.out.println(a);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = sdf.parse("2012-09-08 10:10:10");
		Date d2 = sdf.parse("2012-09-15 00:00:00");
		System.out.println(daysBetween(d1, d2));

		System.out.println(daysBetween("2012-09-08 10:10:10", "2012-09-15 00:00:00"));
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 字符串的日期格式的计算
	 */
	public static int daysBetween(String smdate, String bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

}
