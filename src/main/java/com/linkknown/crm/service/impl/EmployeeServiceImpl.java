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
import com.linkknown.crm.mapper.InvestorMapper;
import com.linkknown.crm.mapper.RoleMapper;
import com.linkknown.crm.service.IEmployeeService;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
    private InvestorMapper investorMapper;

    @Resource
    private CustomerMapper customerMapper;

    @Value("${employee.default.password}")
    private String employeeDefaultPassword;


    /**
     * 添加员工
     * @param employee 员工
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addEmployee(Employee employee) {
        //资方手机号校验
        if (investorMapper.selectInvestorByPhoneNumber(employee.getPhoneNumber())!=null){
            throw new WebException(ResponseEnum.phone_number_is_prohibition_of_use);
        }

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
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateEmployee(Employee employee) {
        //资方手机号校验
        if (investorMapper.selectInvestorByPhoneNumber(employee.getPhoneNumber())!=null){
            throw new WebException(ResponseEnum.phone_number_is_prohibition_of_use);
        }

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
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
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



}
