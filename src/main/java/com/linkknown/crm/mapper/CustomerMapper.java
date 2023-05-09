package com.linkknown.crm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linkknown.crm.bean.dos.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhoupeng
 */
@Repository
public interface CustomerMapper extends BaseMapper<Customer> {

    public Customer selectCustomerById(Integer customerId);

    public List<Customer> selectCustomerList(Customer customer);

    public List<Customer> selectCustomerListByIds(String[] customerIds);

    public int insertCustomer(Customer customer);

    public int updateCustomer(Customer customer);

    public int deleteCustomerById(Integer customerId);

    public int deleteCustomerByIds(String[] customerIds);

    public Customer selectCustomerByPhoneNumber(String phoneNumber);

    void updateCustomerAmount(Customer updateAmountCustomer);
}