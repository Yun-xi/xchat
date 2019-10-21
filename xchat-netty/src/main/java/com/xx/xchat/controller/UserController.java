package com.xx.xchat.controller;

import com.xx.xchat.entity.ChatMsgEntity;
import com.xx.xchat.entity.FriendsEntity;
import com.xx.xchat.entity.UserEntity;
import com.xx.xchat.entity.enums.SearchFriendsStatusEnum;
import com.xx.xchat.enums.OperatorFriendRequestTypeEnum;
import com.xx.xchat.exception.XException;
import com.xx.xchat.service.UserService;
import com.xx.xchat.utils.BaseResp;
import com.xx.xchat.utils.JwtTokenUtil;
import com.xx.xchat.validator.AddGroup;
import com.xx.xchat.validator.UpdateGroup;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.List;

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
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @PostMapping("/login/or/register")
    public ResponseEntity<BaseResp> loginOrRegister(@Validated(AddGroup.class) UserEntity userEntity) throws XException {
        String username = userEntity.getUsername();
        String password = userEntity.getPassword();
        String cid = userEntity.getCid();

        String userId = userService.loginOrRegister(username, password, cid);
        String randomKey = jwtTokenUtil.getRandomKey(6);
        String jwtToken = jwtTokenUtil.generateToken(userId, randomKey);

        return ResponseEntity.ok(BaseResp.ok(jwtToken));
    }

    /**
     * @Description: 搜索好友接口, 根据账号做匹配查询而不是模糊查询
     */
    @PostMapping("/search")
    public ResponseEntity<BaseResp> searchUser(@NotBlank String myUserId, @NotBlank String friendUsername)
            throws Exception {
        // 前置条件 - 1. 搜索的用户如果不存在，返回[无此用户]
        // 前置条件 - 2. 搜索账号是你自己，返回[不能添加自己]
        // 前置条件 - 3. 搜索的朋友已经是你的好友，返回[该用户已经是你的好友]
        Integer status = userService.preconditionSearchFriends(myUserId, friendUsername);
        if (status == SearchFriendsStatusEnum.SUCCESS.getValue()) {
            UserEntity user = userService.queryUserInfoByUsername(friendUsername);
            return ResponseEntity.ok(BaseResp.ok(user));
        }
        return ResponseEntity.ok(BaseResp.fail(SearchFriendsStatusEnum.parseValue(status).getDesc()));

    }


    /**
     * @Description: 发送添加好友的请求
     */
    @PostMapping("/addFriendRequest")
    public ResponseEntity<BaseResp> addFriendRequest(@NotBlank String myUserId, @NotBlank String friendUsername)
            throws Exception {
        // 前置条件 - 1. 搜索的用户如果不存在，返回[无此用户]
        // 前置条件 - 2. 搜索账号是你自己，返回[不能添加自己]
        // 前置条件 - 3. 搜索的朋友已经是你的好友，返回[该用户已经是你的好友]
        Integer status = userService.preconditionSearchFriends(myUserId, friendUsername);
        if (status == SearchFriendsStatusEnum.SUCCESS.getValue()) {
            userService.sendFriendRequest(myUserId, friendUsername);
        } else {
            String errorMsg = SearchFriendsStatusEnum.parseValue(status).getDesc();
            return ResponseEntity.ok(BaseResp.fail(errorMsg));
        }

        return ResponseEntity.ok(BaseResp.ok());
    }

    /**
     * @Description: 发送添加好友的请求
     */
    @PostMapping("/queryFriendRequests")
    public ResponseEntity<BaseResp> queryFriendRequests(@NotBlank String userId) {

        // 1. 查询用户接受到的朋友申请
        return ResponseEntity.ok(BaseResp.ok(userService.queryFriendRequestList(userId)));
    }


    /**
     * @Description: 接受方 通过或者忽略朋友请求
     */
    @PostMapping("/operFriendRequest")
    public ResponseEntity<BaseResp> operFriendRequest(@NotBlank String acceptUserId, @NotBlank String sendUserId, @NotBlank Integer operType) {
        if (operType == OperatorFriendRequestTypeEnum.IGNORE.type) {
            // 1. 判断如果忽略好友请求，则直接删除好友请求的数据库表记录
            userService.deleteFriendRequest(sendUserId, acceptUserId);
        } else if (operType == OperatorFriendRequestTypeEnum.PASS.type) {
            // 2. 判断如果是通过好友请求，则互相增加好友记录到数据库对应的表
            //	   然后删除好友请求的数据库表记录
            userService.passFriendRequest(sendUserId, acceptUserId);
        }

        // 4. 数据库查询好友列表
        List<FriendsEntity> myFirends = userService.queryMyFriends(acceptUserId);

        return ResponseEntity.ok(BaseResp.ok(myFirends));
    }

    /**
     * @Description: 查询我的好友列表
     */
    @PostMapping("/myFriends")
    public ResponseEntity<BaseResp> myFriends(@NotBlank String userId) {

        // 1. 数据库查询好友列表
        List<FriendsEntity> myFirends = userService.queryMyFriends(userId);

        return ResponseEntity.ok(BaseResp.ok(myFirends));
    }

    /**
     *
     * @Description: 用户手机端获取未签收的消息列表
     */
    @PostMapping("/getUnReadMsgList")
    public ResponseEntity<BaseResp> getUnReadMsgList(@NotBlank String acceptUserId) {
        // 查询列表
        List<ChatMsgEntity> unreadMsgList = userService.getUnReadMsgList(acceptUserId);

        return ResponseEntity.ok(BaseResp.ok(unreadMsgList));
    }
}
