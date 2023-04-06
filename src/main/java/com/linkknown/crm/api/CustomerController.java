package com.linkknown.crm.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linkknown.crm.bean.dos.Customer;
import com.linkknown.crm.bean.req.QueryCustomerPage;
import com.linkknown.crm.common.aspect.exception.WebExceptionService;
import com.linkknown.crm.common.aspect.paramslog.WebParamsLog;
import com.linkknown.crm.common.enums.ResponseEnum;
import com.linkknown.crm.common.response.BaseResponse;
import com.linkknown.crm.common.util.CustomerParamUtils;
import com.linkknown.crm.service.ICustomerService;
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
@RequestMapping("/crmWebApi/customer")
@Validated
public class CustomerController {

    @Resource
    private ICustomerService customService;

    @PostMapping(value = "/queryCustomerList")
    @WebParamsLog(description = "查询顾客集合")
    public BaseResponse<Page<Customer>> queryCustomerList(QueryCustomerPage queryCustomerPage){
        Page<Customer> page = customService.queryCustomerList(queryCustomerPage);
        return BaseResponse.success(page);
    }


    @PostMapping(value = "/addCustomer")
    @WebParamsLog(description = "添加顾客")
    public BaseResponse<Object> addCustomer(Customer customer){
        CustomerParamUtils.addOrUpdateCustomerValidate(customer);
        customService.addCustomer(customer);
        return BaseResponse.success(ResponseEnum.add_success);
    }


    @PostMapping(value = "/updateCustomer")
    @WebParamsLog(description = "更新顾客")
    public BaseResponse<Object> updateCustomer(Customer customer){
        CustomerParamUtils.addOrUpdateCustomerValidate(customer);
        customService.updateCustomer(customer);
        return BaseResponse.success(ResponseEnum.update_success);
    }


    @PostMapping(value = "/getAllCustomerMassLevelList")
    @WebParamsLog(description = "获取所有会员等级集合")
    public BaseResponse<Object> getAllCustomerMassLevelList(Customer customer){
        return BaseResponse.success(customService.getAllCustomerMassLevelList());
    }


    @PostMapping(value = "/deleteCustomer")
    @WebParamsLog(description = "删除顾客")
    public BaseResponse<Object> deleteCustomer(Customer customer){
        customService.deleteCustomer(customer);
        return BaseResponse.success(ResponseEnum.delete_success);
    }


}
