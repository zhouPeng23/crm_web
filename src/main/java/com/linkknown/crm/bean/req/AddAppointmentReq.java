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
public class AddAppointmentReq {

    private Integer shopId;

    private String phoneNumber;

    private String customerName;

    private Integer sex;

    private Date appointmentDate;

    private Time appointmentTime;

    private String projectIds;

    private Integer employeeId;



}
