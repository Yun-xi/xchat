package com.xx.xchat.service.impl;

import com.xx.xchat.ApplicationTests;
import com.xx.xchat.entity.MenuEntity;
import com.xx.xchat.entity.enums.MenuTypeEnum;
import com.xx.xchat.service.MenuService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MenuServiceImplTest extends ApplicationTests {

    @Autowired
    private MenuService menuService;

    @Test
    public void testSaveMenu() {
        MenuEntity menuEntity1 = new MenuEntity()
                .setName("系统管理")
                .setUrl(null)
                .setPerms(null)
                .setType(MenuTypeEnum.MENU)
                .setIcon("setting")
                .setParentId(0);
        MenuEntity menuEntity2 = new MenuEntity()
                .setName("用户管理")
                .setUrl("user.html")
                .setPerms(null)
                .setType(MenuTypeEnum.MENU)
                .setIcon("user")
                .setParentId(1);
        MenuEntity menuEntity3 = new MenuEntity()
                .setName("角色管理")
                .setUrl("role.html")
                .setPerms(null)
                .setType(MenuTypeEnum.MENU)
                .setIcon("role")
                .setParentId(1);
        MenuEntity menuEntity4 = new MenuEntity()
                .setName("菜单管理")
                .setUrl("menu.html")
                .setPerms(null)
                .setType(MenuTypeEnum.MENU)
                .setIcon("menu")
                .setParentId(1);
        MenuEntity menuEntity5 = new MenuEntity()
                .setName("新增")
                .setUrl("user.html")
                .setPerms(null)
                .setType(MenuTypeEnum.MENU)
                .setIcon("user")
                .setParentId(2);
        List<MenuEntity> menuEntities = Arrays.asList(menuEntity1, menuEntity2, menuEntity3, menuEntity4, menuEntity5);
        menuService.saveBatch(menuEntities);
    }
}