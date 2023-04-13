package com.linkknown.crm.bean.res;

import lombok.Data;

/**
 * @author zhoupeng
 * @date 2023/4/5 19:46
 */
@Data
public class UserLoginRes {

    private String loginUserPhoneNumber;

    private String loginUserName;

    private String loginUserCanSearchShopIds;

    private String loginUserCanSearchAuthMenu;

}
