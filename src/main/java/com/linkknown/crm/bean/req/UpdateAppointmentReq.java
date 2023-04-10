package com.linkknown.crm.bean.req;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

/**
 * @author zhoupeng
 * @date 2023/4/5 19:46
 */
@Data
public class UpdateAppointmentReq {

    private Integer appointmentId;

    private Date appointmentDate;

    private Time appointmentTime;

    private String projectIds;

    private Integer employeeId;

    /**
     * 预约更新请求需要带上状态
     */
    private Integer appointmentStatus;

}
