package com.linkknown.crm.service;

import com.linkknown.crm.bean.dos.Role;

import java.util.List;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
public interface IRoleService {

    List<Role> queryRoleList(Role role);

    Role queryRoleById(Role role);
}
