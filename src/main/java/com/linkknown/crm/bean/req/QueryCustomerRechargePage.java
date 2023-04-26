package com.linkknown.crm.bean.req;

import lombok.Data;

/**
 * @author zhoupeng
 * @date 2023/4/5 19:46
 */
@Data
public class QueryCustomerRechargePage {

    private Integer shopId;

    private String customerName;
    private String phoneNumber;

    private Integer pageNo;
    private Integer pageSize;



}
