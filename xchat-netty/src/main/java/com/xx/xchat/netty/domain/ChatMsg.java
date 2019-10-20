package com.xx.xchat.netty.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xieyaqi
 * @mail 987159036@qq.com
 * @date 2019-10-20 23:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMsg implements Serializable {

    private static final long serialVersionUID = -870576358789888146L;

    private String msg;         // 聊天内容

    private String senderId;    // 发送者的用户Id

    private String receiverId;  // 接收者的和用户Id

    private String msgId;       // 用于消息的签收
}
