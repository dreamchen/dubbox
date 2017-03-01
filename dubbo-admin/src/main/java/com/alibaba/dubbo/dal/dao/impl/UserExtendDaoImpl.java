package com.alibaba.dubbo.dal.dao.impl;

import com.alibaba.dubbo.dal.dao.UserExtendDao;
import com.alibaba.dubbo.dal.mapper.UserExtendMapper;
import com.alibaba.dubbo.registry.common.domain.UserExtend;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Erlei Chen on 2017/2/17.
 */
public class UserExtendDaoImpl implements UserExtendDao {

    @Resource
    private UserExtendMapper userExtendMapper;

    @Override
    public Boolean insertUserExtend(UserExtend userExtend) {
        int count = this.userExtendMapper.insertUserExtend(userExtend);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean updateUserExtendById(UserExtend userExtend, Long uId) {
        userExtend.setId(uId);
        int count = this.userExtendMapper.updateUserExtendById(userExtend);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean updateUserExtendByIds(UserExtend userExtend, List<Long> ids) {
        userExtend.setIds(ids);
        int count = this.userExtendMapper.updateUserExtendByIds(userExtend);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean updateUserExtendByCondition(UserExtend userExtend, UserExtend userExtendCond) {
        int count = this.userExtendMapper.updateUserExtendByCondition(userExtend, userExtendCond);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean deleteUserExtendById(Long uId) {
        int count = this.userExtendMapper.deleteUserExtendById(uId);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public Boolean deleteUserExtendByIds(List<Long> ids) {
        int count = this.userExtendMapper.deleteUserExtendByIds(ids);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserExtend findUserExtendById(Long uId) {
        return this.userExtendMapper.findUserExtendById(uId);
    }

    @Override
    public UserExtend findUserExtendByCondition(UserExtend userExtendCond) {
        return this.userExtendMapper.findUserExtendByCondition(userExtendCond);
    }

    @Override
    public List<UserExtend> findUserExtendByConditionWithPage(UserExtend userExtendCond) {
        return this.userExtendMapper.findUserExtendByConditionWithPage(userExtendCond);
    }

    @Override
    public int findCountUserExtend(UserExtend userExtendCond) {
        return this.userExtendMapper.findCountUserExtend(userExtendCond);
    }
}
