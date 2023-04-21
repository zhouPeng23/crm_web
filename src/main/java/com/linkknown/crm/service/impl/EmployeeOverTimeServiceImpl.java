package com.linkknown.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linkknown.crm.bean.dos.EmployeeOverTime;
import com.linkknown.crm.bean.req.QueryEmployeeOverTimePage;
import com.linkknown.crm.mapper.EmployeeOverTimeMapper;
import com.linkknown.crm.service.IEmployeeOverTimeService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
@Service
public class EmployeeOverTimeServiceImpl implements IEmployeeOverTimeService {

    @Resource
    private EmployeeOverTimeMapper employeeOverTimeMapper;


    /**
     * 查询所有员工加班记录
     * @param queryEmployeeOverTimePage 请求
     * @return 结果
     */
    @Override
    public Page<EmployeeOverTime> queryEmployeeOverTimeList(QueryEmployeeOverTimePage queryEmployeeOverTimePage) {
        //入参-查询条件
        Integer shopId = queryEmployeeOverTimePage.getShopId();
        Integer employeeId = queryEmployeeOverTimePage.getEmployeeId();
        Date startDate = queryEmployeeOverTimePage.getStartDate();
        Date endDate = queryEmployeeOverTimePage.getEndDate();

        //入参-页码
        Integer pageNo = queryEmployeeOverTimePage.getPageNo();
        Integer pageSize = queryEmployeeOverTimePage.getPageSize();

        //分页查询
        QueryWrapper<EmployeeOverTime> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("shop_id",shopId)
                .eq(!StringUtils.isEmpty(employeeId),"employee_id",employeeId)
                .ge(!StringUtils.isEmpty(startDate),"create_time",startDate)
                .lt(!StringUtils.isEmpty(endDate),"create_time",endDate)
                .orderByDesc("create_time");

        //返回
        return  employeeOverTimeMapper.selectPage(new Page<>(pageNo, pageSize), queryWrapper);
    }


    /**
     * 新增员工加班记录
     * @param employeeOverTime 员工加班记录
     * @return 结果
     */
    @Override
    public int insertEmployeeOverTime(EmployeeOverTime employeeOverTime) {
        return employeeOverTimeMapper.insertEmployeeOverTime(employeeOverTime);
    }


    /**
     * 查询员工加班记录
     * @param overTimeId 员工加班记录ID
     * @return 员工加班记录
     */
    @Override
    public EmployeeOverTime selectEmployeeOverTimeById(Integer overTimeId) {
        return employeeOverTimeMapper.selectEmployeeOverTimeById(overTimeId);
    }


    /**
     * 查询员工加班记录列表
     * @param employeeOverTime 员工加班记录
     * @return 员工加班记录
     */
    @Override
    public List<EmployeeOverTime> selectEmployeeOverTimeList(EmployeeOverTime employeeOverTime) {
        return employeeOverTimeMapper.selectEmployeeOverTimeList(employeeOverTime);
    }


}
