package com.linkknown.crm.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linkknown.crm.bean.dos.Appointment;
import com.linkknown.crm.bean.req.AddAppointmentReq;
import com.linkknown.crm.bean.req.QueryAppointmentPage;
import com.linkknown.crm.bean.req.UpdateAppointmentReq;
import com.linkknown.crm.common.aspect.exception.WebExceptionService;
import com.linkknown.crm.common.aspect.paramslog.WebParamsLog;
import com.linkknown.crm.common.enums.ResponseEnum;
import com.linkknown.crm.common.response.BaseResponse;
import com.linkknown.crm.common.util.paramutil.AppointmentParamUtils;
import com.linkknown.crm.service.IAppointmentService;
import com.linkknown.crm.service.IAppointmentService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:42
 */
@WebExceptionService
@RestController
@RequestMapping("/crmWebApi/appointment")
@Validated
public class AppointmentController {

    @Resource
    private IAppointmentService appointmentService;

    @PostMapping(value = "/queryAppointmentList")
    @WebParamsLog(description = "查询预约集合")
    public BaseResponse<Page<Appointment>> queryAppointmentList(QueryAppointmentPage queryAppointmentPage){
        AppointmentParamUtils.queryAppointmentList(queryAppointmentPage);
        Page<Appointment> page = appointmentService.queryAppointmentList(queryAppointmentPage);
        return BaseResponse.success(page);
    }


    @PostMapping(value = "/addAppointment")
    @WebParamsLog(description = "添加预约")
    public BaseResponse<Object> addAppointment(AddAppointmentReq addAppointmentReq){
        AppointmentParamUtils.addAppointment(addAppointmentReq);
        appointmentService.addAppointment(addAppointmentReq);
        return BaseResponse.success(ResponseEnum.add_success);
    }


    @PostMapping(value = "/updateAppointment")
    @WebParamsLog(description = "更新预约")
    public BaseResponse<Object> updateAppointment(UpdateAppointmentReq updateAppointmentReq){
        AppointmentParamUtils.updateAppointment(updateAppointmentReq);
        appointmentService.updateAppointment(updateAppointmentReq);
        return BaseResponse.success(ResponseEnum.update_success);
    }


}
