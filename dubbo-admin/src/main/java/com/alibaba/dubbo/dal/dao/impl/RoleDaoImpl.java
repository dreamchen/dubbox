package com.alibaba.dubbo.dal.dao.impl;

import com.alibaba.dubbo.dal.dao.RoleDao;
import com.alibaba.dubbo.dal.mapper.RoleMapper;
import com.alibaba.dubbo.registry.common.domain.Role;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Erlei Chen on 2017/2/17.
 */
public class RoleDaoImpl implements RoleDao {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public Boolean insertRole(Role role) {
        int count = this.roleMapper.insertRole(role);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean updateRoleById(Role role, Long uId) {
        role.setId(uId);
        int count = this.roleMapper.updateRoleById(role);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean updateRoleByIds(Role role, List<Long> ids) {
        role.setIds(ids);
        int count = this.roleMapper.updateRoleByIds(role);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean updateRoleByCondition(Role role, Role roleCond) {
        int count = this.roleMapper.updateRoleByCondition(role, roleCond);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean deleteRoleById(Long uId) {
        int count = this.roleMapper.deleteRoleById(uId);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public Boolean deleteRoleByIds(List<Long> ids) {
        int count = this.roleMapper.deleteRoleByIds(ids);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Role findRoleById(Long uId) {
        return this.roleMapper.findRoleById(uId);
    }

    @Override
    public Role findRoleByCondition(Role roleCond) {
        return this.roleMapper.findRoleByCondition(roleCond);
    }

    @Override
    public List<Role> findRoleByConditionWithPage(Role roleCond) {
        return this.roleMapper.findRoleByConditionWithPage(roleCond);
    }

    @Override
    public List<Role> findRoleByIds(List<Long> ids) {
        return this.roleMapper.findRoleByIds(ids);
    }

    @Override
    public int findCountRole(Role roleCond) {
        return this.roleMapper.findCountRole(roleCond);
    }
}
