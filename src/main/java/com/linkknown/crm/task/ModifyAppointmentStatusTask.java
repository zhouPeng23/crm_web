package com.linkknown.crm.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linkknown.crm.CrmWebApplication;
import com.linkknown.crm.bean.dos.Appointment;
import com.linkknown.crm.common.enums.AppointmentStatusEnum;
import com.linkknown.crm.common.util.DateUtils;
import com.linkknown.crm.mapper.AppointmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoupeng
 * @date 2023/4/10 20:28
 */
@Component
public class ModifyAppointmentStatusTask {

    private static Logger logger = LoggerFactory.getLogger(CrmWebApplication.class);

    @Resource
    private AppointmentMapper appointmentMapper;

    /**
     * 每隔5分钟执行一次 - 修改预约单状态
     */
    @Scheduled(cron = "0 */2 * * * ?")
    public void modifyAppointmentStatus() {
        logger.info("==========>正在执行修改预约单状态定时任务");

        // 查看预约单的状态，如果某个单子已经过期了6个小时，系统自动将单子设置为“已结束”
        //1.查询所有“待处理”“进行中”的预约单
        QueryWrapper<Appointment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("appointment_status",AppointmentStatusEnum.appointment_wait_use.getCode())
                    .or()
                    .eq("appointment_status",AppointmentStatusEnum.appointment_on_use.getCode());
        //查询
        List<Appointment> appointmentList = appointmentMapper.selectList(queryWrapper);

        //2.计算这些单子预约具体时间与当前时间的差值，找出过期6小时的单子集合
        List<String> needUpdateAppointmentIdList = new ArrayList<>();
        appointmentList.forEach(appointment -> {
            // 将 date 和 time 转换成毫秒数
            Long appointmentDateTime = appointment.getAppointmentDate().getTime() + appointment.getAppointmentTime().getTime();

            // 计算两个时间戳之间的毫秒数差
            long diffInMilliseconds = System.currentTimeMillis() - appointmentDateTime;

            // 将毫秒数差转换成小时数
            double diffInHours = diffInMilliseconds / (60 * 60 * 1000);

            if (diffInHours>=6){
                needUpdateAppointmentIdList.add(appointment.getAppointmentId().toString());
            }
        });

        logger.info("==========>经查询需要修改预约单状态为“已结束”的预约单ids:{}",needUpdateAppointmentIdList);

        //3.将这些单子设置为“已结束”
        String[] strIds = needUpdateAppointmentIdList.toArray(new String[0]);
        if (strIds.length>0){
            appointmentMapper.updateAppointmentStatusEndByBatchIds(strIds);
        }else{
            logger.info("==========>无需执行");
        }

    }







}
