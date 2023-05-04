package com.linkknown.crm.bean.dos;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 顾客收益设置
 * @author zhoupeng
 */
@Data
public class ShopCustomerIncomeSetter {
    
    private Integer shopId;
    
    private Integer setterId;
    
    private Integer firstIncomePoint;
    
    private Integer foreverIncomePoint;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;
}
