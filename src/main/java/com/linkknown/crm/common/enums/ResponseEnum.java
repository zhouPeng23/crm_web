package com.linkknown.crm.common.enums;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
public enum ResponseEnum {

    /**
     * 成功提示信息，都以0000开头
     */
    web_success                                                            ("0000", "成功"),
    add_success                                                            ("0000", "添加成功"),
    delete_success                                                         ("0000", "删除成功"),
    update_success                                                         ("0000", "更新成功"),


    /**
     * 失败提示信息，三位数开头
     */
    web_is_busy("0101", "系统忙,请稍候"),
    //================================================门店==============================================================
    shop_name_can_not_be_empty                                           ("0201", "门店名称不能为空"),
    shop_id_can_not_be_empty                                             ("0202", "门店id不能为空"),
    shop_weizhi_can_not_be_enpty                                         ("0205", "门店位置不能为空"),
    shop_jingying_type_can_not_be_enpty                                 ("0206", "门店经营类型不能为空"),
    shop_jingying_brand_can_not_be_enpty                                ("0207", "门店经营品牌不能为空"),
    shop_opening_date_can_not_be_enpty                                  ("0208", "门店开业日期不能为空"),
    shop_leader_name_can_not_be_enpty                                   ("0209", "店长名称不能为空"),
    shop_leader_sex_can_not_be_enpty                                    ("0210", "店长性别不能为空"),
    shop_leader_phone_number_style_error                                ("0212", "手机号格式错误"),
    shop_has_employee_can_not_delete                                     ("0213", "门店下有员工，无法删除"),
    shop_has_project_can_not_delete                                     ("0214", "门店下有项目，无法删除"),

    //================================================员工==============================================================
    employee_name_can_not_be_empty                                       ("0301", "员工姓名不能为空"),
    employee_sex_can_not_be_empty                                        ("0302", "员工性别不能为空"),
    epmloyee_phone_number_style_error                                    ("0303", "手机号格式错误"),
    employee_role_can_not_be_empty                                       ("0304", "员工角色不能为空"),
    employee_not_exist                                                    ("0306", "用户不存在"),
    employee_number_error                                                 ("0307", "用户数量错误，请联系管理员"),
    phone_number_or_password_error                                       ("0308", "手机号或密码错误"),
    employee_password_can_not_be_empty                                   ("0309", "密码不能为空"),
    employee_phone_number_has_allready_exist                            ("0310", "手机号已注册"),
    employee_has_customer_can_not_delete                                 ("0311", "员工名下有顾客，无法删除"),
    employee_id_can_not_be_empty                                          ("0312", "员工id不能为空"),
    employee_org_password_can_not_be_empty                               ("0313", "原密码不能为空"),
    employee_new_password_can_not_be_empty                               ("0314", "新密码不能为空"),
    employee_new_second_password_can_not_be_empty                       ("0315", "再次输入的密码不能为空"),
    org_password_is_not_right                                             ("0316", "原密码不正确"),
    new_password_and_second_is_not_same                                  ("0317", "两次输入的新密码不一致"),
    phone_number_is_prohibition_of_use                                   ("0318", "该手机号不为员工所有"),

    //================================================顾客==============================================================
    customer_name_can_not_be_empty                                        ("0401", "顾客姓名不能为空"),
    customer_sex_can_not_be_empty                                         ("0402", "顾客性别不能为空"),
    customer_phone_number_style_error                                    ("0403", "顾客手机号格式错误"),
    customer_mass_level_can_not_be_empty                                 ("0405", "顾客等级不能为空"),
    customer_belong_to_employee_id_can_not_be_empty                     ("0406", "顾客所属员工不能为空"),
    customer_phone_number_has_allready_exist                             ("0407", "手机号已注册"),
    customer_id_can_not_be_empty                                          ("0408", "顾客id不能为空"),
    appointment_status_code_id_can_not_be_empty                          ("0409", "预约状态码不能为空"),
    appointment_date_can_not_be_empty                                     ("0410", "预约日期不能为空"),
    appointment_time_can_not_be_empty                                     ("0411", "预约时间不能为空"),
    appointment_project_id_can_not_be_empty                              ("0412", "预约项目不能为空"),
    appointment_id_can_not_be_empty                                       ("0414", "预约id不能为空"),
    appointment_has_already_zuofei                                        ("0415", "预约已处于作废状态"),
    customer_is_new_name_can_not_be_empty                                 ("0416", "该顾客为新人,姓名不能为空"),
    customer_is_new_sex_can_not_be_empty                                 ("0417", "该顾客为新人,性别不能为空"),

    //================================================角色==============================================================
    role_shop_id_can_not_be_empty                                         ("0501", "门店id不能为空"),

    //================================================项目==============================================================
    project_shop_id_can_not_be_empty                                      ("0601", "门店id不能为空"),
    project_project_name_can_not_be_empty                                 ("0602", "项目名称不能为空"),
    project_project_price_style_error                                     ("0603", "项目价格格式错误"),
    project_project_id_can_not_be_empty                                   ("0604", "项目id不能为空"),




    please_contact_the_administrator                                    ("9999", "请联系管理员");

    String code;
    String msg;

    ResponseEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
