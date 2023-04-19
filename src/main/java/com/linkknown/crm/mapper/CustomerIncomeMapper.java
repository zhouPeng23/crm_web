package com.linkknown.crm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linkknown.crm.bean.dos.CustomerIncome;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhoupeng
 */
@Repository
public interface CustomerIncomeMapper extends BaseMapper<CustomerIncome> {

    public CustomerIncome selectCustomerIncomeById(Integer customerIncomeId);

    public List<CustomerIncome> selectCustomerIncomeList(CustomerIncome customerIncome);

    public int insertCustomerIncome(CustomerIncome customerIncome);

    public int updateCustomerIncome(CustomerIncome customerIncome);

    public int deleteCustomerIncomeById(Integer customerIncomeId);

    public int deleteCustomerIncomeByIds(String[] customerIncomeIds);
}
