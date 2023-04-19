package com.linkknown.crm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linkknown.crm.bean.dos.EmployeeShift;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhoupeng
 */
@Repository
public interface EmployeeShiftMapper extends BaseMapper<EmployeeShift> {

    public EmployeeShift selectEmployeeShiftById(Integer shiftId);

    public List<EmployeeShift> selectEmployeeShiftList(EmployeeShift employeeShift);

    public int insertEmployeeShift(EmployeeShift employeeShift);

    public int updateEmployeeShift(EmployeeShift employeeShift);

    public int deleteEmployeeShiftById(Integer shiftId);

}
