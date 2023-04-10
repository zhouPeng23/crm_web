package com.linkknown.crm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linkknown.crm.bean.dos.Customer;
import com.linkknown.crm.common.enums.EnumsObject;
import com.linkknown.crm.bean.req.QueryCustomerPage;

import java.util.List;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
public interface ICustomerService {

    /**
     * 查询顾客分页
     * @param queryCustomerPage 请求
     * @return 分页集合
     */
    Page<Customer> queryCustomerList(QueryCustomerPage queryCustomerPage);


    /**
     * 添加顾客
     * @param customer 顾客
     */
    void addCustomer(Customer customer);


    /**
     * 获取顾客等级
     * @return 等级集合
     */
    List<EnumsObject> getAllCustomerMassLevelList();


    /**
     * 更新顾客
     * @param customer 顾客
     */
    void updateCustomer(Customer customer);


    /**
     * 删除顾客
     * @param customer 顾客
     */
    void deleteCustomer(Customer customer);


    /**
     * 查询门店所有顾客
     * @param customer 顾客
     * @return 集合
     */
    List<Customer> queryShopAllCustomer(Customer customer);


    /**
     * 根据手机号查询顾客
     * @param customer 请求
     * @return 顾客
     */
    Customer queryCustomerByPhoneNumber(Customer customer);
}
