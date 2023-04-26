package com.linkknown.crm.bean.req;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhoupeng
 * @date 2023/4/5 19:46
 */
@Data
public class AddCustomerRechargeReq {

    private Integer shopId;

    private String phoneNumber;
    private String customerName;
    private Integer sex;
    private BigDecimal rechargeAmount;
    private String rechargeForProject;
    private String rechargeForProduct;
    private String remark;

    private String loginUserPhoneNumber;
    private String password;




}
