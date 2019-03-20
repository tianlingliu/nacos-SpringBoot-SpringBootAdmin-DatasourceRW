package com.aibao.claims.auth.mapper;

import com.aibao.claims.auth.mapper.entity.UserInfo;

public interface UserInfoMapper {

    UserInfo selectByPrimaryKey(Integer id);

    int insert(UserInfo userInfo);

}