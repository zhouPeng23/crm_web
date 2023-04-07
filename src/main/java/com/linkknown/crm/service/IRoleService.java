package com.linkknown.crm.service;

import com.linkknown.crm.bean.dos.Role;

import java.util.List;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
public interface IRoleService {

    /**
     * 查询角色列表
     * @param role 角色
     * @return 角色列表
     */
    List<Role> queryRoleList(Role role);


    /**
     * 根据角色id查询角色
     * @param role 角色
     * @return 角色
     */
    Role queryRoleById(Role role);
}
