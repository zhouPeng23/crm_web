package com.linkknown.crm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linkknown.crm.bean.dos.EmployeeOverTime;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhoupeng
 */
@Repository
public interface EmployeeOverTimeMapper extends BaseMapper<EmployeeOverTime> {

    public EmployeeOverTime selectEmployeeOverTimeById(Integer overTimeId);

    public List<EmployeeOverTime> selectEmployeeOverTimeList(EmployeeOverTime employeeOverTime);

    public int insertEmployeeOverTime(EmployeeOverTime employeeOverTime);

    public int updateEmployeeOverTime(EmployeeOverTime employeeOverTime);

    public int deleteEmployeeOverTimeById(Integer overTimeId);

    public int deleteEmployeeOverTimeByIds(String[] overTimeIds);
}
