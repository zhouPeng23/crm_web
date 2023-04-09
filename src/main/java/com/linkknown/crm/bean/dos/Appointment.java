package com.linkknown.crm.bean.dos;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.time.LocalDateTime;

/**
 * 预约
 * @author zhoupeng
 */
@Data
public class Appointment{

    private Integer shopId;

    private Integer appointmentId;

    private Integer customerId;

    private Date appointmentDate;

    private Time appointmentTime;

    private Integer projectId;

    private BigDecimal projectPrice;

    private Integer employeeId;

    private Integer appointmentStatus;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;


}
