package com.linkknown.crm.common.response;

import com.linkknown.crm.common.enums.ResponseEnum;

import java.io.Serializable;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
public class BaseResponse<T> implements Serializable {
    private String code = ResponseEnum.web_success.getCode();
    private String msg = ResponseEnum.web_success.getMsg();
    private T data;

//=====================================================设置成功信息=====================================================

    /**
     * 成功 - 返回数据
     */
    public static <T> BaseResponse<T> success(T data) {
        BaseResponse<T> resp = new BaseResponse<T>();
        resp.setData(data);
        return resp;
    }

    /**
     * 成功 - 返回提示消息
     */
    public static <T> BaseResponse<T> success(ResponseEnum responseEnum) {
        BaseResponse<T> resp = new BaseResponse<T>();
        resp.setMsg(responseEnum.getMsg());
        return resp;
    }

    /**
     * 成功 - 返回提示消息 & 数据
     */
    public static <T> BaseResponse<T> success(ResponseEnum responseEnum, T data) {
        BaseResponse<T> resp = new BaseResponse<T>();
        resp.setMsg(responseEnum.getMsg());
        resp.setData(data);
        return resp;
    }

//=====================================================设置失败信息=====================================================

    /**
     * 失败 - 返回提示消息
     */
    public static <T> BaseResponse<T> fail(ResponseEnum responseEnum) {
        BaseResponse<T> resp = new BaseResponse<T>();
        resp.setCode(responseEnum.getCode());
        resp.setMsg(responseEnum.getMsg());
        return resp;
    }

    /**
     * 失败 - 返回提示消息 & 数据
     */
    public static <T> BaseResponse<T> fail(ResponseEnum responseEnum, T data) {
        BaseResponse<T> resp = new BaseResponse<T>();
        resp.setCode(responseEnum.getCode());
        resp.setMsg(responseEnum.getMsg());
        resp.setData(data);
        return resp;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
