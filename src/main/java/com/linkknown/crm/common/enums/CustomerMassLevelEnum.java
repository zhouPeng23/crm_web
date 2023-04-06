package com.linkknown.crm.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
public enum CustomerMassLevelEnum {

    /**
     * 会员质量等级
     */
    ordinary_member(1, "普通会员"),
    vip_member(2, "vip会员"),
    senior_member(3, "资深会员");

    Integer code;
    String msg;

    CustomerMassLevelEnum(Integer code, String msg) {
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
