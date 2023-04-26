package com.linkknown.crm.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linkknown.crm.bean.dos.Customer;
import com.linkknown.crm.bean.dos.CustomerRecharge;
import com.linkknown.crm.bean.req.AddCustomerRechargeReq;
import com.linkknown.crm.bean.req.QueryCustomerPage;
import com.linkknown.crm.bean.req.QueryCustomerRechargePage;
import com.linkknown.crm.common.aspect.exception.WebExceptionService;
import com.linkknown.crm.common.aspect.paramslog.WebParamsLog;
import com.linkknown.crm.common.enums.ResponseEnum;
import com.linkknown.crm.common.response.BaseResponse;
import com.linkknown.crm.common.util.paramutil.CustomerParamUtils;
import com.linkknown.crm.common.util.paramutil.CustomerRechargeParamUtils;
import com.linkknown.crm.service.ICustomerRechargeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:42
 */
@WebExceptionService
@RestController
@RequestMapping("/crmWebApi/customerRecharge")
@Validated
public class CustomerRechargeController{

    @Resource
    private ICustomerRechargeService customerRechargeService;

    @PostMapping(value = "/queryCustomerRechargeList")
    @WebParamsLog(description = "查询顾客充值记录集合")
    public BaseResponse<Page<CustomerRecharge>> queryCustomerRechargeList(QueryCustomerRechargePage queryCustomerRechargePage){
        CustomerRechargeParamUtils.queryCustomerRechargeList(queryCustomerRechargePage);
        Page<CustomerRecharge> page = customerRechargeService.queryCustomerRechargeList(queryCustomerRechargePage);
        return BaseResponse.success(page);
    }


    @PostMapping(value = "/addCustomerRecharge")
    @WebParamsLog(description = "添加充值")
    public BaseResponse<Object> addCustomerRecharge(AddCustomerRechargeReq addCustomerRechargeReq){
        CustomerRechargeParamUtils.addCustomerRecharge(addCustomerRechargeReq);
        customerRechargeService.addCustomerRecharge(addCustomerRechargeReq);
        return BaseResponse.success(ResponseEnum.add_success);
    }



}
