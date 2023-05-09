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

    private Integer operateType;

    private BigDecimal rechargeAmount;

    private BigDecimal rechargeCoupon;

    private BigDecimal consumeAmount;

    private String consumeForProject;

    private String consumeForProduct;

    private BigDecimal currentCardTotalAmount;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;

}
