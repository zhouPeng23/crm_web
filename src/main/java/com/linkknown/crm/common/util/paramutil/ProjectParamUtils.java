package com.linkknown.crm.common.util.paramutil;

import com.linkknown.crm.bean.dos.Project;
import com.linkknown.crm.bean.dos.Role;
import com.linkknown.crm.common.aspect.exception.WebException;
import com.linkknown.crm.common.enums.ResponseEnum;
import org.springframework.util.StringUtils;

/**
 * @author zhoupeng
 * @date 2023/4/4 14:55
 */
public class ProjectParamUtils {

    /**
     * 查询店内项目校验
     * @param project 入参
     */
    public static void queryAllProjectsValidate(Project project) {
        //店铺id不能为空
        if (StringUtils.isEmpty(project.getShopId())){
            throw new WebException(ResponseEnum.role_shop_id_can_not_be_empty);
        }
    }



}
