/**
 * Project: dubbo.registry-1.1.0-SNAPSHOT
 * <p>
 * File Created at 2010-4-15
 * $Id: UserServiceImpl.java 182013 2012-06-26 10:32:43Z tony.chenl $
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
package com.alibaba.dubbo.governance.service.impl;

import com.alibaba.dubbo.dal.dao.UserExtendDao;
import com.alibaba.dubbo.governance.service.UserService;
import com.alibaba.dubbo.registry.common.domain.User;
import com.alibaba.dubbo.registry.common.domain.UserExtend;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * IBatisUserService
 *
 * @author william.liangf
 */
public class UserServiceImpl extends AbstractService implements UserService {

//	private String rootPassword;

//	public void setRootPassword(String password) {
//		this.rootPassword = (password == null ? "" : password);
//	}

//	private String guestPassword;

//	public void setGuestPassword(String password) {
//		this.guestPassword = (password == null ? "" : password);
//	}

    @Resource
    private UserExtendDao userExtendDao;

    public UserExtend findUser(String username) {

        UserExtend userExtend = new UserExtend();
        userExtend.setUsername(username);
        return this.userExtendDao.findUserExtendByCondition(userExtend);


//    	if ("guest".equals(username)) {
//    		User user = new User();
//            user.setUsername(username);
//            user.setPassword(Coder.encodeMd5(username + ":" + User.REALM + ":" + guestPassword));
//            user.setName(username);
//            user.setRole(User.GUEST);
//            user.setEnabled(true);
//            user.setLocale("zh");
//            user.setServicePrivilege("");
//            return user;
//    	} else if ("root".equals(username)) {
//    		User user = new User();
//            user.setUsername(username);
//            user.setPassword(Coder.encodeMd5(username + ":" + User.REALM + ":" + rootPassword));
//            user.setName(username);
//            user.setRole(User.ROOT);
//            user.setEnabled(true);
//            user.setLocale("zh");
//            user.setServicePrivilege("*");
//            return user;
//    	}
//    	return null;
    }

    public List<UserExtend> findAllUsers() {
        // TODO Auto-generated method stub
        return this.userExtendDao.findUserExtendByConditionWithPage(null);
    }

    public Map<String, User> findAllUsersMap() {
        // TODO Auto-generated method stub
        return null;
    }

    public UserExtend findById(Long id) {
        // TODO Auto-generated method stub
        return this.userExtendDao.findUserExtendById(id);
    }

    public boolean createUser(UserExtend userExtend) {
        // TODO Auto-generated method stub
        return this.userExtendDao.insertUserExtend(userExtend);
    }

    public void updateUser(User user) {
        // TODO Auto-generated method stub

    }

    public boolean updateUser(UserExtend userExtend, List<Long> ids) {
        // TODO Auto-generated method stub
        return this.userExtendDao.updateUserExtendByIds(userExtend, ids);
    }

    public boolean modifyUser(UserExtend userExtend) {
        // TODO Auto-generated method stub
        return this.userExtendDao.updateUserExtendById(userExtend, userExtend.getId());
    }

    public boolean updatePassword(UserExtend userExtend, Long id) {
        // TODO Auto-generated method stub
        return this.userExtendDao.updateUserExtendById(userExtend, id);
    }

    public void resetPassword(User user) {
        // TODO Auto-generated method stub

    }

    public void enableUser(User user) {
        // TODO Auto-generated method stub

    }

    public void disableUser(User user) {
        // TODO Auto-generated method stub

    }

    public boolean deleteUserById(Long id) {
        // TODO Auto-generated method stub
        return this.userExtendDao.deleteUserExtendById(id);
    }

    public boolean deleteUserByIds(List<Long> ids) {
        // TODO Auto-generated method stub
        return this.userExtendDao.deleteUserExtendByIds(ids);
    }

    public List<User> findUsersByServiceName(String serviceName) {
        // TODO Auto-generated method stub
        return null;
    }

}
