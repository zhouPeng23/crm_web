package com.linkknown.crm.common.util;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhoupeng
 * @date 2023/4/4 12:01
 */
public class DateUtils {


    /**
     * 毫秒格式化字符串日期
     * @param millisecond 毫秒数
     * @return 日期
     */
    public static Date millisecondFormatDate(Long millisecond){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(sdf.format(new Date(millisecond)));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取strDateTime日期的Date类型
     */
    public static Date formatStrTime2Date(String strDateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(strDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 根据sqlDate 和 sqlTime 组合获取utilDate
     * @param sqlDate 日期
     * @param sqlTime 时间
     * @return utilDate
     */
    public static Date formatSqlDateAndTime2UtilDate(java.sql.Date sqlDate, java.sql.Time sqlTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(sqlDate);

        sdf = new SimpleDateFormat("HH:mm:ss");
        String timeStr = sdf.format(sqlTime);

        String strDateTime = dateStr+" "+timeStr;

        Date parseDate = null;
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            parseDate = sdf.parse(strDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return parseDate;
    }


    /**
     * 根据utilDate获取utilDateStr
     * @param date 日期
     * @return utilDateStr
     */
    public static String formatUtilDateStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }


    /**
     * 时间对比
     * @param currentTime 当前时间
     * @param compareTime 对比时间
     * @return 是否大于
     */
    public static boolean greaterThan(Date currentTime, Date compareTime) {
        return currentTime.getTime() >= compareTime.getTime();
    }


    /**
     * 对比两个时间段是否有交集 - 不考虑临界点
     * @param paramStartTime   参数开始时间
     * @param paramEndTime     参数结束时间
     * @param compareStartTime 对比开始时间
     * @param compareEndTime   对比结束时间
     * @return 是否
     */
    public static boolean hasIntersectionBetweenSqlTime(Time paramStartTime, Time paramEndTime, Time compareStartTime, Time compareEndTime) {
        //定义两个临时参数辅助转换
        long temp1 = paramStartTime.getTime();
        long temp2 = paramEndTime.getTime();

        //入参的两个时间值
        long startTime1 = temp1 < temp2 ? temp1 : temp2;
        long endTime1 = temp2 > temp1 ? temp2 : temp1;

        temp1 = compareStartTime.getTime();
        temp2 = compareEndTime.getTime();

        //对比的两个时间值
        long startTime2 = temp1 < temp2 ? temp1 : temp2;
        long endTime2 = temp2 > temp1 ? temp2 : temp1;

        return startTime1 < endTime2 && endTime1 > startTime2;
    }




//===================================================================================================以上为常用的=========================================================================

    /**
     * 计算相差天数
     */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        return Math.abs((int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24)));
    }


    /**当前时间是否在某个时间段内
     * @param nowTime   当前时间
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return 是否
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        return nowTime.getTime() >= beginTime.getTime() && nowTime.getTime() < endTime.getTime();
    }


    /**
     * 对比两个时间段是否有交集
     * @param paramStartDate   参数开始日期
     * @param paramEndDate     参数结束日期
     * @param compareStartDate 对比开始日期
     * @param compareEndDate   对比结束日期
     * @return 是否
     */
    public static boolean hasIntersection(Date paramStartDate, Date paramEndDate, Date compareStartDate, Date compareEndDate) {
        //定义两个临时参数辅助转换
        long temp1 = paramStartDate.getTime();
        long temp2 = paramEndDate.getTime();

        //入参的两个时间值
        long startTime1 = temp1 <= temp2 ? temp1 : temp2;
        long endTime1 = temp2 >= temp1 ? temp2 : temp1;

        temp1 = compareStartDate.getTime();
        temp2 = compareEndDate.getTime();

        //对比的两个时间值
        long startTime2 = temp1 <= temp2 ? temp1 : temp2;
        long endTime2 = temp2 >= temp1 ? temp2 : temp1;

        return startTime1 <= endTime2 && endTime1 >= startTime2;
    }





}
