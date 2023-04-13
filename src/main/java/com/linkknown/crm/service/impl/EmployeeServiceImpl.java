package com.linkknown.crm.service.impl;

import com.linkknown.crm.bean.dos.Customer;
import com.linkknown.crm.bean.dos.Employee;
import com.linkknown.crm.bean.dos.Role;
import com.linkknown.crm.bean.req.ModifyPasswordReq;
import com.linkknown.crm.bean.req.UserLoginReq;
import com.linkknown.crm.common.aspect.exception.WebException;
import com.linkknown.crm.common.enums.ResponseEnum;
import com.linkknown.crm.common.util.MD5Utils;
import com.linkknown.crm.mapper.CustomerMapper;
import com.linkknown.crm.mapper.EmployeeMapper;
import com.linkknown.crm.mapper.RoleMapper;
import com.linkknown.crm.service.IEmployeeService;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import sun.security.provider.MD5;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Resource
    private CustomerMapper customerMapper;

    @Value("${employee.default.password}")
    private String employeeDefaultPassword;


    /**
     * 添加员工
     * @param employee 员工
     */
    @Override
    public void addEmployee(Employee employee) {
        //根据手机号,校验用户是否已存在
        Employee employeeSearch = new Employee();
        employeeSearch.setPhoneNumber(employee.getPhoneNumber());
        List<Employee> employeeSearchList = employeeMapper.selectEmployeeList(employeeSearch);
        if (!CollectionUtils.isEmpty(employeeSearchList)){
            throw new WebException(ResponseEnum.employee_phone_number_has_allready_exist);
        }

        //设置创建人和时间
        employee.setCreateBy("SYSTEM");
        employee.setCreateTime(LocalDateTime.now());

        //设置初始密码
        employee.setPassword(MD5Utils.md5(employeeDefaultPassword));

        //插入
        employeeMapper.insertEmployee(employee);
    }


    /**
     * 查询员工集合
     * @param employee 员工
     * @return 集合
     */
    @Override
    public List<Employee> queryEmployeeList(Employee employee) {
        return employeeMapper.selectEmployeeList(employee);
    }


    /**
     * 更新员工
     * @param employee 员工
     */
    @Override
    public void updateEmployee(Employee employee) {
        //校验用户是否修改了手机号
        Employee employeeDb = employeeMapper.selectEmployeeById(employee.getEmployeeId());
        if (!employee.getPhoneNumber().equals(employeeDb.getPhoneNumber())){
            //修改了手机号
            Employee employeeSearch = new Employee();
            employeeSearch.setPhoneNumber(employee.getPhoneNumber());
            List<Employee> employeeSearchList = employeeMapper.selectEmployeeList(employeeSearch);
            if (!CollectionUtils.isEmpty(employeeSearchList)){
                throw new WebException(ResponseEnum.employee_phone_number_has_allready_exist);
            }
        }

        //设置修改人和时间
        employee.setUpdateBy("SYSTEM");
        employee.setUpdateTime(LocalDateTime.now());

        //更新
        employeeMapper.updateEmployee(employee);
    }


    /**
     * 删除员工
     * @param employee 员工
     */
    @Override
    public void deleteEmployee(Employee employee) {
        //查看员工名下是否有顾客
        Customer customer = new Customer();
        customer.setBelongToEmployeeId(employee.getEmployeeId());
        List<Customer> customerList = customerMapper.selectCustomerList(customer);
        if (!CollectionUtils.isEmpty(customerList)){
            throw new WebException(ResponseEnum.employee_has_customer_can_not_delete);
        }

        //删除
        employeeMapper.deleteEmployeeById(employee.getEmployeeId());
    }


    /**
     * 登录
     * @param userLoginReq 请求
     * @return 用户
     */
    @Override
    public Employee login(UserLoginReq userLoginReq) {
        //入参
        String phoneNumberParam = userLoginReq.getPhoneNumber();
        String passwordParam = userLoginReq.getPassword();

        //查询用户
        Employee employeeParam = new Employee();
        employeeParam.setPhoneNumber(phoneNumberParam);
        List<Employee> employeeList = employeeMapper.selectEmployeeList(employeeParam);
        if (employeeList.size()==0){
            throw new WebException(ResponseEnum.employee_not_exist);
        }
        if (employeeList.size()>1){
            throw new WebException(ResponseEnum.employee_number_error);
        }

        //查到一个用户后对比密码
        Employee employeeDb = employeeList.get(0);
        if (!passwordParam.equals(employeeDb.getPassword())){
            throw new WebException(ResponseEnum.employee_phone_number_or_password_error);
        }

        //认证通过^_^
        return employeeDb;
    }


    /**
     * 修改密码
     * @param modifyPasswordReq 请求
     */
    @Override
    public void modifyPassword(ModifyPasswordReq modifyPasswordReq) {
        //入参
        Integer employeeId = modifyPasswordReq.getEmployeeId();
        String oldPassword = modifyPasswordReq.getOldPassword();
        String newPassword = modifyPasswordReq.getNewPassword();
        String newPasswordSecond = modifyPasswordReq.getNewPasswordSecond();

        //判断原密码是否正确
        Employee employee = employeeMapper.selectEmployeeById(employeeId);
        if (!oldPassword.equals(employee.getPassword())){
            throw new WebException(ResponseEnum.employee_org_password_is_not_right);
        }

        //判断两次输入的新密码是否一致
        if (!newPassword.equals(newPasswordSecond)){
            throw new WebException(ResponseEnum.employee_new_password_and_second_is_not_same);
        }

        //入库新密码
        Employee employeeModify = new Employee();
        employeeModify.setEmployeeId(employeeId);
        employeeModify.setPassword(newPassword);
        employeeMapper.updateEmployee(employeeModify);
    }


}
