package com.linkknown.crm.bean.dos;

import lombok.Data;

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

    private Long birthday;

    private Integer customerMassLevel;

    private Integer belongToEmployeeId;

    private String createBy;

    private Long createTime;

    private String updateBy;

    private Long updateTime;


}