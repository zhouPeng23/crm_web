package com.linkknown.crm.api;

import com.linkknown.crm.bean.dos.Role;
import com.linkknown.crm.common.aspect.exception.WebExceptionService;
import com.linkknown.crm.common.aspect.paramslog.WebParamsLog;
import com.linkknown.crm.common.response.BaseResponse;
import com.linkknown.crm.common.util.paramutil.RoleParamUtils;
import com.linkknown.crm.service.IRoleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:42
 */
@WebExceptionService
@RestController
@RequestMapping("/crmWebApi/role")
@Validated
public class RoleController {

    @Resource
    private IRoleService roleService;

    @PostMapping(value = "/queryRoleList")
    @WebParamsLog(description = "查询角色集合")
    public BaseResponse<List<Role>> queryRoleList(Role role){
        RoleParamUtils.queryAllRolesValidate(role);
        List<Role> roleList = roleService.queryRoleList(role);
        return BaseResponse.success(roleList);
    }


    @PostMapping(value = "/queryRoleById")
    @WebParamsLog(description = "根据角色id查询角色")
    public BaseResponse<Role> queryRoleById(Role role){
        Role roleById = roleService.queryRoleById(role);
        return BaseResponse.success(roleById);
    }

}
