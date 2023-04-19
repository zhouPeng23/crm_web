package com.linkknown.crm.bean.dos;

import lombok.Data;
import java.sql.Time;

/**
 * 班次时间
 * @author zhoupeng
 */
@Data
public class EmployeeShiftTime {

    private Integer shopId;

    private Integer shiftId;

    private Integer shiftTimeId;

    private Time startTime;

    private Time endTime;

}
