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
        UserEntity userEntity = userService.getById("1");
        Assert.assertNull(userEntity);
    }

    @Test
    public void testSave() {
        UserEntity userEntity = new UserEntity()
                .setState(StateEnum.NORMAL)
                .setSalt(UUID.randomUUID().toString())
                .setPassword(UUID.randomUUID().toString())
                .setMail("987@qq.com")
                .setDepartmentId("1")
                .setName(UUID.randomUUID().toString().substring(11))
                .setPhone("133333333333");
        boolean result = userService.save(userEntity);
        Assert.assertTrue(result);
    }

    @Test
    public void testUpdate() {
        UserEntity userEntity = userService.getById("1156059805262737410");
        userEntity.setName(UUID.randomUUID().toString().substring(11));
        userEntity.setVersion(0L);

        boolean b = userService.updateById(userEntity);
        Assert.assertFalse(b);
    }
}