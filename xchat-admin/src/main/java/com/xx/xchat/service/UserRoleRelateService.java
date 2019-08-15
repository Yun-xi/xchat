package com.xx.xchat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xx.xchat.entity.UserRoleRelateEntity;

import java.util.List;

/**
 * @author xieyaqi
 * @mail xieyaqi11@gmail.com
 * @date 2019-07-30 13:39
 */
public interface UserRoleRelateService extends IService<UserRoleRelateEntity> {

    void saveUserRoleRelate(Integer userId, List<Integer> roleIds);
}
