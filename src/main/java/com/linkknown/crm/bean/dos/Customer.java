package com.linkknown.crm.bean.dos;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 * 顾客
 * @author zhoupeng
 */
@Data
public class Customer {

    private Integer customerId;

    private Integer shopId;

    private String customerName;

    private Integer sex;

    private String phoneNumber;

    private Date birthday;

    private Integer customerMassLevel;

    private Integer belongToEmployeeId;

    private Integer introducedByCustomerId;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;


}