package com.linkknown.crm.bean.dos;

import lombok.Data;

@Data
public class Employee {

    private Integer employeeId;

    private Integer shopId;

    private String employeeName;

    private Integer sex;

    private String phoneNumber;

    private Long birthday;

    private Integer roleId;

    private String roleName;

    private String createBy;

    private Long createTime;

    private String updateBy;

    private Long updateTime;

}