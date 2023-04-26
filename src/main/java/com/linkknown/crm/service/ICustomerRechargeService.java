package com.linkknown.crm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linkknown.crm.bean.dos.CustomerRecharge;
import com.linkknown.crm.bean.req.AddCustomerRechargeReq;
import com.linkknown.crm.bean.req.QueryCustomerRechargePage;

import java.util.List;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
public interface ICustomerRechargeService {
    
    /**
     * 查询顾客充值
     * @param rechargeId 顾客充值ID
     * @return 顾客充值
     */
    public CustomerRecharge selectCustomerRechargeById(Integer rechargeId);


    /**
     * 查询顾客充值记录集合
     * @param queryCustomerRechargePage 请求
     * @return 分页
     */
    Page<CustomerRecharge> queryCustomerRechargeList(QueryCustomerRechargePage queryCustomerRechargePage);


    /**
     * 添加充值
     * @param addCustomerRechargeReq 请求
     */
    public void addCustomerRecharge(AddCustomerRechargeReq addCustomerRechargeReq);


}
