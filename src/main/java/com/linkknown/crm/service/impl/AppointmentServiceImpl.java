package com.linkknown.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linkknown.crm.bean.dos.Appointment;
import com.linkknown.crm.bean.dos.Customer;
import com.linkknown.crm.bean.dos.Project;
import com.linkknown.crm.bean.req.AddAppointmentReq;
import com.linkknown.crm.bean.req.QueryAppointmentPage;
import com.linkknown.crm.bean.req.UpdateAppointmentReq;
import com.linkknown.crm.common.aspect.exception.WebException;
import com.linkknown.crm.common.enums.AppointmentStatusEnum;
import com.linkknown.crm.common.enums.CustomerMassLevelEnum;
import com.linkknown.crm.common.enums.EnumsObject;
import com.linkknown.crm.common.enums.ResponseEnum;
import com.linkknown.crm.mapper.AppointmentMapper;
import com.linkknown.crm.mapper.CustomerMapper;
import com.linkknown.crm.mapper.ProjectMapper;
import com.linkknown.crm.service.IAppointmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
@Service
public class AppointmentServiceImpl implements IAppointmentService {

    @Resource
    private AppointmentMapper appointmentMapper;

    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private ProjectMapper projectMapper;


    /**
     * 查询预约分页
     * @param queryAppointmentPage 请求
     * @return 分页集合
     */
    @Override
    public Page<Appointment> queryAppointmentList(QueryAppointmentPage queryAppointmentPage) {
        //入参 - 门店id
        Integer shopId = queryAppointmentPage.getShopId();
        //入参 - 查询条件
        String phoneNumber = queryAppointmentPage.getPhoneNumber();
        Date appointmentDateStart = queryAppointmentPage.getAppointmentDateStart();
        Date appointmentDateEnd = queryAppointmentPage.getAppointmentDateEnd();
        Integer appointmentStatus = queryAppointmentPage.getAppointmentStatus();
        //入参 - 分页
        Integer pageNo = queryAppointmentPage.getPageNo();
        Integer pageSize = queryAppointmentPage.getPageSize();

        //查询条件组装
        QueryWrapper<Appointment> queryWrapper = new QueryWrapper<>();

        //思路: 手机号如果不为空，则精确查找, 如果为空，则不根据手机号查找，而是根据其他条件查
        if (StringUtils.isEmpty(phoneNumber)){
            //分页查询
            queryWrapper.eq("shop_id",shopId)
                    .ge(!StringUtils.isEmpty(appointmentDateStart),"appointment_date",appointmentDateStart)
                    .le(!StringUtils.isEmpty(appointmentDateEnd),"appointment_date",appointmentDateEnd)
                    .eq(!StringUtils.isEmpty(appointmentStatus),"appointment_status",appointmentStatus)
                    .orderByDesc("create_time");
            //返回
            return  appointmentMapper.selectPage(new Page<>(pageNo, pageSize), queryWrapper);

        }else{
            //先根据手机号查询到具体顾客
            Customer customerParam = new Customer();
            customerParam.setPhoneNumber(phoneNumber);
            List<Customer> customerList = customerMapper.selectCustomerList(customerParam);

            if (!CollectionUtils.isEmpty(customerList)){
                //查到了具体顾客
                Customer customer = customerList.get(0);
                //分页查询 - 根据customerId查记录
                queryWrapper.eq("shop_id",shopId)
                        .eq(!StringUtils.isEmpty(customer.getCustomerId()),"customer_id",customer.getCustomerId())
                        .orderByDesc("create_time");

            }else{
                //未查该顾客 - 让界面显示0条记录
                queryWrapper.eq("appointment_id",-1L);

            }
            //返回
            return  appointmentMapper.selectPage(new Page<>(pageNo, pageSize), queryWrapper);
        }

    }


