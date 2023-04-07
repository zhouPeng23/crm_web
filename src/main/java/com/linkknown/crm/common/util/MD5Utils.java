package com.linkknown.crm.common.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author zhoupeng
 * @date 2023/4/7 10:52
 */
public class MD5Utils {

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

}
