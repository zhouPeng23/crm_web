package com.linkknown.crm.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linkknown.crm.bean.dos.CustomerIncome;
import com.linkknown.crm.bean.req.QueryCustomerIncomePage;
import com.linkknown.crm.common.aspect.exception.WebExceptionService;
import com.linkknown.crm.common.aspect.paramslog.WebParamsLog;
import com.linkknown.crm.common.response.BaseResponse;
import com.linkknown.crm.common.util.paramutil.CustomerIncomeParamUtils;
import com.linkknown.crm.service.ICustomerIncomeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:42
 */
@WebExceptionService
@RestController
@RequestMapping("/crmWebApi/customerIncome")
@Validated
public class CustomerIncomeController {

    @Resource
    private ICustomerIncomeService customerIncomeService;

    @PostMapping(value = "/queryCustomerIncomeList")
    @WebParamsLog(description = "查询顾客收益集合")
    public BaseResponse<Page<CustomerIncome>> queryCustomerIncomeList(QueryCustomerIncomePage queryCustomerIncomePage){
        CustomerIncomeParamUtils.queryCustomerIncomeList(queryCustomerIncomePage);
        Page<CustomerIncome> page = customerIncomeService.queryCustomerIncomeList(queryCustomerIncomePage);
        return BaseResponse.success(page);
    }

}
