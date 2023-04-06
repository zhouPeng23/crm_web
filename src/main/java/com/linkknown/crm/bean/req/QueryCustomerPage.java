package com.linkknown.crm.bean.req;

import lombok.Data;

/**
 * @author zhoupeng
 * @date 2023/4/5 19:46
 */
@Data
public class QueryCustomerPage {

    private Integer shopId;

    private String customerName;
    private String phoneNumber;
    private Integer sex;
    private String customerMassLevel;
    private Integer belongToEmployeeId;

    private Integer pageNo;
    private Integer pageSize;



}
