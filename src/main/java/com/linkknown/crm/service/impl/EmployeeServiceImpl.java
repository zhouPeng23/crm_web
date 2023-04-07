package com.linkknown.crm.service.impl;

import com.linkknown.crm.bean.dos.Employee;
import com.linkknown.crm.bean.dos.Role;
import com.linkknown.crm.bean.req.UserLoginReq;
import com.linkknown.crm.common.aspect.exception.WebException;
import com.linkknown.crm.common.constants.Constants;
import com.linkknown.crm.common.enums.ResponseEnum;
import com.linkknown.crm.common.util.MD5Utils;
import com.linkknown.crm.mapper.EmployeeMapper;
import com.linkknown.crm.mapper.RoleMapper;
import com.linkknown.crm.service.IEmployeeService;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;

import javax.annotation.Resource;
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
    private RoleMapper roleMapper;


    /**
     * 添加员工
     * @param employee 员工
     */
    @Override
    public void addEmployee(Employee employee) {
        //设置创建人和时间
        employee.setCreateBy("SYSTEM");
        employee.setCreateTime(System.currentTimeMillis());

        //设置初始密码
        employee.setPassword(MD5Utils.md5(Constants.employee_default_password));

        //根据角色id，设置角色名称
        Role role = roleMapper.selectRoleById(Long.valueOf(employee.getRoleId()));
        employee.setRoleName(role.getRoleName());

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
        //设置修改人和时间
        employee.setUpdateBy("SYSTEM");
        employee.setUpdateTime(System.currentTimeMillis());

        //根据角色id，设置角色名称
        Role role = roleMapper.selectRoleById(Long.valueOf(employee.getRoleId()));
        employee.setRoleName(role.getRoleName());

        //更新
        employeeMapper.updateEmployee(employee);
    }


    /**
     * 删除员工
     * @param employee 员工
     */
    @Override
    public void deleteEmployee(Employee employee) {
        employeeMapper.deleteEmployeeById(Long.valueOf(employee.getEmployeeId()));
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





















}
