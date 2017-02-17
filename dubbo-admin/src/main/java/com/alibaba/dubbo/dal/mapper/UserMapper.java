package com.alibaba.dubbo.dal.mapper;

import com.alibaba.dubbo.registry.common.domain.User;

import java.util.List;

/**
 * Created by Erlei Chen on 2017/2/17.
 */
public interface UserMapper {

    int insertUser(User user);

    int updateUserById(User user);

    int updateUserByCondition(User user, User userCond);

    int deleteUserById(Integer uId);

    User findUserById(Integer uId);

    User findUserByCondition(User user);

    List<User> findUserByConditionWithPage(User user);

    int findCountUser(User user);
}
