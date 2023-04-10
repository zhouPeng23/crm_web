package com.linkknown.crm.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhoupeng
 * @date 2023/4/4 12:01
 */
public class DateUtils {

    private static String YYYY_MM_DD = "yyyy-MM-dd";

    private static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";


    /**
     * 毫秒格式化字符串日期
     * @param millisecond 毫秒数
     * @return 日期
     */
    public static Date millisecondFormatDate(Long millisecond){
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        try {
            return new SimpleDateFormat(YYYY_MM_DD).parse(sdf.format(new Date(millisecond)));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取strDateTime日期的Date类型
     */
    public static Date formatStrTime2Date(String strDateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        try {
            return sdf.parse(strDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
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
