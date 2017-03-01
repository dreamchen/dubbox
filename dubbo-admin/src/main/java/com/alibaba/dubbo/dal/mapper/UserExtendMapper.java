package com.alibaba.dubbo.dal.mapper;

import com.alibaba.dubbo.registry.common.domain.UserExtend;

import java.util.List;

/**
 * Created by Erlei Chen on 2017/2/17.
 */
public interface UserExtendMapper {

    int insertUserExtend(UserExtend userExtend);

    int updateUserExtendById(UserExtend userExtend);

    int updateUserExtendByIds(UserExtend userExtend);

    int updateUserExtendAllById(UserExtend userExtend);

    int updateUserExtendByCondition(UserExtend userExtend, UserExtend condition);

    int deleteUserExtendById(Long uId);

    int deleteUserExtendByIds(List<Long> ids);

    UserExtend findUserExtendById(Long uId);

    UserExtend findUserExtendByCondition(UserExtend condition);

    List<UserExtend> findUserExtendByConditionWithPage(UserExtend condition);

    int findCountUserExtend(UserExtend condition);
}
