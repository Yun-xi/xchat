package com.xx.xchat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xx.xchat.entity.FriendsEntity;
import com.xx.xchat.entity.FriendsRequestEntity;
import com.xx.xchat.entity.UserEntity;
import com.xx.xchat.exception.XException;
import com.xx.xchat.netty.domain.ChatMsg;
import com.xx.xchat.pojo.vo.FriendRequestVO;

import java.util.List;

public interface UserService extends IService<UserEntity> {
    String loginOrRegister(String username, String password, String cid) throws XException;

    /**
     * 保存聊天消息到数据库
     * @param chatMsg
     * @return
     */
    String saveMsg(ChatMsg chatMsg);

    UserEntity queryUserInfoByUsername(String username);

    Integer preconditionSearchFriends(String myUserId, String friendUsername);

    void sendFriendRequest(String myUserId, String friendUsername);

    List<FriendsRequestEntity> queryFriendRequestList(String acceptUserId);

    void deleteFriendRequest(String sendUserId, String acceptUserId);

    void passFriendRequest(String sendUserId, String acceptUserId);

    List<FriendsEntity> queryMyFriends(String userId);

    /**
     * 批量签收消息
     * @param msgIdList
     */
    public void updateMsgSigned(List<String> msgIdList);
}
