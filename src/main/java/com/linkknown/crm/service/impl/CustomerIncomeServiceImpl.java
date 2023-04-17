package com.linkknown.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linkknown.crm.bean.dos.Customer;
import com.linkknown.crm.bean.dos.CustomerIncome;
import com.linkknown.crm.bean.req.QueryCustomerIncomePage;
import com.linkknown.crm.mapper.CustomerIncomeMapper;
import com.linkknown.crm.mapper.CustomerMapper;
import com.linkknown.crm.service.ICustomerIncomeService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
@Service
public class CustomerIncomeServiceImpl implements ICustomerIncomeService {

    @Resource
    private CustomerIncomeMapper customerIncomeMapper;

    @Resource
    private CustomerMapper customerMapper;


    /**
     * 查询顾客收益列表
     * @param queryCustomerIncomePage 请求
     * @return 分页
     */
    @Override
    public Page<CustomerIncome> queryCustomerIncomeList(QueryCustomerIncomePage queryCustomerIncomePage) {
        //入参
        String customerName = queryCustomerIncomePage.getCustomerName();
        String phoneNumber = queryCustomerIncomePage.getPhoneNumber();
        Integer pageNo = queryCustomerIncomePage.getPageNo();
        Integer pageSize = queryCustomerIncomePage.getPageSize();

        //查顾客id集合
        Customer customerParams = new Customer();
        if (!StringUtils.isEmpty(customerName)){
            customerParams.setCustomerName(customerName);
        }
        if (!StringUtils.isEmpty(phoneNumber)){
            customerParams.setPhoneNumber(phoneNumber);
        }
        List<Customer> customerList = customerMapper.selectCustomerList(customerParams);
        List<Integer> customerIdList = customerList.stream().map(Customer::getCustomerId).collect(Collectors.toList());

        //根据customerIdList是否为空分情况查
        QueryWrapper<CustomerIncome> queryWrapper = new QueryWrapper<>();
        if (!CollectionUtils.isEmpty(customerIdList)){
            //正常分页查询
            queryWrapper.in("customer_id",customerIdList)
                    .orderByDesc("create_time");

        }else{
            //返回空
            queryWrapper.eq("customer_income_id",-1);
        }

        //返回分页
        return customerIncomeMapper.selectPage(new Page<>(pageNo, pageSize), queryWrapper);
    }



}
