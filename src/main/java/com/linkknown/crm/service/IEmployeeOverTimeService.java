package com.linkknown.crm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linkknown.crm.bean.dos.EmployeeOverTime;
import com.linkknown.crm.bean.req.QueryEmployeeOverTimePage;

import java.util.List;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
public interface IEmployeeOverTimeService {


    /**
     * 查询所有员工加班记录
     * @param queryEmployeeOverTimePage 请求
     * @return 结果
     */
    Page<EmployeeOverTime> queryEmployeeOverTimeList(QueryEmployeeOverTimePage queryEmployeeOverTimePage);


    /**
     * 新增员工加班记录
     * @param employeeOverTime 员工加班记录
     * @return 结果
     */
    public int insertEmployeeOverTime(EmployeeOverTime employeeOverTime);


    /**
     * 查询员工加班记录
     * @param overTimeId 员工加班记录ID
     * @return 员工加班记录
     */
    public EmployeeOverTime selectEmployeeOverTimeById(Integer overTimeId);


    /**
     * 查询员工加班记录列表
     * @param employeeOverTime 员工加班记录
     * @return 员工加班记录
     */
    public List<EmployeeOverTime> selectEmployeeOverTimeList(EmployeeOverTime employeeOverTime);


}
