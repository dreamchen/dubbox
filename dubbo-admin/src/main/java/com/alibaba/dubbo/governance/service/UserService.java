/**
 * Project: dubbo.registry-1.1.0-SNAPSHOT
 * <p>
 * File Created at 2010-4-15
 * $Id: UserService.java 182013 2012-06-26 10:32:43Z tony.chenl $
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

import com.alibaba.dubbo.registry.common.domain.User;
import com.alibaba.dubbo.registry.common.domain.UserExtend;

import java.util.List;

/**
 * UserService
 *
 * @author william.liangf
 */
public interface UserService {

    List<UserExtend> findAllUsers();

    UserExtend findUser(String username);

    UserExtend findById(Long id);

    boolean createUser(UserExtend userExtend);

    void updateUser(User user);

    boolean updateUser(UserExtend userExtend, List<Long> ids);

    boolean modifyUser(UserExtend userExtend);

    boolean updatePassword(UserExtend userExtend, Long id);

    void resetPassword(User user);

    void enableUser(User user);

    void disableUser(User user);

    boolean deleteUserById(Long id);

    boolean deleteUserByIds(List<Long> ids);

}
