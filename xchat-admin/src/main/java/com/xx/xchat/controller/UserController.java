package com.xx.xchat.controller;

import com.xx.xchat.entity.UserEntity;
import com.xx.xchat.service.UserService;
import com.xx.xchat.utils.BaseResp;
import com.xx.xchat.validator.AddGroup;
import com.xx.xchat.validator.UpdateGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;

/**
 * @author xieyaqi
 * @mail xieyaqi11@gmail.com
 * @date 2019-07-30 15:48
 */
@Api(tags = "用户管理")
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<BaseResp> login(String userName, String password) {
        try{
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            subject.login(token);
        }catch (UnknownAccountException e) {
            return ResponseEntity.ok(BaseResp.fail(e.getMessage()));
        }catch (IncorrectCredentialsException e) {
            return ResponseEntity.ok(BaseResp.fail("账号或密码不正确"));
        }catch (LockedAccountException e) {
            e.printStackTrace();
            return ResponseEntity.ok(BaseResp.fail("账号已被锁定,请联系管理员"));
        }catch (AuthenticationException e) {
            return ResponseEntity.ok(BaseResp.fail("账户验证失败"));
        }

        return ResponseEntity.ok(BaseResp.ok());
    }

    @ApiOperation("登出")
    @GetMapping("/logout")
    @ResponseBody
    public ResponseEntity<BaseResp> logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        return ResponseEntity.ok(BaseResp.ok());
    }

    /**
     * @Validated和@Valid的区别：
     *
     * @Validated:  1.org.springframework.validation.annotation包下的，标准JSR-303的一个变种
     *              2.支持分组
     *              3.用在方法入参上无法单独提供嵌套验证功能。不能用在成员属性（字段）上，也无法提示框架进行嵌套验证。能配合嵌套验证注解@Valid进行嵌套验证。
     *
     * @Valid:      1.javax.validation包下的，标准JSR-303规范
     *              2.不支持分组
     *              3.用在方法入参上无法单独提供嵌套验证功能。能够用在成员属性（字段）上，提示验证框架进行嵌套验证。能配合嵌套验证注解@Valid进行嵌套验证。
     *
     * @param userEntity
     * @return
     */
    @ApiOperation("新增用户")
    @PostMapping("/create")
    @RequiresPermissions("user:create")
    public ResponseEntity<BaseResp<String>> create(@RequestBody @Validated(AddGroup.class) UserEntity userEntity){
        userService.saveUser(userEntity);
        return ResponseEntity.ok(BaseResp.ok("新增成功"));
    }

    @ApiOperation("修改用户")
    @PutMapping("/update")
    @RequiresPermissions("user:update")
    public ResponseEntity<BaseResp<String>> update(@RequestBody @Validated({UpdateGroup.class}) UserEntity userEntity) {
        userService.updateUser(userEntity);
        return ResponseEntity.ok(BaseResp.ok("修改成功"));
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/delete")
    @RequiresPermissions("user:delete")
    public ResponseEntity<BaseResp<String>> delete(@RequestBody @NotBlank ArrayList userIds) {
        userService.removeByIds(userIds);
        return ResponseEntity.ok(BaseResp.ok("删除成功"));
    }
}
