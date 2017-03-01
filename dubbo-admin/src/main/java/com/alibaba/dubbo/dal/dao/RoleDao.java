package com.alibaba.dubbo.dal.dao;

import com.alibaba.dubbo.registry.common.domain.Role;

import java.util.List;

/**
 * Created by Erlei Chen on 2017/2/17.
 */
public interface RoleDao {

    /**
     * 创建角色
     *
     * @param role
     * @return
     */
    Boolean insertRole(Role role);

    /**
     * 根据id更新角色
     *
     * @param role
     * @param uId
     * @return
     */
    Boolean updateRoleById(Role role, Long uId);

    /**
     * 根据id更新角色
     *
     * @param role
     * @param ids
     * @return
     */
    Boolean updateRoleByIds(Role role, List<Long> ids);

    /**
     * 根据条件更新角色
     *
     * @param role
     * @param roleCond
     * @return
     */
    Boolean updateRoleByCondition(Role role, Role roleCond);

    /**
     * 根据id删除角色
     *
     * @param uId
     * @return
     */
    Boolean deleteRoleById(Long uId);

    /**
     * 根据id删除角色
     *
     * @param ids
     * @return
     */
    Boolean deleteRoleByIds(List<Long> ids);

    /**
     * 根据ID获取角色
     *
     * @param uId
     * @return
     */
    Role findRoleById(Long uId);

    /**
     * 获取角色
     *
     * @param role
     * @return
     */
    Role findRoleByCondition(Role role);

    /**
     * 分页获取角色列表
     *
     * @param role
     * @return
     */
    List<Role> findRoleByConditionWithPage(Role role);

    /**
     * 分页获取角色列表
     *
     * @param ids
     * @return
     */
    List<Role> findRoleByIds(List<Long> ids);

    /**
     * 获取角色个数
     *
     * @param role
     * @return
     */
    int findCountRole(Role role);
}
