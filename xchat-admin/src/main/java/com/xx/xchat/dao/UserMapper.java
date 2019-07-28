package com.xx.xchat.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xx.xchat.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<UserEntity> {

    UserEntity seleteUser(@Param("id")Integer id);
}
