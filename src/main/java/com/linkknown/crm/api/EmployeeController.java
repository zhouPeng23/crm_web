package com.linkknown.crm.api;

import com.linkknown.crm.bean.dos.Employee;
import com.linkknown.crm.common.aspect.exception.WebExceptionService;
import com.linkknown.crm.common.aspect.paramslog.WebParamsLog;
import com.linkknown.crm.common.enums.ResponseEnum;
import com.linkknown.crm.common.response.BaseResponse;
import com.linkknown.crm.common.util.paramutil.EmployeeParamUtils;
import com.linkknown.crm.service.IEmployeeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:42
 */
@WebExceptionService
@RestController
@RequestMapping("/crmWebApi/employee")
@Validated
public class EmployeeController {

    @Resource
    private IEmployeeService employeeService;

    @PostMapping(value = "/queryEmployeeList")
    @WebParamsLog(description = "查询员工集合")
    public BaseResponse<List<Employee>> queryEmployeeList(Employee employee){
        EmployeeParamUtils.queryEmployeeList(employee);
        List<Employee> employeeList = employeeService.queryEmployeeList(employee);
        return BaseResponse.success(employeeList);
    }


    @PostMapping(value = "/addEmployee")
    @WebParamsLog(description = "添加员工")
    public BaseResponse<Object> addEmployee(Employee employee){
        EmployeeParamUtils.addEmployee(employee);
        employeeService.addEmployee(employee);
        return BaseResponse.success(ResponseEnum.add_success);
    }


    @PostMapping(value = "/updateEmployee")
    @WebParamsLog(description = "修改员工")
    public BaseResponse<Object> updateEmployee(Employee employee){
        EmployeeParamUtils.updateEmployee(employee);
        employeeService.updateEmployee(employee);
        return BaseResponse.success(ResponseEnum.update_success);
    }


    @PostMapping(value = "/deleteEmployee")
    @WebParamsLog(description = "删除员工")
    public BaseResponse<Object> deleteEmployee(Employee employee){
        EmployeeParamUtils.deleteEmployee(employee);
        employeeService.deleteEmployee(employee);
        return BaseResponse.success(ResponseEnum.delete_success);
    }


}
