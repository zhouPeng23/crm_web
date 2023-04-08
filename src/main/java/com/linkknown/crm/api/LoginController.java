package com.linkknown.crm.api;

import com.linkknown.crm.bean.dos.Employee;
import com.linkknown.crm.bean.req.ModifyPasswordReq;
import com.linkknown.crm.bean.req.UserLoginReq;
import com.linkknown.crm.common.aspect.exception.WebExceptionService;
import com.linkknown.crm.common.aspect.paramslog.WebParamsLog;
import com.linkknown.crm.common.checktoken.JwtUtils;
import com.linkknown.crm.common.enums.ResponseEnum;
import com.linkknown.crm.common.response.BaseResponse;
import com.linkknown.crm.common.util.paramutil.EmployeeParamUtils;
import com.linkknown.crm.service.IEmployeeService;
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
    private IEmployeeService employeeService;

    @PostMapping(value = "/login")
    @WebParamsLog(description = "用户登录")
    public BaseResponse<Object> login(UserLoginReq userLoginReq){
        //入参非空校验
        EmployeeParamUtils.loginValidate(userLoginReq);

        //登录验证
        Employee employee = employeeService.login(userLoginReq);

        //获取response
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();

        //登录成功后设置token
        JwtUtils.setResponseHeaderToken(response,String.valueOf(employee.getEmployeeId()),employee.getEmployeeName());

        //密码就不返回了
        employee.setPassword("");
        return BaseResponse.success(employee);
    }


    @PostMapping(value = "/logout")
    @WebParamsLog(description = "退出登录")
    public BaseResponse<Boolean> logout(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        //退出登录后设置token为空字符串
        JwtUtils.setResponseHeaderTokenEmpty(response);
        return BaseResponse.success(true);
    }


    @PostMapping(value = "/modifyPassword")
    @WebParamsLog(description = "修改密码")
    public BaseResponse<Boolean> modifyPassword(ModifyPasswordReq modifyPasswordReq){
        //入参非空校验
        EmployeeParamUtils.modifyPassword(modifyPasswordReq);
        //修改密码
        employeeService.modifyPassword(modifyPasswordReq);
        //返回
        return BaseResponse.success(ResponseEnum.web_success);
    }


}
