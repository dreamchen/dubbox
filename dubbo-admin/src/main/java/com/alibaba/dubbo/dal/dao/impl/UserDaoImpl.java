package com.alibaba.dubbo.dal.dao.impl;

import com.alibaba.dubbo.dal.dao.UserDao;
import com.alibaba.dubbo.registry.common.domain.User;

import java.util.List;

/**
 * Created by Erlei Chen on 2017/2/17.
 */
public class UserDaoImpl implements UserDao {
    @Override
    public Boolean insertUser(User user) {
        return null;
    }

    @Override
    public Boolean updateUserById(User user) {
        return null;
    }

    @Override
    public Boolean updateUserByCondition(User user, User userCond) {
        return null;
    }

    @Override
    public Boolean deleteUserById(Integer uId) {
        return null;
    }

    @Override
    public User findUserById(Integer uId) {
        return null;
    }

    @Override
    public User findUserByCondition(User user) {
        return null;
    }

    @Override
    public List<User> findUserByConditionWithPage(User user) {
        return null;
    }

    @Override
    public int findCountUser(User user) {
        return 0;
    }
}
