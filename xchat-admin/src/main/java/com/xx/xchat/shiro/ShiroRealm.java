package com.xx.xchat.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xx.xchat.entity.*;
import com.xx.xchat.entity.enums.StateEnum;
import com.xx.xchat.service.*;
import com.xx.xchat.utils.Constants;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Component
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleRelateService userRoleRelateService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;


    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserEntity user = (UserEntity)principalCollection.getPrimaryPrincipal();
        String userId = user.getId();

        // 角色
        List<UserRoleRelateEntity> userRoleRelateEntities = userRoleRelateService.list(new QueryWrapper<UserRoleRelateEntity>().eq("user_id", userId));
        Set<String> roleNameSet = null;
        if (CollectionUtils.isNotEmpty(userRoleRelateEntities)) {
            Set<String> roleIdSet = userRoleRelateEntities.stream().map(UserRoleRelateEntity::getRoleId).collect(toSet());

            Collection<RoleEntity> roleEntities = roleService.listByIds(roleIdSet);
            roleNameSet = roleEntities.stream().map(RoleEntity::getName).collect(toSet());
        }

        // 权限
        List<MenuEntity> menuEntityList = menuService.findUserPerms(userId);
        Set<String> permsSet = menuEntityList.stream().map(MenuEntity::getPerms).collect(toSet());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        info.setRoles(roleNameSet);
        return info;
    }

    /**
     * 认证登录
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();

        UserEntity userEntity = userService.getOne(new QueryWrapper<UserEntity>().eq("name", username));
        if (null == userEntity) {
            throw new UnknownAccountException("账号或密码不正确");
        }

        if (StateEnum.BAN.equals(userEntity.getState())) {
            throw new LockedAccountException("账号已过期");
        }

        return new SimpleAuthenticationInfo(userEntity, userEntity.getPassword(),
                ByteSource.Util.bytes(userEntity.getSalt()), getName());
    }

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName(Constants.Shiro.hashAlgorithmName);
        shaCredentialsMatcher.setHashIterations(Constants.Shiro.hashIterations);
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }
}
