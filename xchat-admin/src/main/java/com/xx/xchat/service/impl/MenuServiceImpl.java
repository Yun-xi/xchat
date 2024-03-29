package com.xx.xchat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xx.xchat.dao.MenuMapper;
import com.xx.xchat.entity.MenuEntity;
import com.xx.xchat.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xieyaqi
 * @mail xieyaqi11@gmail.com
 * @date 2019-07-30 13:49
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> implements MenuService {

    @Override
    public List<MenuEntity> findUserPerms(String userId) {
        return baseMapper.findUserPerms(userId);
    }
}
