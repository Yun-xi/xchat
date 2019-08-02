package com.xx.xchat.service.impl;

import com.xx.xchat.ApplicationTests;
import com.xx.xchat.entity.RoleEntity;
import com.xx.xchat.service.RoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class RoleServiceImplTest extends ApplicationTests {


    @Autowired
    private RoleService roleService;

    @Test
    public void testSaveRole() {
        RoleEntity roleEntity = new RoleEntity()
                .setName("admin")
                .setRemark("系统管理员");
        roleService.save(roleEntity);
    }
}