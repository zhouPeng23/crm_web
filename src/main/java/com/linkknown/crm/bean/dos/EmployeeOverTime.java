package com.linkknown.crm.bean.dos;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 员工加班记录
 * @author zhoupeng
 */
@Data
public class EmployeeOverTime{

    private Integer shopId;

    private Integer employeeId;

    private Integer overTimeId;

    private String shiftTimeStr;

    private Integer appointmentId;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;

}
