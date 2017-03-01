package com.alibaba.dubbo.governance.service.impl;

import com.alibaba.dubbo.dal.dao.RoleDao;
import com.alibaba.dubbo.governance.service.RoleService;
import com.alibaba.dubbo.registry.common.domain.Role;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Erlei Chen on 2017/2/22.
 */
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Override
    public List<Role> findAllRoles() {
        return this.roleDao.findRoleByConditionWithPage(null);
    }

    @Override
    public List<Role> findRolesByIds(List<Long> ids) {
        return this.roleDao.findRoleByIds(ids);
    }

    @Override
    public Role findRoleById(Long id) {
        return this.roleDao.findRoleById(id);
    }

    @Override
    public boolean addRole(Role role) {
        return this.roleDao.insertRole(role);
    }

    @Override
    public boolean updateRoleById(Role role, Long id) {
        return this.roleDao.updateRoleById(role, id);
    }

    @Override
    public boolean updateRoleByIds(Role role, List<Long> ids) {
        return this.roleDao.updateRoleByIds(role, ids);
    }

    @Override
    public boolean deleteRoleById(Long id) {
        return this.roleDao.deleteRoleById(id);
    }

    @Override
    public boolean deleteRoleByIds(List<Long> ids) {
        return this.roleDao.deleteRoleByIds(ids);
    }
}
