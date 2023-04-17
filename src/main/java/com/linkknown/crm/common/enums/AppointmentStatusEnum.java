package com.linkknown.crm.common.enums;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
public enum AppointmentStatusEnum {

    /**
     * 预约状态
     */
    appointment_wait_use(1, "待处理"),
    appointment_on_use(2, "进行中"),
    appointment_end_use(3, "已结束"),
    appointment_zuo_fei(4, "作废");

    Integer code;
    String msg;

    AppointmentStatusEnum(Integer code, String msg) {
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


    /**
     * 通过code获取描述
     * @param code code
     * @return str
     */
    public static String getCodeDesc(Integer code){
        if (code.equals(appointment_wait_use.getCode())){
            return appointment_wait_use.getMsg();

        }else if (code.equals(appointment_on_use.getCode())){
            return appointment_on_use.getMsg();

        }else if (code.equals(appointment_end_use.getCode())){
            return appointment_end_use.getMsg();

        }else if (code.equals(appointment_zuo_fei.getCode())){
            return appointment_zuo_fei.getMsg();

        }
        return "未知状态";
    }


}
