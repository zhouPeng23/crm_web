package com.linkknown.crm.service;

import com.linkknown.crm.bean.dos.EmployeeShift;
import com.linkknown.crm.bean.dos.EmployeeShiftTime;

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


    /**
     * 添加班次时间
     * @param employeeShiftTime 请求
     */
    public void addShiftTime(EmployeeShiftTime employeeShiftTime);


    /**
     * 查询班次时间集合
     * @param employeeShiftTime 请求
     * @return 集合
     */
    public List<EmployeeShiftTime> queryShiftTimeList(EmployeeShiftTime employeeShiftTime);



}
