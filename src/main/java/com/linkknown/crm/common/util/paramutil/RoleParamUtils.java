package com.linkknown.crm.common.util.paramutil;

import com.linkknown.crm.bean.dos.Project;
import com.linkknown.crm.bean.dos.Role;
import com.linkknown.crm.bean.dos.Shop;
import com.linkknown.crm.common.aspect.exception.WebException;
import com.linkknown.crm.common.enums.ResponseEnum;
import com.linkknown.crm.common.util.RegexUtils;
import org.springframework.util.StringUtils;

/**
 * @author zhoupeng
 * @date 2023/4/4 14:55
 */
public class RoleParamUtils {


    /**
     * 查询所有角色校验
     * @param role role
     */
    public static void queryRoleList(Role role) {
        //门店id不能为空
        if (StringUtils.isEmpty(role.getShopId())){
            throw new WebException(ResponseEnum.role_shop_id_can_not_be_empty);
        }
    }



}
