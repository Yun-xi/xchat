package com.xx.xchat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xx.xchat.entity.UserEntity;
import com.xx.xchat.exception.XException;

public interface UserService extends IService<UserEntity> {
    Integer loginOrRegister(String username, String password, String cid) throws XException;
}
