package com.linkknown.crm.service;

import com.linkknown.crm.bean.dos.Employee;

import java.util.List;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
public interface IEmployeeService {

    void addEmployee(Employee employee);

    List<Employee> queryEmployeeList(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(Employee employee);
}
