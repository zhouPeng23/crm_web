package com.linkknown.crm.bean.dos;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

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

    private Date birthday;

    private Integer roleId;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;

}