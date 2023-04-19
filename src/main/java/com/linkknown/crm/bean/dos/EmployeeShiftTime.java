package com.linkknown.crm.bean.dos;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 班次时间
 * @author zhoupeng
 */
@Data
public class EmployeeShiftTime {

    private Integer shopId;

    private Integer shiftId;

    private Integer shiftTimeId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

}
