package com.linkknown.crm.common.util.paramutil;

import com.linkknown.crm.bean.dos.Customer;
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
     * 添加或者更新顾客校验参数
     * @param customer customer
     */
    public static void addOrUpdateCustomerValidate(Customer customer) {
        //店铺id不能为空
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
        if (!RegexUtils.checkPhoneNumber(customer.getPhoneNumber())){
            throw new WebException(ResponseEnum.customer_phone_number_style_error);
        }
        //顾客生日日期不能为空
        if (StringUtils.isEmpty(customer.getBirthday())){
            throw new WebException(ResponseEnum.customer_birthday_can_not_be_empty);
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



}
