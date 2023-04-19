package com.linkknown.crm.common.util.paramutil;

import com.linkknown.crm.bean.dos.Employee;
import com.linkknown.crm.bean.dos.EmployeeShift;
import com.linkknown.crm.bean.dos.EmployeeShiftTime;
import com.linkknown.crm.bean.req.ModifyPasswordReq;
import com.linkknown.crm.bean.req.UserLoginReq;
import com.linkknown.crm.common.aspect.exception.WebException;
import com.linkknown.crm.common.enums.ResponseEnum;
import com.linkknown.crm.common.util.RegexUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author zhoupeng
 * @date 2023/4/4 14:55
 */
public class EmployeeShiftParamUtils {

    /**
     * 查询班次集合
     * @param employeeShift 请求
     */
    public static void queryShiftList(EmployeeShift employeeShift) {
        //门店id不能为空
        if (StringUtils.isEmpty(employeeShift.getShopId())){
            throw new WebException(ResponseEnum.shop_id_can_not_be_empty);
        }
    }


    /**
     * 添加班次
     * @param employeeShift 请求
     */
    public static void addShift(EmployeeShift employeeShift) {
        //门店id不能为空
        if (StringUtils.isEmpty(employeeShift.getShopId())){
            throw new WebException(ResponseEnum.shop_id_can_not_be_empty);
        }
        //班次名称不能为空
        if (StringUtils.isEmpty(employeeShift.getShiftName())){
            throw new WebException(ResponseEnum.shift_name_can_not_be_empty);
        }
    }


    /**
     * 删除班次
     * @param employeeShift 请求
     */
    public static void deleteShift(EmployeeShift employeeShift) {
        //门店id不能为空
        if (StringUtils.isEmpty(employeeShift.getShopId())){
            throw new WebException(ResponseEnum.shop_id_can_not_be_empty);
        }
        //班次id不能为空
        if (StringUtils.isEmpty(employeeShift.getShiftId())){
            throw new WebException(ResponseEnum.shift_id_can_not_be_empty);
        }
    }


    /**
     * 添加班次时间
     * @param employeeShiftTime 请求
     */
    public static void addShiftTime(EmployeeShiftTime employeeShiftTime) {
        //门店id不能为空
        if (StringUtils.isEmpty(employeeShiftTime.getShopId())){
            throw new WebException(ResponseEnum.shop_id_can_not_be_empty);
        }
        //班次id不能为空
        if (StringUtils.isEmpty(employeeShiftTime.getShiftId())){
            throw new WebException(ResponseEnum.shift_id_can_not_be_empty);
        }
        //开始时间不能为空
        if (StringUtils.isEmpty(employeeShiftTime.getStartTime())){
            throw new WebException(ResponseEnum.shift_time_start_can_not_be_empty);
        }
        //结束时间不能为空
        if (StringUtils.isEmpty(employeeShiftTime.getEndTime())){
            throw new WebException(ResponseEnum.shift_time_end_can_not_be_empty);
        }
        //开始时间不能大于结束时间
        if (employeeShiftTime.getStartTime().getTime() > employeeShiftTime.getEndTime().getTime()){
            throw new WebException(ResponseEnum.shift_time_start_time_cannot_greator_end_time);
        }
    }


    /**
     * 查询班次时间集合
     * @param employeeShiftTime 请求
     */
    public static void queryShiftTimeList(EmployeeShiftTime employeeShiftTime) {
        //门店id不能为空
        if (StringUtils.isEmpty(employeeShiftTime.getShopId())){
            throw new WebException(ResponseEnum.shop_id_can_not_be_empty);
        }
    }





}
