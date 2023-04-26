package com.linkknown.crm.bean.dos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 顾客充值表
 * @author zhoupeng
 */
@Data
public class CustomerRecharge{

    private Integer shopId;

    private Integer rechargeId;

    private Integer customerId;

    private BigDecimal rechargeAmount;

    private String rechargeForProject;

    private String rechargeForProduct;

    private String remark;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;

}
