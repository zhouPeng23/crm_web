package com.linkknown.crm.common.enums;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
public enum StatusEnum {

    /**
     * 账户状态
     */
    normal(1, "正常"),
    disabled(0, "已禁用"),
    deleted(-1, "已删除");

    Integer code;
    String msg;

    StatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
