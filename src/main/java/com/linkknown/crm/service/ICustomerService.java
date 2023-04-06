package com.linkknown.crm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linkknown.crm.bean.dos.Customer;
import com.linkknown.crm.bean.dos.EnumsObject;
import com.linkknown.crm.bean.req.QueryCustomerPage;

import java.util.List;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
public interface ICustomerService {

    Page<Customer> queryCustomerList(QueryCustomerPage queryCustomerPage);

    void addCustomer(Customer customer);

    List<EnumsObject> getAllCustomerMassLevelList();

    void updateCustomer(Customer customer);

    void deleteCustomer(Customer customer);

}
