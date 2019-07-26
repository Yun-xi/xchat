package com.xx.xchat.dao.jpa;

import com.xx.xchat.XchatAdminApplicationTests;
import com.xx.xchat.entity.UserEntity;
import com.xx.xchat.enums.StateEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import static org.junit.Assert.*;

public class UserRepositoryTest extends XchatAdminApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void insert() {
        UserEntity userEntity = userRepository.save(UserEntity.builder()
                .name("测试")
                .departmentId(1)
                .mail("987159036@qq.com")
                .password("123456")
                .salt("123456")
                .state(StateEnum.NORMAL.getValue())
                .phone("1888888888")
                .build());

        Assert.assertEquals("测试", userEntity.getName());
    }

    @Test
    public void update() {
        UserEntity userEntity = UserEntity.builder()
                .name("测试")
                .departmentId(1)
                .mail("987159036@qq.com")
                .password("123456789")
                .salt("123456789")
                .state(StateEnum.NORMAL.getValue())
                .phone("1888888888")
                .build();
        userEntity.setId(1);
        UserEntity user = userRepository.save(userEntity);

        Assert.assertEquals("测试", user.getName());
    }
}