package com.xx.xchat.controller;

import com.xx.xchat.entity.UserEntity;
import com.xx.xchat.service.UserService;
import com.xx.xchat.utils.BaseResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author xieyaqi
 * @mail 987159036@qq.com
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

    @ApiOperation("新增用户")
    @PostMapping("/create")
    public ResponseEntity<BaseResp> create(@RequestBody @Validated UserEntity userEntity){
        userService.saveUser(userEntity);
        return ResponseEntity.ok(BaseResp.ok());
    }
}
