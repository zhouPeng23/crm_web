package com.linkknown.crm.bean.req;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhoupeng
 * @date 2023/4/5 19:46
 */
@Data
public class AddCustomerConsumeReq {

    private Integer shopId;

    private String phoneNumber;
    private String customerName;
    private Integer sex;
    private BigDecimal consumeAmount;
    private String consumeForProject;
    private String consumeForProduct;

    private String loginUserPhoneNumber;
    private String password;




}
