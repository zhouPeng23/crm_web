package com.linkknown.crm.bean.req;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

/**
 * @author zhoupeng
 * @date 2023/4/5 19:46
 */
@Data
public class AddCustomerReq {

    private Integer shopId;

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
