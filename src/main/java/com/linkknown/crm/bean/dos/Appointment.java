package com.linkknown.crm.bean.dos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

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

    private Date appointmentTime;

    private Integer projectId;

    private BigDecimal projectPrice;

    private Integer employeeId;

    private String appointmentStatus;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;


}
