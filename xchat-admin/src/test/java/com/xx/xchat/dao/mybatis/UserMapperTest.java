package com.xx.xchat.dao.mybatis;

import com.xx.xchat.XchatAdminApplicationTests;
import com.xx.xchat.entity.UserEntity;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserMapperTest extends XchatAdminApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void seleteUser() {
        UserEntity userEntity = userMapper.seleteUser(1);
        Assert.assertEquals("测试", userEntity.getName());
    }
}