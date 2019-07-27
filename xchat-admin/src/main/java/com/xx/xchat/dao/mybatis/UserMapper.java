package com.xx.xchat.dao.mybatis;

import com.xx.xchat.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    UserEntity seleteUser(@Param("id")Integer id);
}
