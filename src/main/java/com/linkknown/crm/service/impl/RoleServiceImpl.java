package com.linkknown.crm.service.impl;

import com.linkknown.crm.bean.dos.Role;
import com.linkknown.crm.mapper.RoleMapper;
import com.linkknown.crm.service.IRoleService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Resource
    private RoleMapper roleMapper;


    @Override
    public List<Role> queryRoleList(Role role) {
        return roleMapper.selectRoleList(null);
    }

    @Override
    public Role queryRoleById(Role role) {
        return roleMapper.selectRoleById(Long.valueOf(role.getRoleId()));
    }


}
