package com.linkknown.crm.common.util;

import java.util.regex.Pattern;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
public class RegexUtils {

    /**
     * 正整数
     */
    private static final String POSITIVE_INTEGER = "^[1-9]\\d*$";

    /**
     * 非负整数
     */
    private static final String NONNEGATIVE_INTEGER = "^[0-9]\\d*$";

    /**
     * 手机号
     */
    private static final String PHONE_NUMBER = "^1[3-9][0-9]{9}$";



    /**
     * 校验正整数
     *
     * @param str 入参
     */
    public static boolean positiveInteger(String str) {
        return Pattern.matches(POSITIVE_INTEGER, str);
    }


    /**
     * 校验非负整数
     *
     * @param str 入参
     */
    public static boolean nonnegativeInteger(String str) {
        return Pattern.matches(NONNEGATIVE_INTEGER, str);
    }


    /**
     * 校验手机号
     *
     * @param str 入参
     */
    public static boolean checkPhoneNumber(String str) {
        return Pattern.matches(PHONE_NUMBER, str);
    }

}
