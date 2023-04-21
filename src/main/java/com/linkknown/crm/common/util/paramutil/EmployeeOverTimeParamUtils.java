package com.linkknown.crm.common.util.paramutil;

import com.linkknown.crm.bean.req.QueryEmployeeOverTimePage;
import com.linkknown.crm.common.aspect.exception.WebException;
import com.linkknown.crm.common.enums.ResponseEnum;
import org.springframework.util.StringUtils;

/**
 * @author zhoupeng
 * @date 2023/4/4 14:55
 */
public class EmployeeOverTimeParamUtils {


    /**
     * 查询所有员工加班记录
     * @param queryEmployeeOverTimePage 请求
     */
    public static void queryEmployeeOverTimeList(QueryEmployeeOverTimePage queryEmployeeOverTimePage) {
        //门店id不能为空
        if (StringUtils.isEmpty(queryEmployeeOverTimePage.getShopId())){
            throw new WebException(ResponseEnum.shop_id_can_not_be_empty);
        }
    }



}
