package com.linkknown.crm.service.impl;

import com.linkknown.crm.bean.dos.Employee;
import com.linkknown.crm.bean.dos.Investor;
import com.linkknown.crm.bean.req.ModifyPasswordReq;
import com.linkknown.crm.bean.req.UserLoginReq;
import com.linkknown.crm.bean.res.UserLoginRes;
import com.linkknown.crm.common.aspect.exception.WebException;
import com.linkknown.crm.common.enums.ResponseEnum;
import com.linkknown.crm.common.enums.StatusEnum;
import com.linkknown.crm.mapper.EmployeeMapper;
import com.linkknown.crm.mapper.InvestorMapper;
import com.linkknown.crm.mapper.RoleMapper;
import com.linkknown.crm.service.ILoginService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
@Service
public class LoginServiceImpl implements ILoginService {

    @Resource
    private InvestorMapper investorMapper;

    @Resource
    private EmployeeMapper employeeMapper;

    @Resource
    private RoleMapper roleMapper;


    /**
     * 登录
     * @param userLoginReq 请求
     * @return 用户
     */
    @Override
    public UserLoginRes login(UserLoginReq userLoginReq) {
        //入参
        String phoneNumberParam = userLoginReq.getPhoneNumber();
        String passwordParam = userLoginReq.getPassword();

        //创建返回对象
        UserLoginRes userLoginRes = new UserLoginRes();

        //查询资方
        Investor investor = investorMapper.selectInvestorByPhoneNumber(phoneNumberParam);
        if (investor!=null){
            //检查账户状态
            this.checkAccountStatus(investor.getStatus());
            //对比密码
            if (!passwordParam.equals(investor.getPassword())){
                throw new WebException(ResponseEnum.phone_number_or_password_error);
            }
            //设置返回对象值
            userLoginRes.setLoginUserPhoneNumber(investor.getPhoneNumber());
            userLoginRes.setLoginUserName(investor.getInvestorName());
            userLoginRes.setLoginUserCanSearchShopIds(investor.getShopIds());
            userLoginRes.setLoginUserCanSearchAuthMenu("待定");

        }else{
            //员工登录
            Employee employeeParam = new Employee();
            employeeParam.setPhoneNumber(phoneNumberParam);
            List<Employee> employeeList = employeeMapper.selectEmployeeList(employeeParam);
            if (employeeList.size()==0){
                throw new WebException(ResponseEnum.employee_not_exist);
            }
            if (employeeList.size()>1){
                throw new WebException(ResponseEnum.employee_number_error);
            }
            //查到用户
            Employee employeeDb = employeeList.get(0);
            //检查账户状态
            this.checkAccountStatus(employeeDb.getStatus());
            //对比密码
            if (!passwordParam.equals(employeeDb.getPassword())){
                throw new WebException(ResponseEnum.phone_number_or_password_error);
            }
            //设置返回对象值
            userLoginRes.setLoginUserPhoneNumber(employeeDb.getPhoneNumber());
            userLoginRes.setLoginUserName(employeeDb.getEmployeeName());
            userLoginRes.setLoginUserCanSearchShopIds(employeeDb.getShopId().toString());
            userLoginRes.setLoginUserCanSearchAuthMenu(roleMapper.selectRoleById(employeeDb.getRoleId()).getAuthMenu());
        }

        //认证通过^_^
        return userLoginRes;
    }


    /**
     * 检查账户状态
     * @param status 状态
     */
    private void checkAccountStatus(Integer status) {
        //非正常状态做个提示
        if (!StatusEnum.normal.getCode().equals(status)){
            //禁用
            if (StatusEnum.disabled.getCode().equals(status)){
                throw new WebException(ResponseEnum.account_has_been_disabled);
            }
            //已删除
            if (StatusEnum.deleted.getCode().equals(status)){
                throw new WebException(ResponseEnum.account_has_been_deleted);
            }

            //统一提示
            throw new WebException(ResponseEnum.account_status_yi_chang);
        }
    }


    /**
     * 修改密码
     * @param modifyPasswordReq 请求
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void modifyPassword(ModifyPasswordReq modifyPasswordReq) {
        //入参
        String phoneNumber = modifyPasswordReq.getPhoneNumber();
        String oldPassword = modifyPasswordReq.getOldPassword();
        String newPassword = modifyPasswordReq.getNewPassword();
        String newPasswordSecond = modifyPasswordReq.getNewPasswordSecond();

        //查询资方
        Investor investor = investorMapper.selectInvestorByPhoneNumber(phoneNumber);
        if (investor!=null){
            //资方修改
            //判断原密码是否正确
            if (!oldPassword.equals(investor.getPassword())){
                throw new WebException(ResponseEnum.org_password_is_not_right);
            }

            //判断两次输入的新密码是否一致
            if (!newPassword.equals(newPasswordSecond)){
                throw new WebException(ResponseEnum.new_password_and_second_is_not_same);
            }

            //入库新密码
            Investor investorModify = new Investor();
            investorModify.setInvestorId(investor.getInvestorId());
            investorModify.setPassword(newPassword);
            investorMapper.updateInvestor(investorModify);

        }else{
            //员工修改
            //判断原密码是否正确
            Employee employee = employeeMapper.selectEmployeeByPhoneNumber(phoneNumber);
            if (!oldPassword.equals(employee.getPassword())){
                throw new WebException(ResponseEnum.org_password_is_not_right);
            }

            //判断两次输入的新密码是否一致
            if (!newPassword.equals(newPasswordSecond)){
                throw new WebException(ResponseEnum.new_password_and_second_is_not_same);
            }

            //入库新密码
            Employee employeeModify = new Employee();
            employeeModify.setEmployeeId(employee.getEmployeeId());
            employeeModify.setPassword(newPassword);
            employeeMapper.updateEmployee(employeeModify);
        }
    }
}
