package com.linkknown.crm.common.util.paramutil;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linkknown.crm.bean.dos.Customer;
import com.linkknown.crm.bean.req.QueryCustomerPage;
import com.linkknown.crm.common.aspect.exception.WebException;
import com.linkknown.crm.common.enums.ResponseEnum;
import com.linkknown.crm.common.util.RegexUtils;
import org.springframework.util.StringUtils;

/**
 * @author zhoupeng
 * @date 2023/4/4 14:55
 */
public class CustomerParamUtils {

    /**
     * 查询顾客集合校验
     * @param queryCustomerPage 分页查询入参
     */
    public static void queryCustomerList(QueryCustomerPage queryCustomerPage) {
        //门店id不能为空
        if (StringUtils.isEmpty(queryCustomerPage.getShopId())){
            throw new WebException(ResponseEnum.shop_id_can_not_be_empty);
        }
    }


    /**
     * 添加顾客校验参数
     * @param customer customer
     */
    public static void addCustomer(Customer customer) {
        //门店id不能为空
        if (StringUtils.isEmpty(customer.getShopId())){
            throw new WebException(ResponseEnum.shop_id_can_not_be_empty);
        }
        //顾客姓名不能为空
        if (StringUtils.isEmpty(customer.getCustomerName())){
            throw new WebException(ResponseEnum.customer_name_can_not_be_empty);
        }
        //顾客性别不能为空
        if (StringUtils.isEmpty(customer.getSex())){
            throw new WebException(ResponseEnum.customer_sex_can_not_be_empty);
        }
        //顾客手机号格式错误
        if (!RegexUtils.validatePhoneNumber(customer.getPhoneNumber())){
            throw new WebException(ResponseEnum.customer_phone_number_style_error);
        }
        //顾客等级不能为空
        if (StringUtils.isEmpty(customer.getCustomerMassLevel())){
            throw new WebException(ResponseEnum.customer_mass_level_can_not_be_empty);
        }
        //顾客所属员工不能为空
        if (StringUtils.isEmpty(customer.getBelongToEmployeeId())){
            throw new WebException(ResponseEnum.customer_belong_to_employee_id_can_not_be_empty);
        }

    }


    /**
     * 更新顾客校验参数
     * @param customer customer
     */
    public static void updateCustomer(Customer customer) {
        //门店id不能为空
        if (StringUtils.isEmpty(customer.getShopId())){
            throw new WebException(ResponseEnum.shop_id_can_not_be_empty);
        }
        //顾客id不能为空
        if (StringUtils.isEmpty(customer.getCustomerId())){
            throw new WebException(ResponseEnum.customer_id_can_not_be_empty);
        }
        //顾客姓名不能为空
        if (StringUtils.isEmpty(customer.getCustomerName())){
            throw new WebException(ResponseEnum.customer_name_can_not_be_empty);
        }
        //顾客性别不能为空
        if (StringUtils.isEmpty(customer.getSex())){
            throw new WebException(ResponseEnum.customer_sex_can_not_be_empty);
        }
        //顾客手机号格式错误
        if (!RegexUtils.validatePhoneNumber(customer.getPhoneNumber())){
            throw new WebException(ResponseEnum.customer_phone_number_style_error);
        }
        //顾客等级不能为空
        if (StringUtils.isEmpty(customer.getCustomerMassLevel())){
            throw new WebException(ResponseEnum.customer_mass_level_can_not_be_empty);
        }
        //顾客所属员工不能为空
        if (StringUtils.isEmpty(customer.getBelongToEmployeeId())){
            throw new WebException(ResponseEnum.customer_belong_to_employee_id_can_not_be_empty);
        }

    }


    /**
     * 删除顾客
     * @param customer 顾客
     */
    public static void deleteCustomer(Customer customer) {
        //顾客id不能为空
        if (StringUtils.isEmpty(customer.getCustomerId())){
            throw new WebException(ResponseEnum.customer_id_can_not_be_empty);
        }
    }


    /**
     * 查询门店所有顾客集合
     * @param customer 顾客
     */
    public static void queryShopAllCustomer(Customer customer) {
        //门店id不能为空
        if (StringUtils.isEmpty(customer.getShopId())){
            throw new WebException(ResponseEnum.shop_id_can_not_be_empty);
        }
    }






}
