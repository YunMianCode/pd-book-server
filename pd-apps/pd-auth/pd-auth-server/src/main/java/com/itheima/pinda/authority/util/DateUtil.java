package com.itheima.pinda.authority.util;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 日期工具类
 *
 * @author ZhangJun
 * @date 2020年11月17日
 **/
public class DateUtil {
	/**
	 * 本日日期和时间YYYY-MM-DD HH:MM:SS
	 *
	 * @return
	 */
	public static String getTodayTime()
	{
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		return sFormat.format(date);
	}

	/**
	 * 从"YYYY-MM-DD HH:mm:ss"日期字符串得到日期类
	 *
	 * @param sDate
	 * @return
	 */
	public static Date parseDateTime(String sDate)
	{
		if( sDate==null || sDate.trim().length()==0)
			return null;
		try
		{
			Date d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sDate);
			return d1;
		} catch (Exception ex)
		{
			return null;
		}
	}
	/**
	 * 对日期进行操作
	 * <p>当仅对日期进行操作, 不涉及时间时, 使用此类;
	 * <p>在此类的方法中涉及日期类的使用{@link LocalDate} 作为输入输出参数;
	 * 涉及字符串日期时, 则默认为{@code yyyy-MM-dd} 格式;
	 */
	public static class OperDate {
		/**
		 * 日期格式(yyyy-MM-dd)
		 * ISO_LOCAL_DATE
		 */
		public final static String DATE_PATTERN = "yyyy-MM-dd";
		/**
		 * 日期格式(yyyyMMdd)
		 */
		public final static String DATE_PATTERN_UNSIGNED = "yyyyMMdd";



		/**
		 * 将指定格式将字符串解析为LocalDate
		 *
		 * @param dateStr 日期字符串
		 * @param pattern 日期格式
		 *
		 * @return LocalDate
		 */
		public static LocalDate parse(String dateStr, String pattern) {
			if (StringUtils.isEmpty(pattern)) {
				pattern = DATE_PATTERN;
			}
			if (dateStr != null) {
				return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
			}
			return null;
		}

		/**
		 * 将yyyy-MM-dd 格式将字符串解析为LocalDate
		 *
		 * @param dateStr 日期字符串
		 *
		 * @return LocalDate
		 */
		public static LocalDate parse(String dateStr) {
			return parse(dateStr, DATE_PATTERN);
		}

		/**
		 * 按照所需格式将LocalDate 格式化为字符串
		 *
		 * @param date    LocalDate 本地日期
		 * @param pattern 日期格式
		 *
		 * @return 格式化后的日期字符串
		 */
		public static String format(LocalDate date, String pattern) {
			if (StringUtils.isEmpty(pattern)) {
				pattern = DATE_PATTERN;
			}
			if (date != null) {
				return date.format(DateTimeFormatter.ofPattern(pattern));
			}
			return null;
		}
		/**
		 * 将originPattern 格式的日期时间字符串, 转换成targetPattern 格式的日期字符串
		 *
		 * @param dateStr       日期时间字符串
		 * @param originPattern 源格式
		 * @param targetPattern 目标格式
		 *
		 * @return 目标格式的日期时间字符串
		 */
		public static String formatString(String dateStr, String originPattern, String targetPattern) {
			return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(originPattern)).format(DateTimeFormatter.ofPattern(targetPattern));
		}

		/**
		 * 将LocalDate 格式化为yyyy-MM-dd 字符串
		 *
		 * @param date LocalDate 本地日期
		 *
		 * @return yyyy-MM-dd 字符串
		 */
		public static String formatDate(LocalDate date) {
			return format(date, DATE_PATTERN);
		}

		/**
		 * 将LocalDate 格式化为yyyyMMdd 字符串
		 *
		 * @param date LocalDate 本地日期
		 *
		 * @return yyyyMMdd 字符串
		 */
		public static String formatDateUnsigned(LocalDate date) {
			return format(date, DATE_PATTERN_UNSIGNED);
		}

		/**
		 * 指定日期增减天数后的日期字符串
		 *
		 * @param date    LocalDate 本地日期
		 * @param adjust  调整的值
		 * @param pattern 日期格式
		 *
		 * @return 调整后的字符串
		 */
		public static String adjustDay(LocalDate date, int adjust, String pattern) {
			if (date == null) {
				date = LocalDate.now();
			}
			return format(date.plusDays(adjust), pattern);
		}

		/**
		 * 指定日期增减年后的日期字符串
		 *
		 * @param date    LocalDate 本地日期
		 * @param adjust  调整的值
		 * @param pattern 日期格式
		 *
		 * @return 调整后的日期字符串
		 */
		public static String adjustYears(LocalDate date, int adjust, String pattern) {
			if (date == null) {
				date = LocalDate.now();
			}
			return format(date.plusYears(adjust), pattern);
		}

		/**
		 * 指定日期增减月后的日期字符串
		 *
		 * @param date    LocalDate 本地日期
		 * @param adjust  调整的值
		 * @param pattern 日期格式
		 *
		 * @return 调整后的日期字符串
		 */
		public static String adjustMonths(LocalDate date, int adjust, String pattern) {
			if (date == null) {
				date = LocalDate.now();
			}
			return format(date.plusMonths(adjust), pattern);
		}

		/**
		 * 指定日期增减周后的日期字符串
		 *
		 * @param date    LocalDate 本地日期
		 * @param adjust  调整的值
		 * @param pattern 日期格式
		 *
		 * @return 调整后的日期字符串
		 */
		public static String adjustWeeks(LocalDate date, int adjust, String pattern) {
			if (date == null) {
				date = LocalDate.now();
			}
			return format(date.plusWeeks(adjust), pattern);
		}

		/**
		 * 获得指定格式当前日期字符串
		 *
		 * @param pattern 日期格式
		 *
		 * @return 定义的日期格式
		 */
		public static String getToday(String pattern) {
			return format(LocalDate.now(), pattern);
		}

		/**
		 * 获取本日日期 yyyy-MM-dd
		 *
		 * @return 本日日期 yyyy-MM-dd
		 */
		public static String getToday() {
			return getToday(DATE_PATTERN);
		}

		/**
		 * 获取昨日日期 yyyy-MM-dd
		 *
		 * @return yyyy-MM-dd 日期字符串
		 */
		public static String getYesterday() {
			return adjustDay(LocalDate.now(), -1, DATE_PATTERN);
		}

		/**
		 * 获取前日日期 yyyy-MM-dd
		 *
		 * @return yyyy-MM-dd 日期字符串
		 */
		public static String getDayBeforeYesterday() {
			return adjustDay(LocalDate.now(), -2, DATE_PATTERN);
		}

		/**
		 * 获取上周日期 yyyy-MM-dd
		 *
		 * @return yyyy-MM-dd 日期字符串
		 */
		public static String getDayLastWeek() {
			return adjustWeeks(LocalDate.now(), -1, DATE_PATTERN);
		}

		/**
		 * 获取下周日期 yyyy-MM-dd
		 *
		 * @return yyyy-MM-dd 日期字符串
		 */
		public static String getDayNextWeek() {
			return adjustWeeks(LocalDate.now(), 1, DATE_PATTERN);
		}

		/**
		 * 获取给定日期所在月的最后一天
		 *
		 * @param date LocalDate 本地日期时间
		 *
		 * @return yyyy-MM-dd 日期字符串
		 */
		public static String getLastDayOfMonth(LocalDate date) {
			return date.with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.ofPattern(DATE_PATTERN));
		}

		/**
		 * 获取本月的最后一天
		 *
		 * @return yyyy-MM-dd 日期字符串
		 */
		public static String getLastDayOfMonth() {
			return getLastDayOfMonth(LocalDate.now());
		}

		/**
		 * 获取指定年月的最后一天
		 *
		 * @param year  年份
		 * @param month 月份
		 *
		 * @return yyyy-MM-dd 日期字符串
		 */
		public static String getLastDayOfMonth(int year, int month) {
			return getLastDayOfMonth(LocalDate.now().withYear(year).withMonth(month));
		}

		/**
		 * 获取给定日期所在月的第一个星期几
		 *
		 * @param date LocalDate 本地日期
		 * @param day  DayOfWeek 星期枚举
		 *
		 * @return yyyy-MM-dd 日期字符串
		 */
		public static String getFirstInMonth(LocalDate date, DayOfWeek day) {
			return date.with(TemporalAdjusters.firstInMonth(day)).format(DateTimeFormatter.ofPattern(DATE_PATTERN));
		}

		/**
		 * 获取本月的第一个星期几
		 *
		 * @param day DayOfWeek 星期枚举
		 *
		 * @return yyyy-MM-dd 日期字符串
		 */
		public static String getFirstInMonth(DayOfWeek day) {
			return getFirstInMonth(LocalDate.now(), day);
		}

		/**
		 * 获取指定年月的第一个星期几
		 *
		 * @param year  年份
		 * @param month 月份
		 * @param day   DayOfWeek 星期枚举
		 *
		 * @return yyyy-MM-dd 日期字符串
		 */
		public static String getFirstInMonth(int year, int month, DayOfWeek day) {
			return getFirstInMonth(LocalDate.now().withYear(year).withMonth(month), day);
		}

		/**
		 * 获取给定日期所在月的最后一个星期几
		 *
		 * @param date LocalDate 本地日期时间
		 * @param day  DayOfWeek 星期枚举
		 *
		 * @return yyyy-MM-dd 日期字符串
		 */
		public static String getLastInMonth(LocalDate date, DayOfWeek day) {
			return date.with(TemporalAdjusters.lastInMonth(day)).format(DateTimeFormatter.ofPattern(DATE_PATTERN));
		}

		/**
		 * 获取本月的最后一个星期几
		 *
		 * @param day DayOfWeek 星期枚举
		 *
		 * @return yyyy-MM-dd 日期字符串
		 */
		public static String getLastInMonth(DayOfWeek day) {
			return getLastInMonth(LocalDate.now(), day);
		}

		/**
		 * 获取指定年月的最后一个星期几
		 *
		 * @param year  年份
		 * @param month 月份
		 * @param day   DayOfWeek 星期枚举
		 *
		 * @return yyyy-MM-dd 日期字符串
		 */
		public static String getLastInMonth(int year, int month, DayOfWeek day) {
			return getLastInMonth(LocalDate.now().withYear(year).withMonth(month), day);
		}

		/**
		 * 获取指定日期后一个最近(包括当天)的星期几的日期
		 *
		 * @param date LocalDate 本地日期
		 * @param day  DayOfWeek 星期枚举
		 *
		 * @return yyyy-MM-dd 日期字符串
		 */
		public static String getNextDayOfWeek(LocalDate date, DayOfWeek day) {
			return date.with(TemporalAdjusters.nextOrSame(day)).format(DateTimeFormatter.ofPattern(DATE_PATTERN));
		}

		/**
		 * 获取后一个最近(包括当天)的星期几的日期
		 *
		 * @param day DayOfWeek 星期枚举
		 *
		 * @return yyyy-MM-dd 日期字符串
		 */
		public static String getNextDayOfWeek(DayOfWeek day) {
			return getNextDayOfWeek(LocalDate.now(), day);
		}

		/**
		 * 获取指定日期前一个最近(包括当天)的星期几的日期
		 *
		 * @param date LocalDate 本地日期时间
		 * @param day  DayOfWeek 星期枚举
		 *
		 * @return yyyy-MM-dd 日期字符串
		 */
		public static String getPreviousDayOfWeek(LocalDate date, DayOfWeek day) {
			return date.with(TemporalAdjusters.previousOrSame(day)).format(DateTimeFormatter.ofPattern(DATE_PATTERN));
		}

		/**
		 * 获取前一个最近(包括当天)的星期几的日期
		 *
		 * @param day DayOfWeek 星期枚举
		 *
		 * @return yyyy-MM-dd 日期字符串
		 */
		public static String getPreviousDayOfWeek(DayOfWeek day) {
			return getPreviousDayOfWeek(LocalDate.now(), day);
		}

		/**
		 * 获得指定日期时间为星期几
		 *
		 * @param date LocalDate
		 *
		 * @return DayOfWeek 枚举 e.g. SUNDAY
		 */
		public static DayOfWeek getDayOfWeek(LocalDate date) {
			return date.getDayOfWeek();
		}

		/**
		 * 获得指定日期时间为星期几
		 *
		 * @param date LocalDate
		 *
		 * @return 星期编号 星期一 to 星期日 对应 1 to 7
		 */
		public static int getDayOfWeekIndex(LocalDate date) {
			return date.getDayOfWeek().getValue();
		}

		/**
		 * 获得指定日期时间为星期几
		 *
		 * @param date LocalDate
		 *
		 * @return 星期中文 星期一 to 星期日
		 */
		public static String getDayOfWeekStr(LocalDate date) {
			return date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.CHINA);
		}

		/**
		 * 获得当前为星期几
		 *
		 * @return DayOfWeek 枚举 e.g. SUNDAY
		 */
		public static DayOfWeek getDayOfWeek() {
			return getDayOfWeek(LocalDate.now());
		}

		/**
		 * 获得当前为星期几
		 *
		 * @return 星期编号 星期一 to 星期日 对应 1 to 7
		 */
		public static int getDayOfWeekIndex() {
			return getDayOfWeekIndex(LocalDate.now());
		}

		/**
		 * 获得当前为星期几
		 *
		 * @return 星期中文 星期一 to 星期日
		 */
		public static String getDayOfWeekStr() {
			return getDayOfWeekStr(LocalDate.now());
		}

		/**
		 * 获取两个日期之间的时长-天
		 *
		 * @param fromDate 开始日期
		 * @param toDate   结束日期
		 *
		 * @return 经过的时长
		 */
		public static long getDurationDays(LocalDate fromDate, LocalDate toDate) {
			return Duration.between(fromDate.atStartOfDay(), toDate.plusDays(1L).atStartOfDay()).toDays();
		}

		/**
		 * 获取两个日期之间的时长-小时
		 *
		 * @param fromDate 开始日期
		 * @param toDate   结束日期
		 *
		 * @return 经过的时长
		 */
		public static long getDurationHours(LocalDate fromDate, LocalDate toDate) {
			return Duration.between(fromDate.atStartOfDay(), toDate.plusDays(1L).atStartOfDay()).toHours();
		}

		/**
		 * 获取两个日期之间的时长-分钟
		 *
		 * @param fromDate 开始日期
		 * @param toDate   结束日期
		 *
		 * @return 经过的时长
		 */
		public static long getDurationMinutes(LocalDate fromDate, LocalDate toDate) {
			return Duration.between(fromDate.atStartOfDay(), toDate.plusDays(1L).atStartOfDay()).toMinutes();
		}

		/**
		 * 获取两个日期之间的时长-毫秒
		 *
		 * @param fromDate 开始日期
		 * @param toDate   结束日期
		 *
		 * @return 经过的时长
		 */
		public static long getDurationMillis(LocalDate fromDate, LocalDate toDate) {
			return Duration.between(fromDate.atStartOfDay(), toDate.plusDays(1L).atStartOfDay()).toMillis();
		}

		/**
		 * 获取两个日期之间的时长-天
		 *
		 * @param fromStr 开始日期 yyyy-MM-dd
		 * @param toStr   结束日期 yyyy-MM-dd
		 *
		 * @return 经过的时长
		 */
		public static long getDurationDays(String fromStr, String toStr) {

			LocalDate fromDate = LocalDate.parse(fromStr, DateTimeFormatter.ofPattern(DATE_PATTERN));
			LocalDate toDate = LocalDate.parse(toStr, DateTimeFormatter.ofPattern(DATE_PATTERN));

			return Duration.between(fromDate.atStartOfDay(), toDate.plusDays(1L).atStartOfDay()).toDays();
		}

		/**
		 * 获取两个日期之间的时长-小时
		 *
		 * @param fromStr 开始日期 yyyy-MM-dd
		 * @param toStr   结束日期 yyyy-MM-dd
		 *
		 * @return 经过的时长
		 */
		public static long getDurationHours(String fromStr, String toStr) {

			LocalDate fromDate = LocalDate.parse(fromStr, DateTimeFormatter.ofPattern(DATE_PATTERN));
			LocalDate toDate = LocalDate.parse(toStr, DateTimeFormatter.ofPattern(DATE_PATTERN));

			return Duration.between(fromDate.atStartOfDay(), toDate.plusDays(1L).atStartOfDay()).toHours();
		}

		/**
		 * 获取两个日期之间的时长-分钟
		 *
		 * @param fromStr 开始日期 yyyy-MM-dd
		 * @param toStr   结束日期 yyyy-MM-dd
		 *
		 * @return 经过的时长
		 */
		public static long getDurationMinutes(String fromStr, String toStr) {

			LocalDate fromDate = LocalDate.parse(fromStr, DateTimeFormatter.ofPattern(DATE_PATTERN));
			LocalDate toDate = LocalDate.parse(toStr, DateTimeFormatter.ofPattern(DATE_PATTERN));

			return Duration.between(fromDate.atStartOfDay(), toDate.plusDays(1L).atStartOfDay()).toMinutes();
		}

		/**
		 * 获取两个日期之间的时长-毫秒
		 *
		 * @param fromStr 开始日期 yyyy-MM-dd
		 * @param toStr   结束日期 yyyy-MM-dd
		 *
		 * @return 经过的时长
		 */
		public static long getDurationMillis(String fromStr, String toStr) {

			LocalDate fromDate = LocalDate.parse(fromStr, DateTimeFormatter.ofPattern(DATE_PATTERN));
			LocalDate toDate = LocalDate.parse(toStr, DateTimeFormatter.ofPattern(DATE_PATTERN));

			return Duration.between(fromDate.atStartOfDay(), toDate.plusDays(1L).atStartOfDay()).toMillis();
		}

		/**
		 * 获取两个日期之间的时长-天
		 *
		 * @param fromStr 开始日期 yyyy-MM-dd
		 * @param toStr   结束日期 yyyy-MM-dd
		 * @param pattern 日期格式
		 *
		 * @return 经过的时长
		 */
		public static long getDurationDays(String fromStr, String toStr, String pattern) {

			LocalDate fromDate = LocalDate.parse(fromStr, DateTimeFormatter.ofPattern(pattern));
			LocalDate toDate = LocalDate.parse(toStr, DateTimeFormatter.ofPattern(pattern));

			return Duration.between(fromDate.atStartOfDay(), toDate.plusDays(1L).atStartOfDay()).toDays();
		}

		/**
		 * 获取两个日期之间的时长-小时
		 *
		 * @param fromStr 开始日期 yyyy-MM-dd
		 * @param toStr   结束日期 yyyy-MM-dd
		 * @param pattern 日期格式
		 *
		 * @return 经过的时长
		 */
		public static long getDurationHours(String fromStr, String toStr, String pattern) {

			LocalDate fromDate = LocalDate.parse(fromStr, DateTimeFormatter.ofPattern(pattern));
			LocalDate toDate = LocalDate.parse(toStr, DateTimeFormatter.ofPattern(pattern));

			return Duration.between(fromDate.atStartOfDay(), toDate.plusDays(1L).atStartOfDay()).toHours();
		}

		/**
		 * 获取两个日期之间的时长-分钟
		 *
		 * @param fromStr 开始日期 yyyy-MM-dd
		 * @param toStr   结束日期 yyyy-MM-dd
		 * @param pattern 日期格式
		 *
		 * @return 经过的时长
		 */
		public static long getDurationMinutes(String fromStr, String toStr, String pattern) {

			LocalDate fromDate = LocalDate.parse(fromStr, DateTimeFormatter.ofPattern(pattern));
			LocalDate toDate = LocalDate.parse(toStr, DateTimeFormatter.ofPattern(pattern));

			return Duration.between(fromDate.atStartOfDay(), toDate.plusDays(1L).atStartOfDay()).toMinutes();
		}

		/**
		 * 获取两个日期之间的时长-毫秒
		 *
		 * @param fromStr 开始日期 yyyy-MM-dd
		 * @param toStr   结束日期 yyyy-MM-dd
		 * @param pattern 日期格式
		 *
		 * @return 经过的时长
		 */
		public static long getDurationMillis(String fromStr, String toStr, String pattern) {

			LocalDate fromDate = LocalDate.parse(fromStr, DateTimeFormatter.ofPattern(pattern));
			LocalDate toDate = LocalDate.parse(toStr, DateTimeFormatter.ofPattern(pattern));

			return Duration.between(fromDate.atStartOfDay(), toDate.plusDays(1L).atStartOfDay()).toMillis();
		}

		/**
		 * 获取两个日期之间的每一日期(含头不含尾)
		 *
		 * @param fromStr 开始日期 yyyy-MM-dd
		 * @param toStr   结束日期 yyyy-MM-dd
		 *
		 * @return 经过的时长
		 */
		public static List<String> getDurationDayList(String fromStr, String toStr) {

			LocalDate fromDate = LocalDate.parse(fromStr);
			LocalDate toDate = LocalDate.parse(toStr);
			LocalDate temp = fromDate;

			if (toDate.isBefore(fromDate)) {
				fromDate = toDate;
				toDate = temp;
				temp = fromDate;
			}


			List<String> dateStrList = new ArrayList<>(16);
			while (temp.isBefore(toDate)) {
				dateStrList.add(temp.format(DateTimeFormatter.ofPattern(DATE_PATTERN)));
				temp = temp.plusDays(1);
			}
			dateStrList.add(toDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN)));

			return dateStrList;
		}

		/**
		 * 每月天数
		 *
		 * @param year  年份
		 * @param month 月份
		 *
		 * @return 天数
		 */
		public static Integer getMonthLength(long year, int month) {
			return Month.of(month).length(Year.isLeap(year));
		}

		/**
		 * 判断日期时间是否在特定范围内
		 *
		 * @param date     需要判断的日期
		 * @param fromDate 开始日期
		 * @param toDate   结束日期
		 *
		 * @return 是否在范围内
		 */
		public static Boolean isBetween(LocalDate date, LocalDate fromDate, LocalDate toDate) {
			// 同时晚于两个节点时间自然不在范围内
			boolean allAfterFlag = date.isAfter(fromDate) && date.isAfter(toDate);
			// 同时早于两个节点时间当然也不在范围内
			boolean allBeforeFlag = date.isBefore(fromDate) && date.isBefore(toDate);

			return !(allAfterFlag && allBeforeFlag);
		}

		/**
		 * 判断日期时间是否在特定范围内
		 *
		 * @param date    需要判断的日期
		 * @param fromStr 开始日期 yyyy-MM-dd
		 * @param toStr   结束日期 yyyy-MM-dd
		 *
		 * @return 是否在范围内
		 */
		public static Boolean isBetween(LocalDate date, String fromStr, String toStr) {
			LocalDate fromDate = LocalDate.parse(fromStr, DateTimeFormatter.ofPattern(DATE_PATTERN));
			LocalDate toDate = LocalDate.parse(toStr, DateTimeFormatter.ofPattern(DATE_PATTERN));

			return isBetween(date, fromDate, toDate);
		}

		/**
		 * 判断当前日期时间是否在特定范围内
		 *
		 * @param fromStr 开始日期 yyyy-MM-dd
		 * @param toStr   结束日期 yyyy-MM-dd
		 *
		 * @return 是否在范围内
		 */
		public static Boolean isBetween(String fromStr, String toStr) {
			LocalDate date = LocalDate.now();

			return isBetween(date, fromStr, toStr);
		}

	}


	/**
	 * 对日期时间进行操作
	 */
	public static class OperDateTime {
		/**
		 * 时间格式(HH:mm:ss)
		 */
		public final static String TIME_PATTERN = "HH:mm:ss";
		/**
		 * 时间格式(HHmmss)
		 */
		public final static String TIME_PATTERN_UNSIGNED = "HHmmss";
		/**
		 * 日期时间格式(yyyy-MM-dd HH:mm:ss)
		 * ISO_LOCAL_DATE_TIME
		 */
		public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";


		/**
		 * 将指定格式将字符串解析为LocalDateTime
		 *
		 * @param dateTimeStr 日期时间字符串
		 * @param pattern     日期格式
		 *
		 * @return LocalDateTime
		 */
		public static LocalDateTime parse(String dateTimeStr, String pattern) {
			if (StringUtils.isEmpty(pattern)) {
				pattern = DATE_TIME_PATTERN;
			}
			if (dateTimeStr != null) {
				return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(pattern));
			}
			return null;
		}

		/**
		 * 将yyyy-MM-dd HH:mm:ss 格式将字符串解析为LocalDateTime
		 *
		 * @param dateTimeStr 日期字符串
		 *
		 * @return LocalDateTime
		 */
		public static LocalDateTime parse(String dateTimeStr) {
			return parse(dateTimeStr, DATE_TIME_PATTERN);
		}

		/**
		 * 按照所需格式将LocalDateTime 格式化为字符串
		 *
		 * @param dateTime LocalDateTime 本地日期时间
		 * @param pattern  日期格式
		 *
		 * @return 格式化后的日期时间字符串
		 */
		public static String format(LocalDateTime dateTime, String pattern) {
			if (StringUtils.isEmpty(pattern)) {
				pattern = DATE_TIME_PATTERN;
			}
			if (dateTime != null) {
				return dateTime.format(DateTimeFormatter.ofPattern(pattern));
			}
			return null;
		}

		/**
		 * 将originPattern 格式的日期时间字符串, 转换成targetPattern 格式的日期时间字符串
		 *
		 * @param dateStr       日期时间字符串
		 * @param originPattern 源格式
		 * @param targetPattern 目标格式
		 *
		 * @return 目标格式的日期时间字符串
		 */
		public static String formatString(String dateStr, String originPattern, String targetPattern) {
			return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(originPattern)).format(DateTimeFormatter.ofPattern(targetPattern));
		}

		/**
		 * 将LocalDateTime 格式化为yyyy-MM-dd HH:mm:ss 字符串
		 *
		 * @param dateTime LocalDateTime 本地日期时间
		 *
		 * @return yyyy-MM-dd HH:mm:ss 字符串
		 */
		public static String formatDateTime(LocalDateTime dateTime) {
			return format(dateTime, DATE_TIME_PATTERN);
		}

		/**
		 * 指定日期时间增减天数后的日期时间字符串
		 *
		 * @param dateTime LocalDateTime 本地日期时间
		 * @param adjust   调整的值
		 * @param pattern  日期格式
		 *
		 * @return 调整后的日期时间字符串
		 */
		public static String adjustDay(LocalDateTime dateTime, int adjust, String pattern) {
			if (dateTime == null) {
				dateTime = LocalDateTime.now();
			}
			return format(dateTime.plusDays(adjust), pattern);
		}

		/**
		 * 指定日期时间增减小时后的日期时间字符串
		 *
		 * @param dateTime LocalDateTime 本地日期时间
		 * @param adjust   调整的值
		 * @param pattern  日期格式
		 *
		 * @return 调整后的日期时间字符串
		 */
		public static String adjustHours(LocalDateTime dateTime, int adjust, String pattern) {
			if (dateTime == null) {
				dateTime = LocalDateTime.now();
			}
			return format(dateTime.plusHours(adjust), pattern);
		}

		/**
		 * 指定日期时间增减分钟后的日期时间字符串
		 *
		 * @param dateTime LocalDateTime 本地日期时间
		 * @param adjust   调整的值
		 * @param pattern  日期格式
		 *
		 * @return 调整后的日期时间字符串
		 */
		public static String adjustMinutes(LocalDateTime dateTime, int adjust, String pattern) {
			if (dateTime == null) {
				dateTime = LocalDateTime.now();
			}
			return format(dateTime.plusMinutes(adjust), pattern);
		}

		/**
		 * 指定日期时间增减秒后的日期时间字符串
		 *
		 * @param dateTime LocalDateTime 本地日期时间
		 * @param adjust   调整的值
		 * @param pattern  日期格式
		 *
		 * @return 调整后的日期时间字符串
		 */
		public static String adjustSeconds(LocalDateTime dateTime, int adjust, String pattern) {
			if (dateTime == null) {
				dateTime = LocalDateTime.now();
			}
			return format(dateTime.plusSeconds(adjust), pattern);
		}

		/**
		 * 指定日期时间增减年后的日期时间字符串
		 *
		 * @param dateTime LocalDateTime 本地日期时间
		 * @param adjust   调整的值
		 * @param pattern  日期格式
		 *
		 * @return 调整后的日期时间字符串
		 */
		public static String adjustYears(LocalDateTime dateTime, int adjust, String pattern) {
			if (dateTime == null) {
				dateTime = LocalDateTime.now();
			}
			return format(dateTime.plusYears(adjust), pattern);
		}

		/**
		 * 指定日期时间增减月后的日期时间字符串
		 *
		 * @param dateTime LocalDateTime 本地日期时间
		 * @param adjust   调整的值
		 * @param pattern  日期格式
		 *
		 * @return 调整后的日期时间字符串
		 */
		public static String adjustMonths(LocalDateTime dateTime, int adjust, String pattern) {
			if (dateTime == null) {
				dateTime = LocalDateTime.now();
			}
			return format(dateTime.plusMonths(adjust), pattern);
		}

		/**
		 * 指定日期时间增减周后的日期时间字符串
		 *
		 * @param dateTime LocalDateTime 本地日期时间
		 * @param adjust   调整的值
		 * @param pattern  日期格式
		 *
		 * @return 调整后的日期时间字符串
		 */
		public static String adjustWeeks(LocalDateTime dateTime, int adjust, String pattern) {
			if (dateTime == null) {
				dateTime = LocalDateTime.now();
			}
			return format(dateTime.plusWeeks(adjust), pattern);
		}

		/**
		 * 获得指定格式当前日期时间字符串
		 *
		 * @param pattern 日期格式
		 *
		 * @return 定义的日期格式
		 */
		public static String getToday(String pattern) {
			return format(LocalDateTime.now(), pattern);
		}

		/**
		 * 获取当前时间
		 *
		 * @return 时间字符串 HH:mm:ss
		 */
		public static String getNowTime() {
			return getToday(TIME_PATTERN);
		}

		/**
		 * 获取当前日期时间 yyyy-MM-dd HH:mm:ss
		 *
		 * @return 日期时间字符串 yyyy-MM-dd HH:mm:ss
		 */
		public static String getNowDateTime() {
			return getToday(DATE_TIME_PATTERN);
		}

		/**
		 * 获取两个时间点之间的时长-天
		 *
		 * @param fromDateTime 开始时间点
		 * @param toDateTime   结束时间点
		 *
		 * @return 经过的时长
		 */
		public static Long getDurationDays(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
			return Duration.between(fromDateTime, toDateTime).toDays();
		}

		/**
		 * 获取两个时间点之间的时长-小时
		 *
		 * @param fromDateTime 开始时间点
		 * @param toDateTime   结束时间点
		 *
		 * @return 经过的时长
		 */
		public static Long getDurationHours(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
			return Duration.between(fromDateTime, toDateTime).toHours();
		}

		/**
		 * 获取两个时间点之间的时长-分钟
		 *
		 * @param fromDateTime 开始时间点
		 * @param toDateTime   结束时间点
		 *
		 * @return 经过的时长
		 */
		public static Long getDurationMinutes(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
			return Duration.between(fromDateTime, toDateTime).toMinutes();
		}

		/**
		 * 获取两个时间点之间的时长-毫秒
		 *
		 * @param fromDateTime 开始时间点
		 * @param toDateTime   结束时间点
		 *
		 * @return 经过的时长
		 */
		public static Long getDurationMillis(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
			return Duration.between(fromDateTime, toDateTime).toMillis();
		}

		/**
		 * 获取两个时间点之间的时长-天
		 *
		 * @param fromStr 开始时间点 yyyy-MM-dd HH:mm:ss
		 * @param toStr   结束时间点 yyyy-MM-dd HH:mm:ss
		 *
		 * @return 经过的时长
		 */
		public static Long getDurationDays(String fromStr, String toStr) {

			LocalDateTime fromDateTime = LocalDateTime.parse(fromStr, DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
			LocalDateTime toDateTime = LocalDateTime.parse(toStr, DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));

			return Duration.between(fromDateTime, toDateTime).toDays();
		}

		/**
		 * 获取两个时间点之间的时长-小时
		 *
		 * @param fromStr 开始时间点 yyyy-MM-dd HH:mm:ss
		 * @param toStr   结束时间点 yyyy-MM-dd HH:mm:ss
		 *
		 * @return 经过的时长
		 */
		public static Long getDurationHours(String fromStr, String toStr) {

			LocalDateTime fromDateTime = LocalDateTime.parse(fromStr, DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
			LocalDateTime toDateTime = LocalDateTime.parse(toStr, DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));

			return Duration.between(fromDateTime, toDateTime).toHours();
		}

		/**
		 * 获取两个时间点之间的时长-分钟
		 *
		 * @param fromStr 开始时间点 yyyy-MM-dd HH:mm:ss
		 * @param toStr   结束时间点 yyyy-MM-dd HH:mm:ss
		 *
		 * @return 经过的时长
		 */
		public static Long getDurationMinutes(String fromStr, String toStr) {

			LocalDateTime fromDateTime = LocalDateTime.parse(fromStr, DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
			LocalDateTime toDateTime = LocalDateTime.parse(toStr, DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));

			return Duration.between(fromDateTime, toDateTime).toMinutes();
		}

		/**
		 * 获取两个时间点之间的时长-毫秒
		 *
		 * @param fromStr 开始时间点 yyyy-MM-dd HH:mm:ss
		 * @param toStr   结束时间点 yyyy-MM-dd HH:mm:ss
		 *
		 * @return 经过的时长
		 */
		public static Long getDurationMillis(String fromStr, String toStr) {

			LocalDateTime fromDateTime = LocalDateTime.parse(fromStr, DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
			LocalDateTime toDateTime = LocalDateTime.parse(toStr, DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));

			return Duration.between(fromDateTime, toDateTime).toMillis();
		}

		/**
		 * 获取两个时间点之间的时长-天
		 *
		 * @param fromStr 开始时间点 yyyy-MM-dd HH:mm:ss
		 * @param toStr   结束时间点 yyyy-MM-dd HH:mm:ss
		 * @param pattern 日期格式
		 *
		 * @return 经过的时长
		 */
		public static Long getDurationDays(String fromStr, String toStr, String pattern) {

			LocalDateTime fromDateTime = LocalDateTime.parse(fromStr, DateTimeFormatter.ofPattern(pattern));
			LocalDateTime toDateTime = LocalDateTime.parse(toStr, DateTimeFormatter.ofPattern(pattern));

			return Duration.between(fromDateTime, toDateTime).toDays();
		}

		/**
		 * 获取两个时间点之间的时长-小时
		 *
		 * @param fromStr 开始时间点 yyyy-MM-dd HH:mm:ss
		 * @param toStr   结束时间点 yyyy-MM-dd HH:mm:ss
		 * @param pattern 日期格式
		 *
		 * @return 经过的时长
		 */
		public static Long getDurationHours(String fromStr, String toStr, String pattern) {

			LocalDateTime fromDateTime = LocalDateTime.parse(fromStr, DateTimeFormatter.ofPattern(pattern));
			LocalDateTime toDateTime = LocalDateTime.parse(toStr, DateTimeFormatter.ofPattern(pattern));

			return Duration.between(fromDateTime, toDateTime).toHours();
		}

		/**
		 * 获取两个时间点之间的时长-分钟
		 *
		 * @param fromStr 开始时间点 yyyy-MM-dd HH:mm:ss
		 * @param toStr   结束时间点 yyyy-MM-dd HH:mm:ss
		 * @param pattern 日期格式
		 *
		 * @return 经过的时长
		 */
		public static Long getDurationMinutes(String fromStr, String toStr, String pattern) {

			LocalDateTime fromDateTime = LocalDateTime.parse(fromStr, DateTimeFormatter.ofPattern(pattern));
			LocalDateTime toDateTime = LocalDateTime.parse(toStr, DateTimeFormatter.ofPattern(pattern));

			return Duration.between(fromDateTime, toDateTime).toMinutes();
		}

		/**
		 * 获取两个时间点之间的时长-毫秒
		 *
		 * @param fromStr 开始时间点 yyyy-MM-dd HH:mm:ss
		 * @param toStr   结束时间点 yyyy-MM-dd HH:mm:ss
		 * @param pattern 日期格式
		 *
		 * @return 经过的时长
		 */
		public static Long getDurationMillis(String fromStr, String toStr, String pattern) {

			LocalDateTime fromDateTime = LocalDateTime.parse(fromStr, DateTimeFormatter.ofPattern(pattern));
			LocalDateTime toDateTime = LocalDateTime.parse(toStr, DateTimeFormatter.ofPattern(pattern));

			return Duration.between(fromDateTime, toDateTime).toMillis();
		}

		/**
		 * 判断日期时间是否在特定范围内
		 *
		 * @param dateTime     需要判断的时间点
		 * @param fromDateTime 开始时间点
		 * @param toDateTime   结束时间点
		 *
		 * @return 是否在范围内
		 */
		public static Boolean isBetween(LocalDateTime dateTime, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
			// 同时晚于两个节点时间自然不在范围内
			boolean allAfterFlag = dateTime.isAfter(fromDateTime) && dateTime.isAfter(toDateTime);
			// 同时早于两个节点时间当然也不在范围内
			boolean allBeforeFlag = dateTime.isBefore(fromDateTime) && dateTime.isBefore(toDateTime);

			return !(allAfterFlag && allBeforeFlag);
		}

		/**
		 * 判断日期时间是否在特定范围内
		 *
		 * @param dateTime 需要判断的时间点
		 * @param fromStr  开始时间点 yyyy-MM-dd HH:mm:ss
		 * @param toStr    结束时间点 yyyy-MM-dd HH:mm:ss
		 *
		 * @return 是否在范围内
		 */
		public static Boolean isBetween(LocalDateTime dateTime, String fromStr, String toStr) {
			LocalDateTime fromDateTime = LocalDateTime.parse(fromStr, DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
			LocalDateTime toDateTime = LocalDateTime.parse(toStr, DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));

			return isBetween(dateTime, fromDateTime, toDateTime);
		}

		/**
		 * 判断当前日期时间是否在特定范围内
		 *
		 * @param fromStr 开始时间点 yyyy-MM-dd HH:mm:ss
		 * @param toStr   结束时间点 yyyy-MM-dd HH:mm:ss
		 *
		 * @return 是否在范围内
		 */
		public static Boolean isBetween(String fromStr, String toStr) {
			LocalDateTime dateTime = LocalDateTime.now();

			return isBetween(dateTime, fromStr, toStr);
		}

	}

	/**
	 * 转换日期格式
	 *
	 * @param pdate 日期
	 * @param pattern 日期格式
	 * @return 日期
	 */
	public static final String getDate(Date pdate, String pattern) {
		if (pattern == null)
			pattern = "yyyyMMdd";
		return (new SimpleDateFormat(pattern)).format(pdate);

	}

	public static void main(String[] args) {
		System.out.println(OperDate.isBetween("2020-09-01", "2020-10-10"));
	}
}
