package com.linkknown.crm.bean.dos;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
public class Investor {

    private Integer shopId;

    private Integer investorId;

    private String investorName;

    private Integer sex;

    private String phoneNumber;

    private String password;

    private Date birthday;

    private BigDecimal investorAmount;

    private BigDecimal holdingProportion;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;

}
