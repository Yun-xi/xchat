package com.xx.xchat.dao;

import com.xx.xchat.XchatAdminApplicationTests;
import com.xx.xchat.dao.UserMapper;
import com.xx.xchat.entity.UserEntity;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.UUID;

public class UserMapperTest extends XchatAdminApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void seleteUser() {
        UserEntity userEntity = userMapper.seleteUser(1);
        Assert.assertEquals("测试", userEntity.getName());
    }


    @Test
    public void testCount() {
        UserEntity userEntity = userMapper.selectById(1);
        Assert.assertNull(userEntity);
    }

    public void selectAll(){

    }
}