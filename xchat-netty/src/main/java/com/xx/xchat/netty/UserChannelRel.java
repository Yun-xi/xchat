package com.xx.xchat.netty;

import io.netty.channel.Channel;

import java.util.HashMap;

/**
 * 用户id和channel的关联关系处理
 * @author xieyaqi
 * @mail 987159036@qq.com
 * @date 2019-10-20 23:43
 */
public class UserChannelRel {

    private static HashMap<String, Channel> manager = new HashMap<>();

    public static void put(String senderId, Channel channel) {
        manager.put(senderId, channel);
    }

    public static Channel get(String senderId) {
        return manager.get(senderId);
    }
}
