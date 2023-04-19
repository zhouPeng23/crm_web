package com.linkknown.crm.service;

import com.linkknown.crm.bean.dos.Employee;
import com.linkknown.crm.bean.dos.EmployeeShiftTime;
import com.linkknown.crm.bean.req.ModifyPasswordReq;
import com.linkknown.crm.bean.req.UserLoginReq;

import java.util.List;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
public interface IEmployeeService {

    /**
     * 添加员工
     * @param employee 员工
     */
    void addEmployee(Employee employee);


    /**
     * 查询员工集合
     * @param employee 员工
     * @return 集合
     */
    List<Employee> queryEmployeeList(Employee employee);


    /**
     * 更新员工
     * @param employee 员工
     */
    void updateEmployee(Employee employee);


    /**
     * 删除员工
     * @param employee 员工
     */
    void deleteEmployee(Employee employee);


    /**
     * 查询员工班次集合
     * @param employee 员工
     * @return 集合
     */
    List<EmployeeShiftTime> queryEmployeeShiftTimeList(Employee employee);
}
