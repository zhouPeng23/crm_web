package com.linkknown.crm.common.util;

import java.util.regex.Pattern;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
public class RegexUtils {

    /**
     * 手机号
     */
    private static final String PHONE_NUMBER = "^1[3-9][0-9]{9}$";

    /**
     * 金额
     */
    private static final String PRICE = "^[0-9]+(.[0-9]{1,2})?$";


    /**
     * 校验手机号
     * @param str 入参
     */
    public static boolean validatePhoneNumber(String str) {
        return Pattern.matches(PHONE_NUMBER, str);
    }


    /**
     * 校验价格
     * @param str 入参
     */
    public static boolean validateAmount(String str) {
        return Pattern.matches(PRICE, str);
    }


}
