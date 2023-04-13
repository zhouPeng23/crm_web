package com.linkknown.crm.service;

import com.linkknown.crm.bean.dos.Employee;
import com.linkknown.crm.bean.req.ModifyPasswordReq;
import com.linkknown.crm.bean.req.UserLoginReq;
import com.linkknown.crm.bean.res.UserLoginRes;

import java.util.List;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
public interface ILoginService {

    /**
     * 登录
     * @param userLoginReq 请求
     * @return 用户
     */
    UserLoginRes login(UserLoginReq userLoginReq);


    /**
     * 修改密码
     * @param modifyPasswordReq 请求
     */
    void modifyPassword(ModifyPasswordReq modifyPasswordReq);

}
