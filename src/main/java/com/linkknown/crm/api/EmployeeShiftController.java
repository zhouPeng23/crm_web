package com.linkknown.crm.api;

import com.linkknown.crm.bean.dos.EmployeeShift;
import com.linkknown.crm.bean.dos.EmployeeShiftTime;
import com.linkknown.crm.common.aspect.exception.WebExceptionService;
import com.linkknown.crm.common.aspect.paramslog.WebParamsLog;
import com.linkknown.crm.common.enums.ResponseEnum;
import com.linkknown.crm.common.response.BaseResponse;
import com.linkknown.crm.common.util.paramutil.EmployeeShiftParamUtils;
import com.linkknown.crm.service.IEmployeeShiftService;
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
@RequestMapping("/crmWebApi/shift")
@Validated
public class EmployeeShiftController {

    @Resource
    private IEmployeeShiftService employeeShiftService;

    @PostMapping(value = "/queryShiftList")
    @WebParamsLog(description = "查询班次集合")
    public BaseResponse<List<EmployeeShift>> queryShiftList(EmployeeShift employeeShift) {
        EmployeeShiftParamUtils.queryShiftList(employeeShift);
        List<EmployeeShift> employeeShiftList = employeeShiftService.queryShiftList(employeeShift);
        return BaseResponse.success(employeeShiftList);
    }


    @PostMapping(value = "/addShift")
    @WebParamsLog(description = "添加班次")
    public BaseResponse<Object> addShift(EmployeeShift employeeShift) {
        EmployeeShiftParamUtils.addShift(employeeShift);
        employeeShiftService.addShift(employeeShift);
        return BaseResponse.success(ResponseEnum.add_success);
    }


    @PostMapping(value = "/deleteShift")
    @WebParamsLog(description = "删除班次")
    public BaseResponse<Object> deleteShift(EmployeeShift employeeShift) {
        EmployeeShiftParamUtils.deleteShift(employeeShift);
        employeeShiftService.deleteShift(employeeShift);
        return BaseResponse.success(ResponseEnum.delete_success);
    }


    @PostMapping(value = "/addShiftTime")
    @WebParamsLog(description = "添加班次时间")
    public BaseResponse<Object> addShiftTime(EmployeeShiftTime employeeShiftTime) {
        EmployeeShiftParamUtils.addShiftTime(employeeShiftTime);
        employeeShiftService.addShiftTime(employeeShiftTime);
        return BaseResponse.success(ResponseEnum.add_success);
    }


    @PostMapping(value = "/queryShiftTimeList")
    @WebParamsLog(description = "查询班次时间集合")
    public BaseResponse<List<EmployeeShiftTime>> queryShiftTimeList(EmployeeShiftTime employeeShiftTime) {
        EmployeeShiftParamUtils.queryShiftTimeList(employeeShiftTime);
        List<EmployeeShiftTime> employeeShiftTimeList = employeeShiftService.queryShiftTimeList(employeeShiftTime);
        return BaseResponse.success(employeeShiftTimeList);
    }


}
