package com.linkknown.crm.common.util.paramutil;

import com.linkknown.crm.bean.dos.Appointment;
import com.linkknown.crm.bean.dos.Customer;
import com.linkknown.crm.bean.req.AddAppointmentReq;
import com.linkknown.crm.bean.req.QueryAppointmentPage;
import com.linkknown.crm.bean.req.UpdateAppointmentReq;
import com.linkknown.crm.common.aspect.exception.WebException;
import com.linkknown.crm.common.enums.ResponseEnum;
import com.linkknown.crm.common.util.RegexUtils;
import org.springframework.util.StringUtils;

/**
 * @author zhoupeng
 * @date 2023/4/4 14:55
 */
public class AppointmentParamUtils {


    /**
     * 查询预约集合
     * @param queryAppointmentPage 入参
     */
    public static void queryAppointmentList(QueryAppointmentPage queryAppointmentPage) {
        //门店id不能为空
        if (StringUtils.isEmpty(queryAppointmentPage.getShopId())){
            throw new WebException(ResponseEnum.shop_id_can_not_be_empty);
        }
    }


    /**
     * 添加预约
     * @param addAppointmentReq 预约
     */
    public static void addAppointment(AddAppointmentReq addAppointmentReq) {
        //门店id不能为空
        if (StringUtils.isEmpty(addAppointmentReq.getShopId())){
            throw new WebException(ResponseEnum.shop_id_can_not_be_empty);
        }
        //顾客手机号格式错误
        if (!RegexUtils.validatePhoneNumber(addAppointmentReq.getPhoneNumber())){
            throw new WebException(ResponseEnum.customer_phone_number_style_error);
        }

        //预约日期
        if (StringUtils.isEmpty(addAppointmentReq.getAppointmentDate())){
            throw new WebException(ResponseEnum.appointment_date_can_not_be_empty);
        }
        //预约时间
        if (StringUtils.isEmpty(addAppointmentReq.getAppointmentTime())){
            throw new WebException(ResponseEnum.appointment_time_can_not_be_empty);
        }
        //预约项目
        if (StringUtils.isEmpty(addAppointmentReq.getProjectIds())){
            throw new WebException(ResponseEnum.appointment_project_id_can_not_be_empty);
        }
        //顾客所属员工不能为空
        if (StringUtils.isEmpty(addAppointmentReq.getEmployeeId())){
            throw new WebException(ResponseEnum.customer_belong_to_employee_id_can_not_be_empty);
        }
    }


    /**
     * 更新预约
     * @param updateAppointmentReq 请求
     */
    public static void updateAppointment(UpdateAppointmentReq updateAppointmentReq) {
        //预约id不能为空
        if (StringUtils.isEmpty(updateAppointmentReq.getAppointmentId())){
            throw new WebException(ResponseEnum.appointment_id_can_not_be_empty);
        }
        //顾客手机号格式错误
        if (!RegexUtils.validatePhoneNumber(updateAppointmentReq.getPhoneNumber())){
            throw new WebException(ResponseEnum.customer_phone_number_style_error);
        }

        //预约日期
        if (StringUtils.isEmpty(updateAppointmentReq.getAppointmentDate())){
            throw new WebException(ResponseEnum.appointment_date_can_not_be_empty);
        }
        //预约时间
        if (StringUtils.isEmpty(updateAppointmentReq.getAppointmentTime())){
            throw new WebException(ResponseEnum.appointment_time_can_not_be_empty);
        }
        //预约项目
        if (StringUtils.isEmpty(updateAppointmentReq.getProjectIds())){
            throw new WebException(ResponseEnum.appointment_project_id_can_not_be_empty);
        }
        //顾客所属员工不能为空
        if (StringUtils.isEmpty(updateAppointmentReq.getEmployeeId())){
            throw new WebException(ResponseEnum.customer_belong_to_employee_id_can_not_be_empty);
        }
        //预约状态码不能为空
        if (StringUtils.isEmpty(updateAppointmentReq.getAppointmentStatus())){
            throw new WebException(ResponseEnum.appointment_status_code_id_can_not_be_empty);
        }


    }


    /**
     * 作废预约
     * @param appointment 预约
     */
    public static void zuofeiAppointment(Appointment appointment) {
        if (StringUtils.isEmpty(appointment.getAppointmentId())){
            throw new WebException(ResponseEnum.appointment_id_can_not_be_empty);
        }
    }




}
