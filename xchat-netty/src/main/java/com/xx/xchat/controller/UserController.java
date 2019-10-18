package com.xx.xchat.controller;

import com.xx.xchat.entity.UserEntity;
import com.xx.xchat.service.UserService;
import com.xx.xchat.utils.BaseResp;
import com.xx.xchat.validator.AddGroup;
import com.xx.xchat.validator.UpdateGroup;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

/**
 * @author xieyaqi
 * @mail xieyaqi11@gmail.com
 * @date 2019-10-16 17:25
 */
@Api("用户")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login/or/register")
    public ResponseEntity<BaseResp> loginOrRegister(@Validated(AddGroup.class) UserEntity userEntity) {
        String username = userEntity.getUsername();
        String password = userEntity.getPassword();

        String jwtToken = userService.loginOrRegister(username, password);
        return "hello";
    }
}
