package com.linkknown.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linkknown.crm.bean.dos.*;
import com.linkknown.crm.bean.req.AddCustomerRechargeReq;
import com.linkknown.crm.bean.req.QueryCustomerRechargePage;
import com.linkknown.crm.common.aspect.exception.WebException;
import com.linkknown.crm.common.enums.ResponseEnum;
import com.linkknown.crm.mapper.*;
import com.linkknown.crm.service.ICustomerRechargeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:42
 */
@Service
public class CustomerRechargeServiceImpl implements ICustomerRechargeService {
    
    @Resource
    private CustomerRechargeMapper customerRechargeMapper;

    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private EmployeeMapper employeeMapper;

    @Resource
    private CustomerIncomeMapper customerIncomeMapper;

    @Resource
    private ShopCustomerIncomeSetterMapper shopCustomerIncomeSetterMapper;


    /**
     * 查询顾客充值
     * @param rechargeId 顾客充值ID
     * @return 顾客充值
     */
    @Override
    public CustomerRecharge selectCustomerRechargeById(Integer rechargeId) {
        return customerRechargeMapper.selectCustomerRechargeById(rechargeId);
    }


    /**
     * 查询顾客充值记录集合
     * @param queryCustomerRechargePage 请求
     * @return 分页
     */
    @Override
    public Page<CustomerRecharge> queryCustomerRechargeList(QueryCustomerRechargePage queryCustomerRechargePage){
        //入参
        Integer shopId = queryCustomerRechargePage.getShopId();
        String customerName = queryCustomerRechargePage.getCustomerName();
        String phoneNumber = queryCustomerRechargePage.getPhoneNumber();
        Integer pageNo = queryCustomerRechargePage.getPageNo();
        Integer pageSize = queryCustomerRechargePage.getPageSize();

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

        QueryWrapper<CustomerRecharge> qw = new QueryWrapper<>();
        if (!CollectionUtils.isEmpty(customerIdList)){
            //查充值记录表
            qw.in("customer_id",customerIdList)
                    .orderByDesc("create_time");

        }else{
            //查充值记录表
            qw.eq("customer_id",-1);
        }
        //返回
        return customerRechargeMapper.selectPage(new Page<>(pageNo, pageSize), qw);
    }


    /**
     * 添加充值
     * @param addCustomerRechargeReq 请求
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addCustomerRecharge(AddCustomerRechargeReq addCustomerRechargeReq){
        //店铺id
        Integer shopId = addCustomerRechargeReq.getShopId();

        //入参 - 操作员
        String loginUserPhoneNumber = addCustomerRechargeReq.getLoginUserPhoneNumber();
        String password = addCustomerRechargeReq.getPassword();

        //入参 - 顾客手机号
        String phoneNumber = addCustomerRechargeReq.getPhoneNumber();
        String customerName = addCustomerRechargeReq.getCustomerName();

        //校验操作员密码
        Employee employee = employeeMapper.selectEmployeeByPhoneNumber(loginUserPhoneNumber);
        if (employee==null){
            throw new WebException(ResponseEnum.not_this_shop_employee_cannot_do_this);
        }
        if (!employee.getPassword().equals(password)){
            throw new WebException(ResponseEnum.password_is_wrong);
        }

        //查询顾客信息
        Customer customer = customerMapper.selectCustomerByPhoneNumber(phoneNumber);
        if (customer==null){
            throw new WebException(ResponseEnum.customer_is_not_exist);
        }
        if (!customer.getCustomerName().equals(customerName)){
            throw new WebException(ResponseEnum.customer_phone_number_and_name_is_error);
        }

        //插入操作
        CustomerRecharge customerRecharge = new CustomerRecharge();
        BeanUtils.copyProperties(addCustomerRechargeReq,customerRecharge);
        customerRecharge.setCustomerId(customer.getCustomerId());
        customerRecharge.setCreateBy(employee.getEmployeeName());
        customerRecharge.setCreateTime(LocalDateTime.now());
        customerRechargeMapper.insertCustomerRecharge(customerRecharge);

        //判断该笔充值是否需要分润
        if (customer.getIntroducedByCustomerId()!=null){
            //分润规则查询
            ShopCustomerIncomeSetter shopCustomerIncomeSetter = new ShopCustomerIncomeSetter();
            shopCustomerIncomeSetter.setShopId(shopId);
            List<ShopCustomerIncomeSetter> shopCustomerIncomeSetterList = shopCustomerIncomeSetterMapper.selectShopCustomerIncomeSetterList(shopCustomerIncomeSetter);

            if (shopCustomerIncomeSetterList.size()==1){
                //需要分润
                Integer introducedByCustomerId = customer.getIntroducedByCustomerId();
                Customer introduceCustomer = customerMapper.selectCustomerById(introducedByCustomerId);

                //分润规则
                ShopCustomerIncomeSetter customerIncomeSetter = shopCustomerIncomeSetterList.get(0);

                //查询充值记录
                CustomerRecharge recharge = new CustomerRecharge();
                recharge.setCustomerId(customer.getCustomerId());
                List<CustomerRecharge> customerRechargeList = customerRechargeMapper.selectCustomerRechargeList(recharge);

                //判断是不是首单
                if (customerRechargeList.size()==1){
                    //首单分润
                    this.addCustomerIncome(customer, customerRecharge, introduceCustomer, customerIncomeSetter.getFirstIncomePoint());

                }else{
                    //非首单分润
                    this.addCustomerIncome(customer, customerRecharge, introduceCustomer, customerIncomeSetter.getForeverIncomePoint());
                }
            }
        }

    }


    /**
     * 添加分润记录
     * @param customer 顾客
     * @param customerRecharge 充值数据
     * @param introduceCustomer 介绍人
     * @param incomePoint 收益点数
     */
    private void addCustomerIncome(Customer customer, CustomerRecharge customerRecharge, Customer introduceCustomer, Integer incomePoint) {
        CustomerIncome customerIncome = new CustomerIncome();
        customerIncome.setShopId(introduceCustomer.getShopId());
        customerIncome.setCustomerId(introduceCustomer.getCustomerId());
        customerIncome.setIntroduceCustomerId(customer.getCustomerId());
        customerIncome.setIntroduceCustomerRechargeId(customerRecharge.getRechargeId());
        customerIncome.setIncomePoint(incomePoint);
        //点数转为百分比
        BigDecimal pointPercent = new BigDecimal(incomePoint).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP);
        //收益金额
        BigDecimal incomeAmouont = customerRecharge.getRechargeAmount().multiply(pointPercent).setScale(2, BigDecimal.ROUND_HALF_UP);
        customerIncome.setIncomeAmount(incomeAmouont);
        customerIncome.setCreateBy("SYSTEM");
        customerIncome.setCreateTime(LocalDateTime.now());
        customerIncomeMapper.insertCustomerIncome(customerIncome);
    }


    /**
     * 根据充值ids批量查充值记录
     * @param rechargeIds ids
     * @return 集合
     */
    @Override
    public List<CustomerRecharge> queryRechargeListByIds(String rechargeIds){
        return customerRechargeMapper.selectRechargeListByIds(rechargeIds.split(","));
    }



}
