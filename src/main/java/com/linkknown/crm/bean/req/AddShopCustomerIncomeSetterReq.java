package com.linkknown.crm.bean.req;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhoupeng
 * @date 2023/4/5 19:46
 */
@Data
public class AddShopCustomerIncomeSetterReq {

    private Integer shopId;

    private Integer firstIncomePoint;
    private Integer foreverIncomePoint;

    private String loginUserPhoneNumber;
    private String password;




}
