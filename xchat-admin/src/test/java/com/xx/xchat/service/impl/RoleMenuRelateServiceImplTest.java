package com.xx.xchat.service.impl;

import com.xx.xchat.ApplicationTests;
import com.xx.xchat.entity.RoleMenuRelateEntity;
import com.xx.xchat.service.RoleMenuRelateService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class RoleMenuRelateServiceImplTest extends ApplicationTests {

    @Autowired
    private RoleMenuRelateService roleMenuRelateService;

    @Test
    public void testSaveRelate(){
        RoleMenuRelateEntity roleMenuRelateEntity1 = new RoleMenuRelateEntity()
                .setRoleId(1)
                .setMenuId(1);
        RoleMenuRelateEntity roleMenuRelateEntity2 = new RoleMenuRelateEntity()
                .setRoleId(1)
                .setMenuId(2);
        RoleMenuRelateEntity roleMenuRelateEntity3 = new RoleMenuRelateEntity()
                .setRoleId(1)
                .setMenuId(3);
        RoleMenuRelateEntity roleMenuRelateEntity4 = new RoleMenuRelateEntity()
                .setRoleId(1)
                .setMenuId(4);
        RoleMenuRelateEntity roleMenuRelateEntity5 = new RoleMenuRelateEntity()
                .setRoleId(1)
                .setMenuId(5);
        List<RoleMenuRelateEntity> roleMenuRelateEntities = Arrays.asList(roleMenuRelateEntity1, roleMenuRelateEntity2, roleMenuRelateEntity3, roleMenuRelateEntity4, roleMenuRelateEntity5);
        roleMenuRelateService.saveBatch(roleMenuRelateEntities);
    }
}