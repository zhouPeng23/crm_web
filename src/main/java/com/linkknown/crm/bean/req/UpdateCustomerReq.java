package com.linkknown.crm.bean.req;

import lombok.Data;

import java.sql.Date;

/**
 * @author zhoupeng
 * @date 2023/4/5 19:46
 */
@Data
public class UpdateCustomerReq {

    private Integer shopId;

    private Integer customerId;

    private String customerName;

    private Integer sex;

    private String phoneNumber;

    private Date birthday;

    private Integer customerMassLevel;

    private Integer belongToEmployeeId;

    private String hasIntroducedByCustomer;

    private String introducedByCustomerPhoneNumber;

    private String introducedByCustomerName;


}
