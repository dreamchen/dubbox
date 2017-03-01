/**
 * Project: dubbo.registry-1.1.0-SNAPSHOT
 * <p>
 * File Created at 2010-4-15
 * $Id: RoleService.java 182013 2012-06-26 10:32:43Z tony.chenl $
 * <p>
 * Copyright 2008 Alibaba.com Croporation Limited.
 * All rights reserved.
 * <p>
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Alibaba.com.
 */
package com.alibaba.dubbo.governance.service;

import com.alibaba.dubbo.registry.common.domain.Role;

import java.util.List;

/**
 * RoleService
 *
 * @author william.liangf
 */
public interface RoleService {

    List<Role> findAllRoles();

    List<Role> findRolesByIds(List<Long> ids);
    
    Role findRoleById(Long id);

    boolean addRole(Role role);

    boolean updateRoleById(Role role, Long id);

    boolean updateRoleByIds(Role role, List<Long> ids);

    boolean deleteRoleById(Long id);

    boolean deleteRoleByIds(List<Long> ids);

}
