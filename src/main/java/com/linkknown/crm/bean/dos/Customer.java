package com.linkknown.crm.bean.dos;

import lombok.Data;

@Data
public class Customer {

    private Integer customerId;

    private Integer shopId;

    private String customerName;

    private Integer sex;

    private String phoneNumber;

    private Long birthday;

    private String customerMassLevel;

    private Integer belongToEmployeeId;

    private String belongToEmployeeName;

    private String createBy;

    private Long createTime;

    private String updateBy;

    private Long updateTime;


}