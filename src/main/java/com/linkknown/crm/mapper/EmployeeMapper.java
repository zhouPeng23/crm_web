package com.linkknown.crm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linkknown.crm.bean.dos.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhoupeng
 */
@Repository
public interface EmployeeMapper extends BaseMapper<Employee> {

    public Employee selectEmployeeById(Long employeeId);

    public List<Employee> selectEmployeeList(Employee employee);

    public int insertEmployee(Employee employee);

    public int updateEmployee(Employee employee);

    public int deleteEmployeeById(Long employeeId);

    public int deleteEmployeeByIds(String[] employeeIds);
}