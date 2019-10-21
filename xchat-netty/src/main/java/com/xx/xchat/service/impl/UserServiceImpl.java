package com.xx.xchat.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xx.xchat.dao.ChatMsgMapper;
import com.xx.xchat.dao.UserMapper;
import com.xx.xchat.entity.ChatMsgEntity;
import com.xx.xchat.entity.FriendsEntity;
import com.xx.xchat.entity.FriendsRequestEntity;
import com.xx.xchat.entity.UserEntity;
import com.xx.xchat.entity.enums.MsgSignFlagEnum;
import com.xx.xchat.entity.enums.SearchFriendsStatusEnum;
import com.xx.xchat.enums.ErrorEnum;
import com.xx.xchat.enums.MsgActionEnum;
import com.xx.xchat.exception.XException;
import com.xx.xchat.netty.domain.UserChannelRel;
import com.xx.xchat.netty.domain.ChatMsg;
import com.xx.xchat.netty.domain.DataContent;
import com.xx.xchat.service.ChatMsgService;
import com.xx.xchat.service.FriendsRequestService;
import com.xx.xchat.service.FriendsService;
import com.xx.xchat.service.UserService;
import com.xx.xchat.utils.JsonUtils;
import com.xx.xchat.utils.MD5Util;
import com.xx.xchat.utils.SnowflakeIdWorker;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author xieyaqi
 * @mail xieyaqi11@gmail.com
 * @date 2019-10-16 16:51
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Autowired
    private ChatMsgService chatMsgService;
    @Autowired
    private ChatMsgMapper chatMsgMapper;
    @Autowired
    private ChatMsgService chatMsgService;
    @Autowired
    private FriendsService friendsService;
    @Autowired
    private FriendsRequestService friendsRequestService;

    @Override
    @Transactional
    public String loginOrRegister(String username, String password, String cid) throws XException {

        UserEntity userEntity = this.getOne(Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getUsername, username));
        boolean usernameIsExist = (null != userEntity);

        if (usernameIsExist) {
            // 登录
            String targetPassword = userEntity.getPassword();
            String salt = userEntity.getSalt();

            String currentPassword = MD5Util.encrypt(password + salt);
            if (!currentPassword.equals(targetPassword)) {
                throw new XException(ErrorEnum.PASSWORD_IS_ERROR, "用户名或密码错误");
            }
        } else {
            // 注册
            String salt = UUID.randomUUID().toString();
            String md5Password = MD5Util.encrypt(password + salt);

            userEntity = UserEntity.builder().username(username).password(md5Password).salt(salt).cid(cid).build();
            boolean registerResult = this.save(userEntity);
            if (registerResult) {
                userEntity = this.getOne(Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getUsername, username));
            } else {
                throw new XException(ErrorEnum.REGISTER_FILED, "注册失败");
            }
        }

        return userEntity.getId();
    }

    @Override
    @Transactional
    public String saveMsg(ChatMsg chatMsg) {
        String primaryId = SnowflakeIdWorker.getStringId();
        ChatMsgEntity chatMsgEntity =
                new ChatMsgEntity().setSendUserId(chatMsg.getSenderId()).setAcceptUserId(chatMsg.getReceiverId()).setMsg(chatMsg.getMsg()).setSignFlag(MsgSignFlagEnum.NOSIGN);
        chatMsgEntity.setId(primaryId);

        chatMsgService.save(chatMsgEntity);
        return primaryId;
    }

    @Override
    @Transactional
    public UserEntity queryUserInfoByUsername(String username) {
        return this.getOne(Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getUsername, username));
    }

    @Override
    @Transactional
    public Integer preconditionSearchFriends(String myUserId, String friendUsername) {
        UserEntity user = queryUserInfoByUsername(friendUsername);

        // 1. 搜索的用户如果不存在，返回[无此用户]
        if (user == null) {
            return SearchFriendsStatusEnum.USER_NOT_EXIST.getValue();
        }

        // 2. 搜索账号是你自己，返回[不能添加自己]
        if (user.getId().equals(myUserId)) {
            return SearchFriendsStatusEnum.NOT_YOURSELF.getValue();
        }

        // 3. 搜索的朋友已经是你的好友，返回[该用户已经是你的好友]
        FriendsEntity friends = friendsService.getOne(Wrappers.<FriendsEntity>lambdaQuery().eq(FriendsEntity::getMyUserId, myUserId).eq(FriendsEntity::getMyFriendUserId, user.getId()));
        if (friends != null) {
            return SearchFriendsStatusEnum.ALREADY_FRIENDS.getValue();
        }

        return SearchFriendsStatusEnum.SUCCESS.getValue();
    }

    @Override
    @Transactional
    public void sendFriendRequest(String myUserId, String friendUsername) {
        // 根据用户名把朋友信息查询出来
        UserEntity friend = queryUserInfoByUsername(friendUsername);

        // 1. 查询发送好友请求记录表
        FriendsRequestEntity friendsRequest = friendsRequestService.getOne(Wrappers.<FriendsRequestEntity>lambdaQuery().eq(FriendsRequestEntity::getSendUserId, myUserId).eq(FriendsRequestEntity::getAcceptUserId, friend.getId()));

        if (friendsRequest == null) {
            // 2. 如果不是你的好友，并且好友记录没有添加，则新增好友请求记录
            String requestId = SnowflakeIdWorker.getStringId();

            FriendsRequestEntity request = new FriendsRequestEntity();
            request.setId(requestId);
            request.setSendUserId(myUserId);
            request.setAcceptUserId(friend.getId());
            friendsRequestService.save(request);
        }
    }

    @Override
    @Transactional
    public List<FriendsRequestEntity> queryFriendRequestList(String acceptUserId) {
        return friendsRequestService.list(Wrappers.<FriendsRequestEntity>lambdaQuery().eq(FriendsRequestEntity::getAcceptUserId, acceptUserId));
    }

    @Override
    @Transactional
    public void deleteFriendRequest(String sendUserId, String acceptUserId) {
        friendsRequestService.remove(Wrappers.<FriendsRequestEntity>lambdaQuery().eq(FriendsRequestEntity::getSendUserId, sendUserId).eq(FriendsRequestEntity::getAcceptUserId, acceptUserId));
    }

    @Override
    @Transactional
    public void passFriendRequest(String sendUserId, String acceptUserId) {
        saveFriends(sendUserId, acceptUserId);
        saveFriends(acceptUserId, sendUserId);
        deleteFriendRequest(sendUserId, acceptUserId);

        Channel sendChannel = UserChannelRel.get(sendUserId);
        if (sendChannel != null) {
            // 使用websocket主动推送消息到请求发起者，更新他的通讯录列表为最新
            DataContent dataContent = new DataContent();
            dataContent.setAction(MsgActionEnum.PULL_FRIEND.type);

            sendChannel.writeAndFlush(
                    new TextWebSocketFrame(
                            JsonUtils.objectToJson(dataContent)));
        }
    }

    @Override
    public List<FriendsEntity> queryMyFriends(String userId) {
        return friendsService.list(Wrappers.<FriendsEntity>lambdaQuery().eq(FriendsEntity::getMyUserId, userId));
    }

    @Transactional
    public void saveFriends(String sendUserId, String acceptUserId) {
        FriendsEntity myFriends = new FriendsEntity();
        String recordId = SnowflakeIdWorker.getStringId();
        myFriends.setId(recordId);
        myFriends.setMyFriendUserId(acceptUserId);
        myFriends.setMyUserId(sendUserId);
        friendsService.save(myFriends);
    }

    @Override
    @Transactional
    public void updateMsgSigned(List<String> msgIdList) {
        List<ChatMsgEntity> chatMsgEntityList = chatMsgMapper.selectBatchIds(msgIdList);
        chatMsgEntityList.stream().forEach(chatMsgEntity -> chatMsgEntity.setSignFlag(MsgSignFlagEnum.ALREADY));

        chatMsgService.updateBatchById(chatMsgEntityList);
    }

    @Override
    @Transactional
    public List<ChatMsgEntity> getUnReadMsgList(String acceptUserId) {
        List<ChatMsgEntity> chatMsgEntityList = chatMsgService.list(Wrappers.<ChatMsgEntity>lambdaQuery().eq(ChatMsgEntity::getAcceptUserId, acceptUserId).eq(ChatMsgEntity::getSignFlag, 0));
        return chatMsgEntityList;
    }

}
