package com.linkknown.crm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linkknown.crm.bean.dos.EmployeeShiftTime;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhoupeng
 */
@Repository
public interface EmployeeShiftTimeMapper extends BaseMapper<EmployeeShiftTime> {

    public EmployeeShiftTime selectEmployeeShiftTimeById(Integer shiftTimeId);

    public List<EmployeeShiftTime> selectEmployeeShiftTimeList(EmployeeShiftTime employeeShiftTime);

    public int insertEmployeeShiftTime(EmployeeShiftTime employeeShiftTime);

    public int updateEmployeeShiftTime(EmployeeShiftTime employeeShiftTime);

    public int deleteEmployeeShiftTimeById(Integer shiftTimeId);

    public void deleteEmployeeShiftTimeByIds(String[] shiftTimeIds);
}
