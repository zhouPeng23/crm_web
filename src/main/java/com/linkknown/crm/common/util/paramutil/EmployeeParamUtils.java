package com.linkknown.crm.common.util.paramutil;

import com.linkknown.crm.bean.dos.Employee;
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
public class EmployeeParamUtils {


    /**
     * 查询店铺下所有员工集合校验
     * @param employee employee
     */
    public static void queryShopAllEmployeeList(Employee employee) {
        //门店id不能为空
        if (StringUtils.isEmpty(employee.getShopId())){
            throw new WebException(ResponseEnum.shop_id_can_not_be_empty);
        }
    }


    /**
     * 查询店铺下所有正常状态员工集合校验
     * @param employee employee
     */
    public static void queryShopNormalEmployeeList(Employee employee) {
        //门店id不能为空
        if (StringUtils.isEmpty(employee.getShopId())){
            throw new WebException(ResponseEnum.shop_id_can_not_be_empty);
        }
    }


    /**
     * 添加员工校验参数
     * @param employee employee
     */
    public static void addEmployee(Employee employee) {
        //门店id不能为空
        if (StringUtils.isEmpty(employee.getShopId())){
            throw new WebException(ResponseEnum.shop_id_can_not_be_empty);
        }
        //员工姓名不能为空
        if (StringUtils.isEmpty(employee.getEmployeeName())){
            throw new WebException(ResponseEnum.employee_name_can_not_be_empty);
        }
        //员工性别不能为空
        if (StringUtils.isEmpty(employee.getSex())){
            throw new WebException(ResponseEnum.employee_sex_can_not_be_empty);
        }
        //员工班次不能为空
        if (StringUtils.isEmpty(employee.getShiftId())){
            throw new WebException(ResponseEnum.employee_shift_cannot_be_empty);
        }
        //员工手机号格式错误
        if (!RegexUtils.validatePhoneNumber(employee.getPhoneNumber())){
            throw new WebException(ResponseEnum.epmloyee_phone_number_style_error);
        }
        //员工角色不能为空
        if (StringUtils.isEmpty(employee.getRoleId())){
            throw new WebException(ResponseEnum.employee_role_can_not_be_empty);
        }

    }


    /**
     * 更新员工校验参数
     * @param employee employee
     */
    public static void updateEmployee(Employee employee) {
        //门店id不能为空
        if (StringUtils.isEmpty(employee.getShopId())){
            throw new WebException(ResponseEnum.shop_id_can_not_be_empty);
        }
        //员工id不能为空
        if (StringUtils.isEmpty(employee.getEmployeeId())){
            throw new WebException(ResponseEnum.employee_id_can_not_be_empty);
        }
        //员工姓名不能为空
        if (StringUtils.isEmpty(employee.getEmployeeName())){
            throw new WebException(ResponseEnum.employee_name_can_not_be_empty);
        }
        //员工性别不能为空
        if (StringUtils.isEmpty(employee.getSex())){
            throw new WebException(ResponseEnum.employee_sex_can_not_be_empty);
        }
        //员工班次不能为空
        if (StringUtils.isEmpty(employee.getShiftId())){
            throw new WebException(ResponseEnum.employee_shift_cannot_be_empty);
        }
        //员工手机号格式错误
        if (!RegexUtils.validatePhoneNumber(employee.getPhoneNumber())){
            throw new WebException(ResponseEnum.epmloyee_phone_number_style_error);
        }
        //员工角色不能为空
        if (StringUtils.isEmpty(employee.getRoleId())){
            throw new WebException(ResponseEnum.employee_role_can_not_be_empty);
        }

    }


    /**
     * 删除员工
     * @param employee employee
     */
    public static void deleteEmployee(Employee employee) {
        //员工id不能为空
        if (StringUtils.isEmpty(employee.getEmployeeId())){
            throw new WebException(ResponseEnum.employee_id_can_not_be_empty);
        }
    }


    /**
     * 登录校验
     * @param userLoginReq 请求
     */
    public static void loginValidate(UserLoginReq userLoginReq) {
        //员工手机号格式错误
        if (!RegexUtils.validatePhoneNumber(userLoginReq.getPhoneNumber())){
            throw new WebException(ResponseEnum.epmloyee_phone_number_style_error);
        }
        //密码不能为空
        if (StringUtils.isEmpty(userLoginReq.getPassword())){
            throw new WebException(ResponseEnum.employee_password_can_not_be_empty);
        }
    }


    /**
     * 修改密码
     * @param modifyPasswordReq 请求
     */
    public static void modifyPassword(ModifyPasswordReq modifyPasswordReq) {
        //手机号格式错误
        if (!RegexUtils.validatePhoneNumber(modifyPasswordReq.getPhoneNumber())){
            throw new WebException(ResponseEnum.epmloyee_phone_number_style_error);
        }
        //原密码不能为空
        if (StringUtils.isEmpty(modifyPasswordReq.getOldPassword())){
            throw new WebException(ResponseEnum.employee_org_password_can_not_be_empty);
        }
        //新密码不能为空
        if (StringUtils.isEmpty(modifyPasswordReq.getNewPassword())){
            throw new WebException(ResponseEnum.employee_new_password_can_not_be_empty);
        }
        //再次输入的密码不能为空
        if (StringUtils.isEmpty(modifyPasswordReq.getNewPasswordSecond())){
            throw new WebException(ResponseEnum.employee_new_second_password_can_not_be_empty);
        }
    }


    /**
     * 查询员工班次集合
     * @param employee 员工
     */
    public static void queryEmployeeShiftTimeList(Employee employee) {
        //门店id不能为空
        if (StringUtils.isEmpty(employee.getShopId())){
            throw new WebException(ResponseEnum.shop_id_can_not_be_empty);
        }
        //员工id不能为空
        if (StringUtils.isEmpty(employee.getEmployeeId())){
            throw new WebException(ResponseEnum.employee_id_can_not_be_empty);
        }
    }
}
