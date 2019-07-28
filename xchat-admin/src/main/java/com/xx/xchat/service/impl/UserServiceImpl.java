package com.xx.xchat.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xx.xchat.dao.UserMapper;
import com.xx.xchat.entity.UserEntity;
import com.xx.xchat.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author xieyaqi
 * @mail 987159036@qq.com
 * @date 2019-07-27 17:21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService{
}
