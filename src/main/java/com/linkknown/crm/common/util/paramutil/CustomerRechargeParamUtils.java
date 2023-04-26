package com.linkknown.crm.common.util.paramutil;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linkknown.crm.bean.dos.Customer;
import com.linkknown.crm.bean.dos.CustomerRecharge;
import com.linkknown.crm.bean.req.*;
import com.linkknown.crm.common.aspect.exception.WebException;
import com.linkknown.crm.common.enums.ResponseEnum;
import com.linkknown.crm.common.util.RegexUtils;
import org.springframework.util.StringUtils;

/**
 * @author zhoupeng
 * @date 2023/4/4 14:55
 */
public class CustomerRechargeParamUtils {

    /**
     * 查询顾客充值记录集合
     * @param queryCustomerRechargePage 请求
     */
    public static void queryCustomerRechargeList(QueryCustomerRechargePage queryCustomerRechargePage) {
        //门店id不能为空
        if (StringUtils.isEmpty(queryCustomerRechargePage.getShopId())){
            throw new WebException(ResponseEnum.shop_id_can_not_be_empty);
        }
        //如果输入了手机号，那么需要校验格式
        if (!StringUtils.isEmpty(queryCustomerRechargePage.getPhoneNumber())){
            //手机号如果不为空，需校验格式
            if (!RegexUtils.validatePhoneNumber(queryCustomerRechargePage.getPhoneNumber())){
                throw new WebException(ResponseEnum.phone_number_style_error);
            }
        }
    }


    /**
     * 添加充值
     * @param addCustomerRechargeReq 请求
     */
    public static void addCustomerRecharge(AddCustomerRechargeReq addCustomerRechargeReq) {
        //门店id不能为空
        if (StringUtils.isEmpty(addCustomerRechargeReq.getShopId())){
            throw new WebException(ResponseEnum.shop_id_can_not_be_empty);
        }
        //手机号需校验格式
        if (!RegexUtils.validatePhoneNumber(addCustomerRechargeReq.getPhoneNumber())){
            throw new WebException(ResponseEnum.phone_number_style_error);
        }
        //顾客姓名不能为空
        if (StringUtils.isEmpty(addCustomerRechargeReq.getCustomerName())){
            throw new WebException(ResponseEnum.customer_name_can_not_be_empty);
        }
        //顾客性别不能为空
        if (StringUtils.isEmpty(addCustomerRechargeReq.getSex())){
            throw new WebException(ResponseEnum.customer_sex_can_not_be_empty);
        }
        //充值金额格式校验
        if (!RegexUtils.validateAmount(String.valueOf(addCustomerRechargeReq.getRechargeAmount()))){
            throw new WebException(ResponseEnum.amount_style_is_error);
        }
        //备注不能为空
        if (StringUtils.isEmpty(addCustomerRechargeReq.getRemark())){
            throw new WebException(ResponseEnum.remark_cannot_be_empty);
        }
        //操作员手机号
        if (StringUtils.isEmpty(addCustomerRechargeReq.getLoginUserPhoneNumber())){
            throw new WebException(ResponseEnum.login_user_phone_number_cannot_be_empty);
        }
        //密码不能为空
        if (StringUtils.isEmpty(addCustomerRechargeReq.getPassword())){
            throw new WebException(ResponseEnum.password_cannot_be_empty);
        }

    }
}
