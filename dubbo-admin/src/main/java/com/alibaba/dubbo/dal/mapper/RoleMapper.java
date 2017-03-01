package com.alibaba.dubbo.dal.mapper;

import com.alibaba.dubbo.registry.common.domain.Role;

import java.util.List;

/**
 * Created by Erlei Chen on 2017/2/17.
 */
public interface RoleMapper {

    int insertRole(Role role);

    int updateRoleById(Role role);

    int updateRoleByIds(Role role);

    int updateRoleAllById(Role role);

    int updateRoleByCondition(Role role, Role condition);

    int deleteRoleById(Long uId);

    int deleteRoleByIds(List<Long> ids);

    Role findRoleById(Long uId);

    Role findRoleByCondition(Role condition);

    List<Role> findRoleByConditionWithPage(Role condition);

    List<Role> findRoleByIds(List<Long> ids);

    int findCountRole(Role condition);
}
