package com.linkknown.crm.bean.dos;

import lombok.Data;

/**
 * 员工
 * @author zhoupeng
 */
@Data
public class Employee {

    private Integer employeeId;

    private Integer shopId;

    private String employeeName;

    private Integer sex;

    private String phoneNumber;

    private String password;

    private Long birthday;

    private Integer roleId;

    private String createBy;

    private Long createTime;

    private String updateBy;

    private Long updateTime;

}