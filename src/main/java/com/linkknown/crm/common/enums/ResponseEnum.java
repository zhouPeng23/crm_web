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
    selected_success                                                       ("0000", "选择成功"),


    /**
     * 失败提示信息，三位数开头
     */
    web_is_busy("0101", "系统忙,请稍候"),
    //================================================店铺==============================================================
    shop_name_can_not_be_empty                                           ("0201", "店铺名称不能为空"),
    shop_id_can_not_be_empty                                             ("0202", "店铺id不能为空"),
    selected_shop_number_in_db_error                                    ("0203", "数据库中已选择的店铺数量错误"),
    shop_is_selected_can_not_delete                                     ("0204", "店铺正在被选择，无法删除"),
    shop_weizhi_can_not_be_enpty                                         ("0205", "店铺位置不能为空"),
    shop_jingying_type_can_not_be_enpty                                 ("0206", "店铺经营类型不能为空"),
    shop_jingying_brand_can_not_be_enpty                                ("0207", "店铺经营品牌不能为空"),
    shop_opening_date_can_not_be_enpty                                  ("0208", "店铺开业日期不能为空"),
    shop_leader_name_can_not_be_enpty                                   ("0209", "店长名称不能为空"),
    shop_leader_sex_can_not_be_enpty                                    ("0210", "店长性别不能为空"),
    shop_leader_phone_number_style_error                                ("0212", "手机号格式错误"),
    shop_has_employee_can_not_delete                                     ("0213", "店铺下有员工，无法删除"),

    //================================================员工==============================================================
    employee_name_can_not_be_empty                                       ("0301", "员工姓名不能为空"),
    employee_sex_can_not_be_empty                                        ("0302", "员工性别不能为空"),
    epmloyee_phone_number_style_error                                    ("0303", "手机号格式错误"),
    employee_role_can_not_be_empty                                       ("0304", "员工角色不能为空"),
    employee_birthday_can_not_be_empty                                   ("0305", "员工生日日期不能为空"),
    employee_not_exist                                                    ("0306", "用户不存在"),
    employee_number_error                                                 ("0307", "用户数量错误，请联系管理员"),
    employee_phone_number_or_password_error                             ("0308", "手机号或密码错误"),
    employee_password_can_not_be_empty                                   ("0309", "密码不能为空"),
    employee_phone_number_has_allready_exist                            ("0310", "手机号已注册"),
    employee_has_customer_can_not_delete                                 ("0311", "员工名下有顾客，无法删除"),

    //================================================顾客==============================================================
    customer_name_can_not_be_empty                                        ("0401", "顾客姓名不能为空"),
    customer_sex_can_not_be_empty                                         ("0402", "顾客性别不能为空"),
    customer_phone_number_style_error                                    ("0403", "顾客手机号格式错误"),
    customer_birthday_can_not_be_empty                                   ("0404", "顾客生日日期不能为空"),
    customer_mass_level_can_not_be_empty                                 ("0405", "顾客等级不能为空"),
    customer_belong_to_employee_id_can_not_be_empty                     ("0406", "顾客所属员工不能为空"),
    customer_phone_number_has_allready_exist                             ("0407", "手机号已注册"),

    //================================================角色==============================================================
    role_shop_id_can_not_be_empty                                         ("0501", "店铺id不能为空"),





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
