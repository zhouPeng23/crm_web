package com.linkknown.crm.api;

import com.linkknown.crm.bean.req.UserLoginReq;
import com.linkknown.crm.common.aspect.exception.WebExceptionService;
import com.linkknown.crm.common.aspect.paramslog.WebParamsLog;
import com.linkknown.crm.common.checktoken.JwtUtils;
import com.linkknown.crm.common.response.BaseResponse;
import com.linkknown.crm.service.ICustomerService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:42
 */
@WebExceptionService
@RestController
@RequestMapping("/crmWebApi/user")
@Validated
public class LoginController {

    @Resource
    private ICustomerService customService;

    @PostMapping(value = "/login")
    @WebParamsLog(description = "用户登录")
    public BaseResponse<Boolean> login(UserLoginReq userLoginReq){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();

        //登录成功后设置token
        JwtUtils.setResponseHeaderToken(response,"1","张三");
        return BaseResponse.success(true);
    }




}
