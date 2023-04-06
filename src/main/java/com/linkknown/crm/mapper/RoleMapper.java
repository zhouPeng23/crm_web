package com.linkknown.crm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linkknown.crm.bean.dos.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhoupeng
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    public Role selectRoleById(Long roleId);

    public List<Role> selectRoleList(Role role);

    public int insertRole(Role role);

    public int updateRole(Role role);

    public int deleteRoleById(Long roleId);

    public int deleteRoleByIds(String[] roleIds);
}