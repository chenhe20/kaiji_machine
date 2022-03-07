package kcl.ac.uk.kaiji_machine.util;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * date util
 */
public class DateUtils {

        /**
         * yyyyMMdd时间格式
         */
        public final static String SHORT_FORMAT = "yyyyMMdd";
        /**
         * yyyyMMddHHmmss时间格式
         */
        public final static String LONG_FORMAT = "yyyyMMddHHmmss";
        /**
         * yyyy-MM-dd HH:mm:ss时间格式
         */
        public final static String NEW_FORMAT = "yyyy-MM-dd HH:mm:ss";
        /**
         * yyyyMMddHH:mm:ss时间格式
         */
        public final static String QUERY_FORMAT = "yyyyMMddHH:mm:ss";
        /**
         * yyyy-MM-dd HH:mm:ss.SSS时间格式
         */
        public final static String INSERT_FORMAT = "yyyy-MM-dd HH:mm:ss";

        /**
         * 时间格式
         *
         * @param dateStr 格式化前的时间
         * @param format  需要转换的格式
         * @return 格式化后的时间
         * @throws ParseException
         */
        public static Date formatToDate(String dateStr, String format) throws ParseException {
            if (dateStr == null) {
                return null;
            }
            DateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.parse(dateStr);
        }

        /**
         * 格式化时间
         *
         * @param date   转换前的日期
         * @param format 需要转换的格式
         * @return 转换后的日期
         */
        public static String getDateString(Date date, String format) {
            if (date == null || StringUtils.isBlank(format)) {
                return null;
            }
            DateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(date);
        }

        /**
         * 相差分钟数,date>otherDate返回正数,否则返回负数
         *
         * @param date      日期
         * @param otherDate 另一个日期
         * @return 相差分钟。如果失败则返回-1
         */
        public static int getIntervalMinutes(Date date, Date otherDate) {
            int num = -1;
            if (date != null && otherDate != null) {
                long time = date.getTime() - otherDate.getTime();
                num = (int) (time / (60 * 1000));
            }
            return num;
        }


        /**
         * 相差天数
         *
         * @param date      日期
         * @param otherDate 另一个日期
         * @return 相差天数。如果失败则返回-1
         */
        public static int getIntervalDays(Date date, Date otherDate) {
            int num = -1;
            if (date != null && otherDate != null) {
                long time = Math.abs(date.getTime() - otherDate.getTime());
                num = (int) (time / (24 * 60 * 60 * 1000));
            }
            return num;
        }

        /**
         * 相差小时数
         *
         * @param date      日期
         * @param otherDate 另一个日期
         * @return 相差天数。如果失败则返回-1
         */
        public static int getIntervalHours(Date date, Date otherDate) {
            int num = -1;
            if (date != null && otherDate != null) {
                long time = Math.abs(date.getTime() - otherDate.getTime());
                num = (int) (time / (60 * 60 * 1000));
            }
            return num;
        }

        /**
         * 判断是否是同一天
         *
         * @param date      日期
         * @param otherDate 另一个日期
         * @return 同一天返回true
         */
        public static boolean isSameDay(Date date, Date otherDate) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            Calendar otherCalendar = Calendar.getInstance();
            otherCalendar.setTime(otherDate);

            /**
             * 依次判断是否是同一年、同一天
             */
            boolean isSameYear = calendar.get(Calendar.YEAR) == otherCalendar.get(Calendar.YEAR);
            boolean isSameDay = isSameYear && calendar.get(Calendar.DAY_OF_YEAR) == otherCalendar.get(Calendar.DAY_OF_YEAR);

