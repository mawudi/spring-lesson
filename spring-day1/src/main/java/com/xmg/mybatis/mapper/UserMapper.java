package com.xmg.mybatis.mapper;

import com.xmg.mybatis.pojo.User;

public interface UserMapper {
    User queryUserById(Long userId);
}
