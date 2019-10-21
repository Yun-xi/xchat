package com.xx.xchat.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xx.xchat.entity.MenuEntity;

import java.util.List;

public interface MenuMapper extends BaseMapper<MenuEntity> {
    List<MenuEntity> findUserPerms(String userId);
}
