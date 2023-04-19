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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhoupeng
 * @date 2023/4/10 20:28
 */
@Component
public class ModifyAppointmentStatus2OnUseTask {

    private static Logger logger = LoggerFactory.getLogger(CrmWebApplication.class);

    @Resource
    private AppointmentMapper appointmentMapper;

    /**
     * 每隔1分钟执行一次 - 修改预约单状态
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void modifyAppointmentStatus() {
        logger.info("==========>正在执行修改预约单状态为“进行中”定时任务");

        //1.查询所有“待处理”的预约单
        QueryWrapper<Appointment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("appointment_status",AppointmentStatusEnum.appointment_wait_use.getCode());

        //查询
        List<Appointment> appointmentList = appointmentMapper.selectList(queryWrapper);
        logger.info("以下是所有处于'待处理'中的预约单");

        //2.计算这些单子预约具体时间与当前时间的差值
        List<String> needUpdateAppointmentIdList = new ArrayList<>();
        appointmentList.forEach(appointment -> {

            //预约时间
            Date appointmentDate = DateUtils.formatSqlDateAndTime2UtilDate(appointment.getAppointmentDate(), appointment.getAppointmentTime());
            logger.info("预约id:{},预约时间:{}, - {}",appointment.getAppointmentId(),DateUtils.formatUtilDateStr(appointmentDate),AppointmentStatusEnum.getCodeDesc(appointment.getAppointmentStatus()));

            // 转换成毫秒数
            Long appointmentDateTime = appointmentDate.getTime();

            // 计算两个时间戳之间的毫秒数差
            long diffInMilliseconds = System.currentTimeMillis() - appointmentDateTime;

            if (diffInMilliseconds>=0){
                needUpdateAppointmentIdList.add(appointment.getAppointmentId().toString());
            }
        });

        logger.info("==========>已到开始时间,需要修改预约单状态为“进行中”的预约单ids:{}",needUpdateAppointmentIdList);

        //3.将这些单子设置为“进行中”
        String[] strIds = needUpdateAppointmentIdList.toArray(new String[0]);
        if (strIds.length>0){
            appointmentMapper.updateAppointmentStatusOnUseByBatchIds(strIds);
        }else{
            logger.info("==========>ids为空无需执行");
        }

    }







}
