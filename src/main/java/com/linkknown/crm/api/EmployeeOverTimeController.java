package com.linkknown.crm.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linkknown.crm.bean.dos.Employee;
import com.linkknown.crm.bean.dos.EmployeeOverTime;
import com.linkknown.crm.bean.req.QueryEmployeeOverTimePage;
import com.linkknown.crm.common.aspect.exception.WebExceptionService;
import com.linkknown.crm.common.aspect.paramslog.WebParamsLog;
import com.linkknown.crm.common.response.BaseResponse;
import com.linkknown.crm.common.util.paramutil.EmployeeOverTimeParamUtils;
import com.linkknown.crm.service.impl.EmployeeOverTimeServiceImpl;
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
@RequestMapping("/crmWebApi/employeeOverTime")
@Validated
public class EmployeeOverTimeController{

    @Resource
    private EmployeeOverTimeServiceImpl employeeOverTimeService;


    @PostMapping(value = "/queryEmployeeOverTimeList")
    @WebParamsLog(description = "查询所有员工加班记录")
    public BaseResponse<Page<EmployeeOverTime>> queryEmployeeOverTimeList(QueryEmployeeOverTimePage queryEmployeeOverTimePage){
        EmployeeOverTimeParamUtils.queryEmployeeOverTimeList(queryEmployeeOverTimePage);
        Page<EmployeeOverTime> page = employeeOverTimeService.queryEmployeeOverTimeList(queryEmployeeOverTimePage);
        return BaseResponse.success(page);
    }


}
