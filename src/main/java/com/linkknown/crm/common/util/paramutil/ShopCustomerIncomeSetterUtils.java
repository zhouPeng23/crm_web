package com.linkknown.crm.common.util.paramutil;

import com.linkknown.crm.bean.dos.ShopCustomerIncomeSetter;
import com.linkknown.crm.bean.req.AddShopCustomerIncomeSetterReq;
import com.linkknown.crm.bean.req.DeleteShopCustomerIncomeSetterReq;
import com.linkknown.crm.bean.req.UpdateShopCustomerIncomeSetterReq;
import com.linkknown.crm.common.aspect.exception.WebException;
import com.linkknown.crm.common.enums.ResponseEnum;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author zhoupeng
 * @date 2023/4/4 14:55
 */
public class ShopCustomerIncomeSetterUtils {


    /**
     * 查询门店里顾客收益设置集合
     * @param shopCustomerIncomeSetter 请求
     */
    public static void selectShopCustomerIncomeSetterList(ShopCustomerIncomeSetter shopCustomerIncomeSetter) {
        //门店id不能为空
        if (StringUtils.isEmpty(shopCustomerIncomeSetter.getShopId())){
            throw new WebException(ResponseEnum.shop_id_can_not_be_empty);
        }
    }


    /**
     * 添加门店里顾客收益设置
     * @param addShopCustomerIncomeSetterReq 请求
     */
    public static void addShopCustomerIncomeSetter(AddShopCustomerIncomeSetterReq addShopCustomerIncomeSetterReq) {
        //门店id不能为空
        if (StringUtils.isEmpty(addShopCustomerIncomeSetterReq.getShopId())){
            throw new WebException(ResponseEnum.shop_id_can_not_be_empty);
        }
        //首单收益点数不能为空
        if (StringUtils.isEmpty(addShopCustomerIncomeSetterReq.getFirstIncomePoint())){
            throw new WebException(ResponseEnum.shop_customer_first_income_point_cannot_be_empty);
        }
        //永久收益点数不能为空
        if (StringUtils.isEmpty(addShopCustomerIncomeSetterReq.getForeverIncomePoint())){
            throw new WebException(ResponseEnum.shop_customer_forever_income_point_cannot_be_empty);
        }
        //操作员手机号
        if (StringUtils.isEmpty(addShopCustomerIncomeSetterReq.getLoginUserPhoneNumber())){
            throw new WebException(ResponseEnum.login_user_phone_number_cannot_be_empty);
        }
        //密码不能为空
        if (StringUtils.isEmpty(addShopCustomerIncomeSetterReq.getPassword())){
            throw new WebException(ResponseEnum.password_cannot_be_empty);
        }
    }


    /**
     * 修改门店里顾客收益设置
     * @param updateShopCustomerIncomeSetterReq 请求
     */
    public static void updateShopCustomerIncomeSetter(UpdateShopCustomerIncomeSetterReq updateShopCustomerIncomeSetterReq) {
        //门店id不能为空
        if (StringUtils.isEmpty(updateShopCustomerIncomeSetterReq.getShopId())){
            throw new WebException(ResponseEnum.shop_id_can_not_be_empty);
        }
        //顾客收益设置id不能为空
        if (StringUtils.isEmpty(updateShopCustomerIncomeSetterReq.getSetterId())){
            throw new WebException(ResponseEnum.shop_customer_setter_id_cannot_be_empty);
        }
        //首单收益点数不能为空
        if (StringUtils.isEmpty(updateShopCustomerIncomeSetterReq.getFirstIncomePoint())){
            throw new WebException(ResponseEnum.shop_customer_first_income_point_cannot_be_empty);
        }
        //永久收益点数不能为空
        if (StringUtils.isEmpty(updateShopCustomerIncomeSetterReq.getForeverIncomePoint())){
            throw new WebException(ResponseEnum.shop_customer_forever_income_point_cannot_be_empty);
        }
        //操作员手机号
        if (StringUtils.isEmpty(updateShopCustomerIncomeSetterReq.getLoginUserPhoneNumber())){
            throw new WebException(ResponseEnum.login_user_phone_number_cannot_be_empty);
        }
        //密码不能为空
        if (StringUtils.isEmpty(updateShopCustomerIncomeSetterReq.getPassword())){
            throw new WebException(ResponseEnum.password_cannot_be_empty);
        }
    }


    /**
     * 删除门店里顾客收益设置
     * @param deleteShopCustomerIncomeSetterReq 请求
     */
    public static void deleteShopCustomerIncomeSetter(DeleteShopCustomerIncomeSetterReq deleteShopCustomerIncomeSetterReq) {
        //门店id不能为空
        if (StringUtils.isEmpty(deleteShopCustomerIncomeSetterReq.getShopId())){
            throw new WebException(ResponseEnum.shop_id_can_not_be_empty);
        }
        //顾客收益设置id不能为空
        if (StringUtils.isEmpty(deleteShopCustomerIncomeSetterReq.getSetterId())){
            throw new WebException(ResponseEnum.shop_customer_setter_id_cannot_be_empty);
        }
        //操作员手机号
        if (StringUtils.isEmpty(deleteShopCustomerIncomeSetterReq.getLoginUserPhoneNumber())){
            throw new WebException(ResponseEnum.login_user_phone_number_cannot_be_empty);
        }
        //密码不能为空
        if (StringUtils.isEmpty(deleteShopCustomerIncomeSetterReq.getPassword())){
            throw new WebException(ResponseEnum.password_cannot_be_empty);
        }
    }



}
