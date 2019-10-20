package com.xx.xchat.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xx.xchat.dao.UserMapper;
import com.xx.xchat.entity.UserEntity;
import com.xx.xchat.enums.ErrorEnum;
import com.xx.xchat.exception.XException;
import com.xx.xchat.service.UserService;
import com.xx.xchat.utils.JwtTokenUtil;
import com.xx.xchat.utils.MD5Util;
import com.xx.xchat.validator.AddGroup;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * @author xieyaqi
 * @mail xieyaqi11@gmail.com
 * @date 2019-10-16 16:51
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Override
    public Integer loginOrRegister(String username, String password, String cid) throws XException {

        UserEntity userEntity = this.getOne(Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getUsername, username));
        boolean usernameIsExist = (null != userEntity);

        if (usernameIsExist) {
            // 登录
            String targetPassword = userEntity.getPassword();
            String salt = userEntity.getSalt();

            String currentPassword = MD5Util.encrypt(password + salt);
            if (!currentPassword.equals(targetPassword)) {
                throw new XException(ErrorEnum.PASSWORD_IS_ERROR, "用户名或密码错误");
            }
        } else {
            // 注册
            String salt = UUID.randomUUID().toString();
            String md5Password = MD5Util.encrypt(password + salt);

            userEntity = UserEntity.builder().username(username).password(md5Password).salt(salt).cid(cid).build();
            boolean registerResult = this.save(userEntity);
            if (registerResult) {
                userEntity = this.getOne(Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getUsername, username));
            } else {
                throw new XException(ErrorEnum.REGISTER_FILED, "注册失败");
            }
        }

        return userEntity.getId();
    }

}
