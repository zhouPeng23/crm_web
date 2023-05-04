package com.linkknown.crm.bean.req;

import lombok.Data;

/**
 * @author zhoupeng
 * @date 2023/4/5 19:46
 */
@Data
public class DeleteShopCustomerIncomeSetterReq {

    private Integer shopId;

    private Integer setterId;

    private String loginUserPhoneNumber;
    private String password;




}
