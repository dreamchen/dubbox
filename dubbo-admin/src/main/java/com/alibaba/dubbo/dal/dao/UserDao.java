package com.alibaba.dubbo.dal.dao;

import com.alibaba.dubbo.registry.common.domain.User;

import java.util.List;

/**
 * Created by Erlei Chen on 2017/2/17.
 */
public interface UserDao {

    /**
     * 创建赛季
     *
     * @param user
     * @return
     */
    Boolean insertUser(User user);

    /**
     * 根据id更新赛季
     *
     * @param user
     * @return
     */
    Boolean updateUserById(User user);

    /**
     * 根据条件更新赛季
     *
     * @param user
     * @param userCond
     * @return
     */
    Boolean updateUserByCondition(User user, User userCond);

    /**
     * 根据id删除赛季
     *
     * @param uId
     * @return
     */
    Boolean deleteUserById(Integer uId);

    /**
     * 根据ID获取赛季
     *
     * @param uId
     * @return
     */
    User findUserById(Integer uId);

    /**
     * 获取赛季
     *
     * @param user
     * @return
     */
    User findUserByCondition(User user);

    /**
     * 分页获取赛季列表
     *
     * @param user
     * @return
     */
    List<User> findUserByConditionWithPage(User user);

    /**
     * 获取赛季个数
     *
     * @param user
     * @return
     */
    int findCountUser(User user);
}
