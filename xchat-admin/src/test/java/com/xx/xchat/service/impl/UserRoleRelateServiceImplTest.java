package com.xx.xchat.service.impl;

import com.xx.xchat.ApplicationTests;
import com.xx.xchat.entity.UserRoleRelateEntity;
import com.xx.xchat.service.UserRoleRelateService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

import static org.junit.Assert.*;

public class UserRoleRelateServiceImplTest extends ApplicationTests {

    @Autowired
    private UserRoleRelateService userRoleRelateService;

    @Test
    public void testSaveRelate() {

        userRoleRelateService.saveUserRoleRelate(1, Collections.singletonList(1));
    }
}