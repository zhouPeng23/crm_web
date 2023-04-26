package com.linkknown.crm.common.util;

import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author zhoupeng
 * @date 2023/4/7 10:52
 */
public class MyStringUtils {

    /**
     * 将IntegerList转为字符串
     * @param reqList 集合
     * @return 字符串
     */
    public static String[] integerList2Array(List<Integer> reqList) {
        if (CollectionUtils.isEmpty(reqList)){
            return new String[0];
        }
        StringBuilder res = new StringBuilder();
        for (Integer i:reqList){
            res.append(String.valueOf(i)).append(",");
        }
        return res.toString().substring(0, res.length() - 1).split(",");
    }

}
