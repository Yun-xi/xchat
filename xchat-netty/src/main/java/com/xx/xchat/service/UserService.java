package com.xx.xchat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xx.xchat.entity.UserEntity;
import com.xx.xchat.exception.XException;

public interface UserService extends IService<UserEntity> {
    String loginOrRegister(String username, String password) throws XException;
}
