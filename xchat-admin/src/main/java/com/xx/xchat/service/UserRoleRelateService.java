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

    /**
     * 保存用户角色关联关系
     * @param userId
     * @param roleIds
     */
    void saveUserRoleRelate(String userId, List<String> roleIds);

    /**
     * 根据用户编号获取对应的角色列表
     * @param userId
     * @return
     */
    List<String> getByUserId(String userId);

    /**
     * 查询该角色下的所有用户编号
     * @param roleId
     * @return
     */
    List<String> getByRoleId(String roleId);
}