            return isSameDay;
        }

        /**
         * 时间格式
         *
         * @param dateStr 格式化前的时间
         * @param format  需要转换的格式
         * @return 格式化后的时间
         * @throws ParseException
         */
        public static boolean isFormat(String dateStr, String format) {
            if (dateStr == null) {
                return false;
            }
            try {
                DateFormat dateFormat = new SimpleDateFormat(format);
                dateFormat.parse(dateStr);
            } catch (Exception e) {
                return false;
            }
            return true;
        }

        /**
         * 将Data转为cron表达式
         *
         * @param date
         * @return
         */
        public static String getCron(Date date) {
            String dateFormat = "ss mm HH dd MM ? yyyy";
            return getDateString(date, dateFormat);
        }

        /**
         * <pre>
         *    判断今日是星期几;
         *    星期一~星期日分别对应1~7
         * </pre>
         */
        public static int getWeekDay() {
            Calendar calendar = Calendar.getInstance();
            int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
            if (weekDay == Calendar.SUNDAY) {
                return weekDay + 6;
            } else {
                return weekDay - 1;
            }
        }

        /**
         * <pre>
         *    获取当天所在周的周一，周日算每周的结束，周一算开始
         * </pre>
         */
        public static Date getFirstDayOfWeek() {
            Calendar cal = Calendar.getInstance();
            //设置周一作为每周的开始
            cal.setFirstDayOfWeek(Calendar.MONDAY);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            return cal.getTime();
        }

        /**
         * <pre>
         *    获取当天所在周的周日，周日算每周的结束
         * </pre>
         */
        public static Date getLastDayOfWeek() {
            Calendar cal = Calendar.getInstance();
            cal.setFirstDayOfWeek(Calendar.MONDAY);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            return cal.getTime();
        }

        /**
         * 在dateTime时间之上增加days天
         *
         * @param dateTime
         * @param days
         * @return
         */
        public static Date addDay(Date dateTime, Integer days) {

            Calendar ca = Calendar.getInstance();
            ca.setTime(dateTime);
            ca.add(Calendar.DAY_OF_MONTH, days);

            return ca.getTime();
        }

        /**
         * 分钟转换毫秒
         *
         * @param minute
         * @return
         */
        public static long convertMinuteToMilliseconds(Integer minute) {
            return minute * 60 * 1000L;
        }

        /**
         * 日期比较大小
         * date1>date2 return 1;
         * date1<date2 return -1;
         * date1=date2 return 0;
         *
         * @param dt1
         * @param dt2
         * @return
         */
        public static int compareDate(Date dt1, Date dt2) {
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        }

        /**
         * 生成当前日期的时间戳（字符串）
         */
        public static String getCurrentTimeStamp() {
            return String.valueOf(System.currentTimeMillis());
        }

        /**
         * Adds a number of minutes to a date returning a new object.
         * The original {@code Date} is unchanged.
         *
         * @param date   the date, not null
         * @param amount the amount to add, may be negative
         * @return the new {@code Date} with the amount added
         * @throws IllegalArgumentException if the date is null
         */
        public static Date addMinutes(Date date, int amount) {
            return add(date, 12, amount);
        }

        private static Date add(Date date, int calendarField, int amount) {
            if (date == null) {
                throw new IllegalArgumentException("The date must not be null");
            } else {
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.add(calendarField, amount);
                return c.getTime();
            }
        }

        public static Map<String, String> getYearMonthDay(Date date) {
            if (date == null) {
                return null;
            }
            Map<String, String> map = new HashMap<>();
            Calendar calendar = Calendar.getInstance();
            //放入Date类型数据
            calendar.setTime(date);
            //获取年份
            map.put("YEAR", String.valueOf(calendar.get(Calendar.YEAR)));
            //获取月份
            map.put("MONTH", String.valueOf(calendar.get(Calendar.MONTH) + 1));
            //获取日
            map.put("DATE", String.valueOf(calendar.get(Calendar.DATE)));
            return map;

        }

        public static Date getDateFromYearMonthDay(String year, String month, String day) {
            Date resultDate = null;
            if ("0000".equals(year) || "00".equals(month) || "00".equals(day)) {
                return null;
            }
            try {
                String dateStr = year + month + day;
                DateFormat dateFormat = new SimpleDateFormat(SHORT_FORMAT);
                resultDate = dateFormat.parse(dateStr);
            } catch (ParseException e) {
                return null;
            }
            return resultDate;
        }
}
