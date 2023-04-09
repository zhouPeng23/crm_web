package com.linkknown.crm.bean.req;

import lombok.Data;

import java.sql.Date;

/**
 * @author zhoupeng
 * @date 2023/4/5 19:46
 */
@Data
public class QueryAppointmentPage {

    private Integer shopId;

    private String phoneNumber;
    private Date appointmentDate;
    private Integer appointmentStatus;

    private Integer pageNo;
    private Integer pageSize;



}
