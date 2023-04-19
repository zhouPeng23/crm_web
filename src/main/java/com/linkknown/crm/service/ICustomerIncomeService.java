package com.linkknown.crm.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linkknown.crm.bean.dos.CustomerIncome;
import com.linkknown.crm.bean.req.QueryCustomerIncomePage;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
public interface ICustomerIncomeService {

    /**
     * 查询顾客收益列表
     * @param queryCustomerIncomePage 请求
     * @return 分页
     */
    Page<CustomerIncome> queryCustomerIncomeList(QueryCustomerIncomePage queryCustomerIncomePage);
}
