package com.xx.xchat.service.impl;

import com.xx.xchat.XchatAdminApplicationTests;
import com.xx.xchat.entity.UserEntity;
import com.xx.xchat.enums.StateEnum;
import com.xx.xchat.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.UUID;


public class UserServiceImplTest extends XchatAdminApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void testSelect() {
        UserEntity userEntity = userService.getById(1);
        Assert.assertNull(userEntity);
    }

    @Test
    public void testSave() {
        UserEntity userEntity = UserEntity.builder()
                .createTime(new Date())
                .updateTime(new Date())
                .state(StateEnum.NORMAL.getValue())
                .salt(UUID.randomUUID().toString())
                .password(UUID.randomUUID().toString())
                .mail("987@qq.com")
                .departmentId(1)
                .name("张三")
                .phone("133333333333")
                .build();
        boolean result = userService.save(userEntity);
        Assert.assertTrue(result);
    }
}