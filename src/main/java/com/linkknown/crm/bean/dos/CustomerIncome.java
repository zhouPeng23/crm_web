package com.linkknown.crm.bean.dos;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 顾客收益
 * @author zhoupeng
 */
@Data
public class CustomerIncome {

    private Integer shopId;

    private Integer customerIncomeId;

    private Integer customerId;

    private Integer introduceCustomerId;

    private Integer introduceCustomerAppointmentId;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;

}
