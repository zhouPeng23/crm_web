package com.linkknown.crm.common.aspect.exception;

import com.linkknown.crm.common.enums.ResponseEnum;
import com.linkknown.crm.common.response.BaseResponse;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
public class WebException extends RuntimeException {

    private BaseResponse response;

    /**
     * 返回错误枚举
     */
    public WebException(ResponseEnum responseEnum) {
        //直接返回定义过的错误信息枚举
        this.response = BaseResponse.fail(responseEnum);
    }


    public BaseResponse getResponse() {
        return response;
    }

    public void setResponse(BaseResponse response) {
        this.response = response;
    }
}
