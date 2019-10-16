package com.xx.xchat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xx.xchat.dao.UserMapper;
import com.xx.xchat.entity.UserEntity;
import com.xx.xchat.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author xieyaqi
 * @mail xieyaqi11@gmail.com
 * @date 2019-10-16 16:51
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
}
