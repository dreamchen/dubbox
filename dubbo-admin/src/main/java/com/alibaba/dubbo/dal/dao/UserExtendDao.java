package com.alibaba.dubbo.dal.dao;

import com.alibaba.dubbo.registry.common.domain.UserExtend;

import java.util.List;

/**
 * Created by Erlei Chen on 2017/2/17.
 */
public interface UserExtendDao {

    /**
     * 创建用户
     *
     * @param userExtend
     * @return
     */
    Boolean insertUserExtend(UserExtend userExtend);

    /**
     * 根据id更新用户
     *
     * @param userExtend
     * @param uId
     * @return
     */
    Boolean updateUserExtendById(UserExtend userExtend, Long uId);

    /**
     * 根据id更新用户
     *
     * @param userExtend
     * @param ids
     * @return
     */
    Boolean updateUserExtendByIds(UserExtend userExtend, List<Long> ids);

    /**
     * 根据条件更新用户
     *
     * @param userExtend
     * @param userExtendCond
     * @return
     */
    Boolean updateUserExtendByCondition(UserExtend userExtend, UserExtend userExtendCond);

    /**
     * 根据id删除用户
     *
     * @param uId
     * @return
     */
    Boolean deleteUserExtendById(Long uId);
    /**
     * 根据id删除用户
     *
     * @param ids
     * @return
     */
    Boolean deleteUserExtendByIds(List<Long> ids);

    /**
     * 根据ID获取用户
     *
     * @param uId
     * @return
     */
    UserExtend findUserExtendById(Long uId);

    /**
     * 获取用户
     *
     * @param userExtend
     * @return
     */
    UserExtend findUserExtendByCondition(UserExtend userExtend);

    /**
     * 分页获取用户列表
     *
     * @param userExtend
     * @return
     */
    List<UserExtend> findUserExtendByConditionWithPage(UserExtend userExtend);

    /**
     * 获取用户个数
     *
     * @param userExtend
     * @return
     */
    int findCountUserExtend(UserExtend userExtend);
}
