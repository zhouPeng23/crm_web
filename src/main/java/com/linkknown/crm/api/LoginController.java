package com.linkknown.crm.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linkknown.crm.bean.dos.Customer;
import com.linkknown.crm.bean.req.QueryCustomerPage;
import com.linkknown.crm.bean.req.UserLoginReq;
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
@RequestMapping("/crmWebApi/login")
@Validated
public class LoginController {

    @Resource
    private ICustomerService customService;

    @PostMapping(value = "/userLogin")
    @WebParamsLog(description = "用户登录")
    public BaseResponse<Boolean> userLogin(UserLoginReq userLoginReq){

        return BaseResponse.success(true);
    }




}