    /**
     * 添加预约
     * @param addAppointmentReq 请求
     */
    @Override
    public void addAppointment(AddAppointmentReq addAppointmentReq) {
        //入库的预约实体
        Appointment appointment = new Appointment();
        BeanUtils.copyProperties(addAppointmentReq,appointment);
        //设置预约状态
        appointment.setAppointmentStatus(AppointmentStatusEnum.appointment_wait_use.getCode());
        //设置预约项目总金额
        appointment.setProjectPrice(this.tongjiProjectPriceByIds(addAppointmentReq.getProjectIds()));

        //根据手机号查询顾客表
        Customer customerParam = new Customer();
        customerParam.setPhoneNumber(addAppointmentReq.getPhoneNumber());
        List<Customer> customerList = customerMapper.selectCustomerList(customerParam);
        if (!CollectionUtils.isEmpty(customerList)){
            //老顾客
            Customer customer = customerList.get(0);
            appointment.setCustomerId(customer.getCustomerId());
            //设置创建人和时间
            appointment.setCreateBy("SYSTEM");
            appointment.setCreateTime(LocalDateTime.now());
            appointmentMapper.insertAppointment(appointment);

        }else{
            //新顾客 - 先添加顾客表
            Customer customer = new Customer();
            customer.setShopId(addAppointmentReq.getShopId());
            customer.setCustomerName(addAppointmentReq.getCustomerName());
            customer.setSex(addAppointmentReq.getSex());
            customer.setPhoneNumber(addAppointmentReq.getPhoneNumber());
            customer.setCustomerMassLevel(CustomerMassLevelEnum.ordinary_member.getCode());
            customer.setBelongToEmployeeId(addAppointmentReq.getEmployeeId());
            //插入顾客表
            customerMapper.insertCustomer(customer);

            appointment.setCustomerId(customer.getCustomerId());
            //设置创建人和时间
            appointment.setCreateBy("SYSTEM");
            appointment.setCreateTime(LocalDateTime.now());
            appointmentMapper.insertAppointment(appointment);
        }
    }


    /**
     * 更新预约
     * @param updateAppointmentReq 请求
     */
    @Override
    public void updateAppointment(UpdateAppointmentReq updateAppointmentReq) {
        //更新入库的预约实体
        Appointment appointment = new Appointment();
        BeanUtils.copyProperties(updateAppointmentReq,appointment);

        //设置预约项目总金额
        appointment.setProjectPrice(this.tongjiProjectPriceByIds(updateAppointmentReq.getProjectIds()));

        //设置更新人和时间
        appointment.setUpdateBy("SYSTEM");
        appointment.setUpdateTime(LocalDateTime.now());
        appointmentMapper.updateAppointment(appointment);
    }


    /**
     * 统计项目价格
     * @param projectIds 项目ids
     * @return 总金额
     */
    private BigDecimal tongjiProjectPriceByIds(String projectIds) {
        //定义返回结果
        BigDecimal totalAmount = BigDecimal.ZERO;

        //逗号分隔，将数组转为集合
        String[] projectIdsArray = projectIds.split(",");

        //批量查询
        List<Project> projectList = projectMapper.selectProjectByIds(projectIdsArray);
        for (Project project:projectList){
            totalAmount = totalAmount.add(project.getProjectPrice());
        }

        //返回总金额
        return totalAmount;
    }


    /**
     * 查询预约状态集合
     * @return 集合
     */
    @Override
    public Object queryAppointmentStatusList() {
        //源数据
        AppointmentStatusEnum[] enums = AppointmentStatusEnum.values();

        //返回集合
        List<EnumsObject> enumsObjectList = new ArrayList<>();
        //装配数据
        Arrays.stream(enums).forEach(e -> {
            EnumsObject enumsObject = new EnumsObject();
            enumsObject.setMsg(e.getMsg());
            enumsObject.setCode(e.getCode());
            enumsObjectList.add(enumsObject);
        });

        //返回
        return enumsObjectList;
    }


    /**
     * 作废预约
     * @param appointment 预约
     */
    @Override
    public void zuofeiAppointment(Appointment appointment) {
        Appointment appointmentParam = new Appointment();
        appointmentParam.setAppointmentId(appointment.getAppointmentId());
        appointmentParam.setAppointmentStatus(AppointmentStatusEnum.appointment_zuo_fei.getCode());

        //设置更新人和时间
        appointmentParam.setUpdateBy("SYSTEM");
        appointmentParam.setUpdateTime(LocalDateTime.now());

        //更新为作废状态
        appointmentMapper.updateAppointment(appointmentParam);

    }






}
