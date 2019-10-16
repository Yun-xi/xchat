package com.xx.xchat.controller;

import com.xx.xchat.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
