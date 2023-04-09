package com.linkknown.crm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linkknown.crm.bean.dos.Appointment;
import com.linkknown.crm.bean.req.AddAppointmentReq;
import com.linkknown.crm.bean.req.QueryAppointmentPage;
import com.linkknown.crm.bean.req.UpdateAppointmentReq;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
public interface IAppointmentService {


    /**
     * 查询预约分页
     * @param queryAppointmentPage 请求
     * @return 分页集合
     */
    Page<Appointment> queryAppointmentList(QueryAppointmentPage queryAppointmentPage);


    /**
     * 添加预约
     * @param addAppointmentReq 请求
     */
    void addAppointment(AddAppointmentReq addAppointmentReq);


    /**
     * 更新预约
     * @param updateAppointmentReq 请求
     */
    void updateAppointment(UpdateAppointmentReq updateAppointmentReq);


    /**
     * 查询预约状态集合
     * @return 集合
     */
    Object queryAppointmentStatusList();


    /**
     * 作废预约
     * @param appointment 预约
     */
    void zuofeiAppointment(Appointment appointment);
}
