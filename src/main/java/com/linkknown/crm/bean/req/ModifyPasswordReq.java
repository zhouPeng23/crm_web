package com.linkknown.crm.bean.req;

import lombok.Data;

/**
 * @author zhoupeng
 * @date 2023/4/5 19:46
 */
@Data
public class ModifyPasswordReq {

    private String phoneNumber;

    private String oldPassword;

    private String newPassword;

    private String newPasswordSecond;


}
