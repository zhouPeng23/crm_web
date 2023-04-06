package com.linkknown.crm.service.impl;

import com.linkknown.crm.bean.dos.Employee;
import com.linkknown.crm.bean.dos.Role;
import com.linkknown.crm.mapper.EmployeeMapper;
import com.linkknown.crm.mapper.RoleMapper;
import com.linkknown.crm.service.IEmployeeService;
import org.springframework.stereotype.Service;

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

    @Override
    public List<Employee> queryEmployeeList(Employee employee) {
        return employeeMapper.selectEmployeeList(employee);
    }


    @Override
    public void addEmployee(Employee employee) {
        //设置创建人和时间
        employee.setCreateBy("SYSTEM");
        employee.setCreateTime(System.currentTimeMillis());

        //根据角色id，设置角色名称
        Role role = roleMapper.selectRoleById(Long.valueOf(employee.getRoleId()));
        employee.setRoleName(role.getRoleName());

        //插入
        employeeMapper.insertEmployee(employee);
    }


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


    @Override
    public void deleteEmployee(Employee employee) {
        employeeMapper.deleteEmployeeById(Long.valueOf(employee.getEmployeeId()));
    }


}
