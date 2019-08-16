package com.xx.xchat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xx.xchat.entity.UserEntity;

public interface UserService extends IService<UserEntity> {
    /**
     * 新增用户
     * @param userEntity
     */
    void saveUser(UserEntity userEntity);

    /**
     * 修改用户
     * @param userEntity
     */
    void updateUser(UserEntity userEntity);

    /**
     * 修改密码
     * @param userId        用户编号
     * @param oldPassword   旧密码
     * @param newPassword   新密码
     */
    boolean updatePassword(Integer userId, String oldPassword, String newPassword);
}
