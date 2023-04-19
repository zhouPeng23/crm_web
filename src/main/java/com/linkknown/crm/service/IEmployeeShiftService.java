package com.linkknown.crm.service;

import com.linkknown.crm.bean.dos.EmployeeShift;
import java.util.List;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
public interface IEmployeeShiftService {


    /**
     * 查询班次列表
     * @param employeeShift 请求
     * @return 集合
     */
    List<EmployeeShift> queryShiftList(EmployeeShift employeeShift);


    /**
     * 查询班次
     * @param shiftId 班次ID
     * @return 班次
     */
    public EmployeeShift selectEmployeeShiftById(Integer shiftId);


    /**
     * 新增班次
     * @param employeeShift 班次
     */
    public void addShift(EmployeeShift employeeShift);


    /**
     * 删除班次信息
     * @param employeeShift 班次
     */
    public void deleteShift(EmployeeShift employeeShift);



}
