package com.linkknown.crm.common.util.paramutil;

import com.linkknown.crm.bean.req.QueryCustomerIncomePage;
import com.linkknown.crm.common.aspect.exception.WebException;
import com.linkknown.crm.common.enums.ResponseEnum;
import com.linkknown.crm.common.util.RegexUtils;
import org.springframework.util.StringUtils;

/**
 * @author zhoupeng
 * @date 2023/4/4 14:55
 */
public class CustomerIncomeParamUtils {

    /**
     * 查询顾客收益列表
     * @param queryCustomerIncomePage 请求
     */
    public static void queryCustomerIncomeList(QueryCustomerIncomePage queryCustomerIncomePage) {
        //门店id不能为空
        if (StringUtils.isEmpty(queryCustomerIncomePage.getShopId())){
            throw new WebException(ResponseEnum.shop_id_can_not_be_empty);
        }
        //如果输入了手机号，那么需要校验格式
        if (!StringUtils.isEmpty(queryCustomerIncomePage.getPhoneNumber())){
            //手机号如果不为空，需校验格式
            if (!RegexUtils.validatePhoneNumber(queryCustomerIncomePage.getPhoneNumber())){
                throw new WebException(ResponseEnum.phone_number_style_error);
            }
        }
    }
}
