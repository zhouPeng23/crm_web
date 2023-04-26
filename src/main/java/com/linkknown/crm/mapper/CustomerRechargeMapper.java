package com.linkknown.crm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linkknown.crm.bean.dos.CustomerRecharge;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhoupeng
 */
@Repository
public interface CustomerRechargeMapper extends BaseMapper<CustomerRecharge> {

    public CustomerRecharge selectCustomerRechargeById(Integer rechargeId);

    public List<CustomerRecharge> selectRechargeListByIds(String[] rechargeIds);

    public List<CustomerRecharge> selectCustomerRechargeList(CustomerRecharge customerRecharge);

    public int insertCustomerRecharge(CustomerRecharge customerRecharge);

    public int updateCustomerRecharge(CustomerRecharge customerRecharge);

    public int deleteCustomerRechargeById(Integer rechargeId);

    public int deleteCustomerRechargeByIds(String[] rechargeIds);
}
