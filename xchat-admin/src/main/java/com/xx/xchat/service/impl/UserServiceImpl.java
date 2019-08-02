package com.xx.xchat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xx.xchat.dao.UserMapper;
import com.xx.xchat.entity.UserEntity;
import com.xx.xchat.service.UserRoleRelateService;
import com.xx.xchat.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.xx.xchat.utils.Constants.Shiro.hashAlgorithmName;
import static com.xx.xchat.utils.Constants.Shiro.hashIterations;

/**
 * @author xieyaqi
 * @mail 987159036@qq.com
 * @date 2019-07-27 17:21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService{

    @Autowired
    private UserRoleRelateService userRoleRelateService;


    @Override
    public void saveUser(UserEntity userEntity) {
        // 获取盐、加密密码并保存用户
        String salt = RandomStringUtils.randomAlphanumeric(32);
        String password = userEntity.getPassword();
        password = new SimpleHash(hashAlgorithmName, password, salt, hashIterations).toString();
        userEntity.setSalt(salt);
        userEntity.setPassword(password);
        this.save(userEntity);

        // 保存用户与角色关联关系
        userRoleRelateService.saveUserRoleRelate(userEntity.getId(), userEntity.getRoleIds());
    }
}
