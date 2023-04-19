package com.linkknown.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linkknown.crm.bean.dos.EmployeeShift;
import com.linkknown.crm.bean.dos.EmployeeShiftTime;
import com.linkknown.crm.common.aspect.exception.WebException;
import com.linkknown.crm.common.enums.ResponseEnum;
import com.linkknown.crm.common.util.DateUtils;
import com.linkknown.crm.mapper.EmployeeShiftMapper;
import com.linkknown.crm.mapper.EmployeeShiftTimeMapper;
import com.linkknown.crm.service.IEmployeeShiftService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
@Service
public class EmployeeShiftServiceImpl implements IEmployeeShiftService {

    @Resource
    private EmployeeShiftMapper employeeShiftMapper;

    @Resource
    private EmployeeShiftTimeMapper employeeShiftTimeMapper;


    /**
     * 查询班次列表
     * @param employeeShift 请求
     * @return 集合
     */
    @Override
    public List<EmployeeShift> queryShiftList(EmployeeShift employeeShift){
        return employeeShiftMapper.selectEmployeeShiftList(employeeShift);
    }


    /**
     * 查询班次
     * @param shiftId 班次ID
     * @return 班次
     */
    @Override
    public EmployeeShift selectEmployeeShiftById(Integer shiftId) {
        return employeeShiftMapper.selectEmployeeShiftById(shiftId);
    }


    /**
     * 新增班次
     * @param employeeShift 班次
     */
    @Override
    public void addShift(EmployeeShift employeeShift) {
        employeeShiftMapper.insertEmployeeShift(employeeShift);
    }


    /**
     * 删除班次信息
     * @param employeeShift 班次
     */
    @Override
    public void deleteShift(EmployeeShift employeeShift) {
        //入参
        Integer shiftId = employeeShift.getShiftId();

        //删除班次
        employeeShiftMapper.deleteEmployeeShiftById(shiftId);

        //删除班次时间
        EmployeeShiftTime employeeShiftTime = new EmployeeShiftTime();
        employeeShiftTime.setShiftId(shiftId);
        List<Integer> shiftTimeIds = employeeShiftTimeMapper.selectEmployeeShiftTimeList(employeeShiftTime).stream()
                .map(EmployeeShiftTime::getShiftTimeId).collect(Collectors.toList());

        //批量删除
        if (!CollectionUtils.isEmpty(shiftTimeIds)){
            String[] ids = shiftTimeIds.stream().map(Object::toString).toArray(String[]::new);
            employeeShiftTimeMapper.deleteEmployeeShiftTimeByIds(ids);
        }
    }


    /**
     * 添加班次时间
     * @param employeeShiftTime 请求
     */
    @Override
    public void addShiftTime(EmployeeShiftTime employeeShiftTime){
        //入参
        Integer shiftId = employeeShiftTime.getShiftId();
        Time startTimeParam = employeeShiftTime.getStartTime();
        Time endTimeParam = employeeShiftTime.getEndTime();

        //根据班次查目前所有的时间段
        EmployeeShiftTime employeeShiftTimeParam = new EmployeeShiftTime();
        employeeShiftTimeParam.setShiftId(shiftId);
        List<EmployeeShiftTime> employeeShiftTimeList = employeeShiftTimeMapper.selectEmployeeShiftTimeList(employeeShiftTimeParam);

        //循环对比
        if (!CollectionUtils.isEmpty(employeeShiftTimeList)){
            employeeShiftTimeList.forEach(employeeShiftTimeDb -> {
                //已存在记录的时间段
                Time startTimeDb = employeeShiftTimeDb.getStartTime();
                Time endTimeDb = employeeShiftTimeDb.getEndTime();
                //调用工具类对比时间是否有交集
                if (DateUtils.hasIntersectionBetweenSqlTime(startTimeParam,endTimeParam, startTimeDb,endTimeDb)){
                    throw new WebException(ResponseEnum.shift_time_has_chong_he);
                }
            });
        }

        employeeShiftTimeMapper.insertEmployeeShiftTime(employeeShiftTime);
    }


    /**
     * 查询班次时间集合
     * @param employeeShiftTime 请求
     * @return 集合
     */
    @Override
    public List<EmployeeShiftTime> queryShiftTimeList(EmployeeShiftTime employeeShiftTime){
        QueryWrapper<EmployeeShiftTime> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("shop_id",employeeShiftTime.getShopId())
                    .orderByAsc("start_time");
        return employeeShiftTimeMapper.selectList(queryWrapper);
    }


}
