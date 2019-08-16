package com.xx.xchat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xx.xchat.dao.UserRoleRelateMapper;
import com.xx.xchat.entity.UserRoleRelateEntity;
import com.xx.xchat.service.UserRoleRelateService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author xieyaqi
 * @mail xieyaqi11@gmail.com
 * @date 2019-07-30 13:39
 */
@Service
public class UserRoleRelateServiceImpl extends ServiceImpl<UserRoleRelateMapper, UserRoleRelateEntity> implements UserRoleRelateService {

    @Override
    public void saveUserRoleRelate(Integer userId, List<Integer> roleIds) {
        // 先全部删除
        this.remove(new QueryWrapper<UserRoleRelateEntity>().eq("user_id", userId));

        if (CollectionUtils.isEmpty(roleIds)) {
            return;
        }

        // 再进行保存
        List<UserRoleRelateEntity> userRoleRelateEntities = roleIds.stream().map(roleId ->
                new UserRoleRelateEntity().setUserId(userId).setRoleId(roleId)
        ).collect(toList());
        this.saveBatch(userRoleRelateEntities);
    }

    @Override
    public List<Integer> getByUserId(Integer userId) {
        List<UserRoleRelateEntity> userRoleRelateEntities = this.list(Wrappers.<UserRoleRelateEntity>lambdaQuery().eq(UserRoleRelateEntity::getUserId, userId));
        if (CollectionUtils.isEmpty(userRoleRelateEntities)) {
            return null;
        }
        return userRoleRelateEntities.stream().map(UserRoleRelateEntity::getRoleId).collect(toList());
    }

    @Override
    public List<Integer> getByRoleId(Integer roleId) {
        List<UserRoleRelateEntity> userRoleRelateEntities = this.list(Wrappers.<UserRoleRelateEntity>lambdaQuery().eq(UserRoleRelateEntity::getRoleId, roleId));
        if (CollectionUtils.isEmpty(userRoleRelateEntities)) {
            return null;
        }
        return userRoleRelateEntities.stream().map(UserRoleRelateEntity::getUserId).collect(toList());
    }
}
