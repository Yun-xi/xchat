package com.xx.xchat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xx.xchat.entity.MenuEntity;

import java.util.List;

public interface MenuService extends IService<MenuEntity> {
    List<MenuEntity> findUserPerms(Integer userId);
}
