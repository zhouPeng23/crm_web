package com.linkknown.crm.common.util.paramutil;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linkknown.crm.bean.dos.Customer;
import com.linkknown.crm.bean.req.AddCustomerReq;
import com.linkknown.crm.bean.req.QueryCustomerPage;
import com.linkknown.crm.bean.req.UpdateCustomerReq;
import com.linkknown.crm.common.aspect.exception.WebException;
import com.linkknown.crm.common.enums.ResponseEnum;
import com.linkknown.crm.common.util.RegexUtils;
import org.springframework.util.StringUtils;

import java.util.List;

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
     * @param addCustomerReq addCustomerReq
     */
    public static void addCustomer(AddCustomerReq addCustomerReq) {
        //门店id不能为空
        if (StringUtils.isEmpty(addCustomerReq.getShopId())){
            throw new WebException(ResponseEnum.shop_id_can_not_be_empty);
        }
        //顾客姓名不能为空
        if (StringUtils.isEmpty(addCustomerReq.getCustomerName())){
            throw new WebException(ResponseEnum.customer_name_can_not_be_empty);
        }
        //顾客性别不能为空
        if (StringUtils.isEmpty(addCustomerReq.getSex())){
            throw new WebException(ResponseEnum.customer_sex_can_not_be_empty);
        }
        //顾客手机号格式错误
        if (!RegexUtils.validatePhoneNumber(addCustomerReq.getPhoneNumber())){
            throw new WebException(ResponseEnum.customer_phone_number_style_error);
        }
        //顾客等级不能为空
        if (StringUtils.isEmpty(addCustomerReq.getCustomerMassLevel())){
            throw new WebException(ResponseEnum.customer_mass_level_can_not_be_empty);
        }
        //顾客所属员工不能为空
        if (StringUtils.isEmpty(addCustomerReq.getBelongToEmployeeId())){
            throw new WebException(ResponseEnum.customer_belong_to_employee_id_can_not_be_empty);
        }

        //如果有被介绍人，那么被介绍人手机号、姓名不能为空
        if ("1".equals(addCustomerReq.getHasIntroducedByCustomer())){

            //被介绍人手机号格式错误
            if (!RegexUtils.validatePhoneNumber(addCustomerReq.getIntroducedByCustomerPhoneNumber())){
                throw new WebException(ResponseEnum.introduced_by_customer_phone_number_style_error);
            }
            //新增顾客手机号与被介绍人手机号不能相同
            if (addCustomerReq.getPhoneNumber().equals(addCustomerReq.getIntroducedByCustomerPhoneNumber())){
                throw new WebException(ResponseEnum.two_phone_number_is_same);
            }
            //被介绍人姓名不能为空
            if (StringUtils.isEmpty(addCustomerReq.getIntroducedByCustomerName())){
                throw new WebException(ResponseEnum.introduced_by_customer_name_cannot_be_empty);
            }

        }

    }


    /**
     * 更新顾客校验参数
     * @param updateCustomerReq updateCustomerReq
     */
    public static void updateCustomer(UpdateCustomerReq updateCustomerReq) {
        //门店id不能为空
        if (StringUtils.isEmpty(updateCustomerReq.getShopId())){
            throw new WebException(ResponseEnum.shop_id_can_not_be_empty);
        }
        //顾客id不能为空
        if (StringUtils.isEmpty(updateCustomerReq.getCustomerId())){
            throw new WebException(ResponseEnum.customer_id_can_not_be_empty);
        }
        //顾客姓名不能为空
        if (StringUtils.isEmpty(updateCustomerReq.getCustomerName())){
            throw new WebException(ResponseEnum.customer_name_can_not_be_empty);
        }
        //顾客性别不能为空
        if (StringUtils.isEmpty(updateCustomerReq.getSex())){
            throw new WebException(ResponseEnum.customer_sex_can_not_be_empty);
        }
        //顾客手机号格式错误
        if (!RegexUtils.validatePhoneNumber(updateCustomerReq.getPhoneNumber())){
            throw new WebException(ResponseEnum.customer_phone_number_style_error);
        }
        //顾客等级不能为空
        if (StringUtils.isEmpty(updateCustomerReq.getCustomerMassLevel())){
            throw new WebException(ResponseEnum.customer_mass_level_can_not_be_empty);
        }
        //顾客所属员工不能为空
        if (StringUtils.isEmpty(updateCustomerReq.getBelongToEmployeeId())){
            throw new WebException(ResponseEnum.customer_belong_to_employee_id_can_not_be_empty);
        }

        //如果有被介绍人，那么被介绍人手机号、姓名不能为空
        if ("1".equals(updateCustomerReq.getHasIntroducedByCustomer())){

            //更新顾客手机号与被介绍人手机号不能相同
            if (updateCustomerReq.getPhoneNumber().equals(updateCustomerReq.getIntroducedByCustomerPhoneNumber())){
                throw new WebException(ResponseEnum.two_phone_number_is_same);
            }
            //被介绍人手机号格式错误
            if (!RegexUtils.validatePhoneNumber(updateCustomerReq.getIntroducedByCustomerPhoneNumber())){
                throw new WebException(ResponseEnum.introduced_by_customer_phone_number_style_error);
            }
            //被介绍人姓名不能为空
            if (StringUtils.isEmpty(updateCustomerReq.getIntroducedByCustomerName())){
                throw new WebException(ResponseEnum.introduced_by_customer_name_cannot_be_empty);
            }

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


    /**
     * 根据手机号查询顾客
     * @param customer 请求
     */
    public static void queryCustomerByPhoneNumber(Customer customer) {
        //顾客手机号格式错误
        if (!RegexUtils.validatePhoneNumber(customer.getPhoneNumber())){
            throw new WebException(ResponseEnum.customer_phone_number_style_error);
        }
    }


    /**
     * 根据预约单ids查询顾客集合
     * @param appointmentIds ids
     */
    public static void queryCustomerListByAppointmentIds(String appointmentIds) {
        if (StringUtils.isEmpty(appointmentIds)){
            throw new WebException(ResponseEnum.appointment_id_can_not_be_empty);
        }
    }


    /**
     * 根据顾客ids查询顾客集合
     * @param customerIds ids
     */
    public static void queryCustomerListByIds(String customerIds) {
        if (StringUtils.isEmpty(customerIds)){
            throw new WebException(ResponseEnum.customer_id_can_not_be_empty);
        }
    }



}
