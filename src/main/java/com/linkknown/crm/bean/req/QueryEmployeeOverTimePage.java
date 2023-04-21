package com.linkknown.crm.bean.req;

import lombok.Data;

import java.sql.Date;

/**
 * @author zhoupeng
 * @date 2023/4/5 19:46
 */
@Data
public class QueryEmployeeOverTimePage {

    private Integer shopId;

    private Integer employeeId;
    private Date startDate;
    private Date endDate;

    private Integer pageNo;
    private Integer pageSize;



}
