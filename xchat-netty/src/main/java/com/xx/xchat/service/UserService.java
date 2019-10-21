package com.xx.xchat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xx.xchat.entity.UserEntity;
import com.xx.xchat.exception.XException;
import com.xx.xchat.netty.domain.ChatMsg;

import java.util.List;

public interface UserService extends IService<UserEntity> {
    String loginOrRegister(String username, String password, String cid) throws XException;

    /**
     * 保存聊天消息到数据库
     * @param chatMsg
     * @return
     */
    String saveMsg(ChatMsg chatMsg);

    /**
     * 批量签收消息
     * @param msgIdList
     */
    public void updateMsgSigned(List<String> msgIdList);
}
