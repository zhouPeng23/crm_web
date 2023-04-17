package com.linkknown.crm.bean.dos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerIncome {

    private Integer shopId;

    private Integer customerId;

    private Integer introduceCustomerId;

    private Integer introduceCustomerAppointmentId;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;

}
